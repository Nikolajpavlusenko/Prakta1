package one.controller.command.user.post;


import one.controller.command.Command;
import one.model.service.PaymentService;
import one.model.service.UtilityService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class CreateOrderCommand implements Command {
    @Inject
    PaymentService paymentService;
    @Inject
    UtilityService utilityService;



    @Override
    public String execute(HttpServletRequest request) {
        int utilityId = Integer.parseInt(request.getParameter("utilityId"));
        try {
            request.setAttribute("utility", utilityService.getUtility(utilityId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/user/create_order.jsp";
    }
}
