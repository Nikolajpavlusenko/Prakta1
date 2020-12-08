package one.controller.command.admin.post;


import one.controller.command.Command;
import one.model.service.UtilityService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class UpdateUtilityCommand implements Command {
    @Inject
    UtilityService utilityService;


    @Override
    public String execute(HttpServletRequest request) {
        int utilityId = Integer.parseInt(request.getParameter("utilityId2"));
        try {
           request.setAttribute("utility", utilityService.getUtility(utilityId));
        } catch (Exception e) {
        }
        return "/WEB-INF/admin/update_utility.jsp";
    }
}