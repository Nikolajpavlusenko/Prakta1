package one.controller.command.user;




import one.controller.command.Command;

import one.model.service.UtilityService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class PaymentCommand implements Command {
    @Inject
    UtilityService utilityService;


    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("items", utilityService.getUtilities());
        return "/WEB-INF/user/utilityList.jsp";
    }
}
