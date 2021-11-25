package apap.tugaskelompok.sibusiness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class PageController {
    @RequestMapping("/")
    private String home(HttpServletRequest request, Model model) {
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
