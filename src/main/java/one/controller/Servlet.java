package one.controller;


import one.controller.command.Command;
import one.controller.command.LogoutCommand;
import one.controller.command.admin.AddNewUtilityCommand;
import one.controller.command.admin.AdminUtilityListCommand;
import one.controller.command.admin.post.AddNewUtilityPostCommand;
import one.controller.command.admin.post.DeleteUtilityCommand;
import one.controller.command.admin.post.UpdateUtilityCommand;
import one.controller.command.admin.post.UpdateUtilitySubmitCommand;
import one.controller.command.guest.LoginPageCommand;
import one.controller.command.guest.MainCommand;
import one.controller.command.guest.RegistrationPageCommand;
import one.controller.command.guest.post.LoginCommand;
import one.controller.command.guest.post.RegistrationCommand;
import one.controller.command.user.PaymentCommand;
import one.controller.command.user.post.AddUtilityToPaymentCommand;
import one.controller.command.user.post.CreateOrderCommand;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet("/")
public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();


    public void init(ServletConfig servletConfig){
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        commands.put("logout",container.select(LogoutCommand.class).get());
        commands.put("login",container.select(LoginPageCommand.class).get());
        commands.put("log", container.select(LoginCommand.class).get());
        commands.put("reg", container.select(RegistrationCommand.class).get());
        commands.put("registration",container.select(RegistrationPageCommand.class).get());
        commands.put("",container.select(MainCommand.class).get());

        commands.put("admin/add_new_utility",container.select(AddNewUtilityCommand.class).get());
        commands.put("admin/adminUtilityList", container.select(AdminUtilityListCommand.class).get());
        commands.put("admin/deleteUtility", container.select(DeleteUtilityCommand.class).get());
        commands.put("admin/updateUtility", container.select(UpdateUtilityCommand.class).get());
        commands.put("admin/updateUtilitySubmit",container.select(UpdateUtilitySubmitCommand.class).get());
        commands.put("admin/addNewUtilityPost", container.select(AddNewUtilityPostCommand.class).get());

        commands.put("user/order_utility", container.select(PaymentCommand.class).get());
        commands.put("user/order_create", container.select(CreateOrderCommand.class).get());
        commands.put("user/addOrderToPayment", container.select(AddUtilityToPaymentCommand.class).get());

    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/web/" , "");
        Command command = commands.getOrDefault(path ,
                (req)->"/error.jsp");
        String page = command.execute(request);
        if (page.contains("redirect"))
            response.sendRedirect(request.getContextPath() + page.replace("redirect:", ""));
        else
            request.getRequestDispatcher(page).forward(request,response);
    }

}
