package apap.tugaskelompok.sibusiness.controller;

import apap.tugaskelompok.sibusiness.models.RoleModel;
import apap.tugaskelompok.sibusiness.models.UserModel;
import apap.tugaskelompok.sibusiness.repository.UserDB;
import apap.tugaskelompok.sibusiness.rest.CabangDTO;
import apap.tugaskelompok.sibusiness.service.RoleService;
import apap.tugaskelompok.sibusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDB userDB;

    @GetMapping(value = "/add")
    private String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRole",listRole);
        return "form-add-user";
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model){
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping(value = "/update/{uuid}")
    private String updateUserFormPage(
            @PathVariable(name = "uuid") String uuid,
            Model model
    ){
        UserModel user = userService.getUserByUuid(uuid);
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRole",listRole);
        return "form-update-user";
    }

    @PostMapping(value = "/update")
    private String updateUserSubmit(
            @ModelAttribute UserModel user,
            Model model
    ){
        userService.updateUser(user);
        String uuid = user.getUuid();
        model.addAttribute("uuid",uuid);
        return "update-user";
    }

    @GetMapping(value = "/list")
    private String getListRole(Model model){
        List<UserModel> listUser = userDB.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role =auth.getAuthorities().toString().replace("[", "").replace("]","");
        if(role.equals("Manager Business")){
            model.addAttribute("listUser", listUser);
            return "list-user";
        }
        return "error/403";
    }
}
