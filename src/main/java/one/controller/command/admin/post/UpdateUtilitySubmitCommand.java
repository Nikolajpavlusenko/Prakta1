package one.controller.command.admin.post;


import one.controller.command.Command;

import one.model.entity.Utility;

import one.model.service.UtilityService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class UpdateUtilitySubmitCommand implements Command {
    @Inject
    UtilityService utilityService;


    @Override
    public String execute(HttpServletRequest request) {
        int utilityId = Integer.parseInt(request.getParameter("id"));
        String utilityName = request.getParameter("name");
        int utilityPrice = Integer.parseInt(request.getParameter("price"));
        Utility utility = new Utility(utilityId, utilityName, utilityPrice);
        utilityService.updateUtility(utility);
        return "redirect:/admin/adminUtilityList";
    }
}
