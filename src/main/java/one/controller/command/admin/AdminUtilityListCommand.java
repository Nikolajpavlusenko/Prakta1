package one.controller.command.admin;


import one.controller.command.Command;
import one.model.entity.Utility;
import one.model.service.UtilityService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminUtilityListCommand  implements Command {
    @Inject
    UtilityService utilityService;


    @Override
    public String execute(HttpServletRequest request) {
        String utilityName = request.getParameter("utilityName");
        String utilityPrice = request.getParameter("price");
        List<Utility> utilities =  utilityService.searchUtilitiesFromList(utilityService.getUtilities(), utilityName, utilityPrice);
        request.setAttribute("items", utilities);

        return "/WEB-INF/admin/adminUtilityList.jsp";
    }
}
