package apap.tugaskelompok.sibusiness.controller;

import apap.tugaskelompok.sibusiness.rest.MesinDTO;
import apap.tugaskelompok.sibusiness.service.MesinRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/mesin")
public class MesinController {

    @Autowired
    private MesinRestService mesinRestService;

    @RequestMapping(value = {"/list", "/list/{idKategori}"}, method = RequestMethod.GET)
    private String getListMesin(
            @PathVariable(name = "idKategori",required = false) Integer idKategori,
            Model model
    ){
        List<MesinDTO> listMesin = mesinRestService.getMesinList().getResult();
        int res = 0;
        if (idKategori != null){
            model.addAttribute("idKategori",idKategori);
        } else {
            res = 1;
            int num = -1;
            model.addAttribute("idKategori", num);
        }
        model.addAttribute("res",res);
        model.addAttribute("listMesin", listMesin);
        return "list-mesin";
    }
}
