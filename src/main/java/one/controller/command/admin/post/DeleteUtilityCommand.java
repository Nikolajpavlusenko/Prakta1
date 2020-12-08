package one.controller.command.admin.post;


import one.controller.command.Command;
import one.model.service.UtilityService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class DeleteUtilityCommand implements Command {
    @Inject
    UtilityService utilityService;


    @Override
    public String execute(HttpServletRequest request) {
        int utilityId = Integer.parseInt(request.getParameter("utilityId"));
        try {
            utilityService.deleteUtility(utilityId);
        } catch (Exception e) {

        }
        return "redirect:/admin/adminUtilityList";
    }
}
