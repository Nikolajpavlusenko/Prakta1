package one.controller.command.user.post;


import one.controller.command.Command;
import one.model.entity.Payment;
import one.model.service.PaymentService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class AddUtilityToPaymentCommand implements Command {
    @Inject
    PaymentService paymentService;


    @Override
    public String execute(HttpServletRequest request) {

        int utilityId = Integer.parseInt(request.getParameter("utilityId"));
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int price = Integer.parseInt(request.getParameter("price"));
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        Payment payment = new Payment.Builder()
                .utilityId(utilityId)
                .clientId((Integer) request.getSession().getAttribute("userId"))
                .amount(amount)
                .price(price*amount)
                .date(date)
                .build();
        paymentService.saveNewPayment(payment);
        request.setAttribute("payment", payment);
        request.setAttribute("utilityName", name);
        request.setAttribute("utilityPrice", price);
        return "/WEB-INF/user/order_submit.jsp";
    }
}
