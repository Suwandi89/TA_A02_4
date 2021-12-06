package apap.tugaskelompok.sibusiness.controller;

import apap.tugaskelompok.sibusiness.models.UserModel;
import apap.tugaskelompok.sibusiness.rest.CabangDTO;
import apap.tugaskelompok.sibusiness.service.CabangRestService;
import apap.tugaskelompok.sibusiness.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/cabang")
public class CabangController {

    @Autowired
    private CabangRestService cabangRestService;

    @GetMapping(value = "/add")
    private String addCabang(Model model){
        CabangDTO cabangDummy = new CabangDTO();
        model.addAttribute("cabang", cabangDummy);
        return "form-cabang";
    }
    @PostMapping(value = "/add")
    private String bukaCabang(@ModelAttribute CabangDTO cabang, Model model){
        Mono<String> result=cabangRestService.addCabangRequest(cabang);
        System.out.println(result);
        model.addAttribute("cabang", cabang);
        return "form-cabang";
    }
}
