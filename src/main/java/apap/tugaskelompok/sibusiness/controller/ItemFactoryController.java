package apap.tugaskelompok.sibusiness.controller;

import apap.tugaskelompok.sibusiness.models.ItemFactoryModel;
import apap.tugaskelompok.sibusiness.models.UserModel;
import apap.tugaskelompok.sibusiness.service.ItemFactoryRestService;
import apap.tugaskelompok.sibusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/itemFactory")
public class ItemFactoryController {
    @Autowired
    private ItemFactoryRestService itemFactoryRestService;

    @Autowired
    private UserService userService;

    @GetMapping("/viewall")
    public String listItemFactory(Model model) {
        List<ItemFactoryModel> listItemFactory = itemFactoryRestService.getItemFactoryList();

        model.addAttribute("listItemFactory", listItemFactory);
        return "viewall-itemFactory";
    }

    @RequestMapping("/accept/{id}")
    public String acceptItemFactory(
            @PathVariable Long id,
            Model model) {
        ItemFactoryModel itemFactoryModel = itemFactoryRestService.getById(id);
        itemFactoryModel.setStatus(1);
        System.out.println(itemFactoryModel.getName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userService.getUserByUsername(authentication.getName());
        itemFactoryModel.setApprover(user);

        itemFactoryRestService.updateItemFactory(itemFactoryModel);
        model.addAttribute("name", itemFactoryModel.getName());
        model.addAttribute("isApproved", 1);
        return "accepted-itemFactory";
    }

    @RequestMapping("/reject/{id}")
    public String rejectItemFactory(
            @PathVariable Long id,
            Model model) {
        ItemFactoryModel itemFactoryModel = itemFactoryRestService.getById(id);
        itemFactoryModel.setStatus(2);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userService.getUserByUsername(authentication.getName());
        itemFactoryModel.setApprover(user);

        itemFactoryRestService.updateItemFactory(itemFactoryModel);
        model.addAttribute("name", itemFactoryModel.getName());
        model.addAttribute("isApproved", 0);
        itemFactoryRestService.deleteItemFactory(itemFactoryModel);
        return "rejected-itemFactory";
    }
}