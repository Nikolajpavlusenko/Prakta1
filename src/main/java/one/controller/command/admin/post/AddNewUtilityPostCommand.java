package one.controller.command.admin.post;


import one.controller.command.Command;
import one.model.entity.Utility;
import one.model.service.UtilityService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class AddNewUtilityPostCommand implements Command {

    @Inject
    UtilityService utilityService;


    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
            try {
                utilityService.addUtility(new Utility(name, price));
            } catch (Exception e) {
                System.out.println(e);
            }
        return "redirect:/admin/add_new_utility";
    }
}
