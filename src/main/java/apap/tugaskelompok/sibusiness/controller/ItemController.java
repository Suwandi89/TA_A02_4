package apap.tugaskelompok.sibusiness.controller;

import apap.tugaskelompok.sibusiness.models.UserModel;
import apap.tugaskelompok.sibusiness.rest.ItemDTO;
import apap.tugaskelompok.sibusiness.rest.ResponseDTO;
import apap.tugaskelompok.sibusiness.service.ItemRestService;
import apap.tugaskelompok.sibusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRestService itemRestService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
    private String getListItem(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userService.getUserByUsername(authentication.getName());
        List<String> listKategori = Arrays.asList(new String[]{"BUKU", "DAPUR", "MAKANAN & MINUMAN", "ELEKTRONIK", "FASHION", "KECANTIKAN & PERAWATAN DIRI", "FILM & MUSIK", "GAMING", "GADGET", "KESEHATAN", "RUMAH TANGGA", "FURNITURE", "ALAT & PERANGKAT KERAS", "WEDDING"});
        List<ItemDTO> listItem1 = itemRestService.getItemList(1).getResult();
        List<ItemDTO> listItem2 = itemRestService.getItemList(2).getResult();
        List<ItemDTO> listItem3 = itemRestService.getItemList(3).getResult();
        List<ItemDTO> listItem4 = itemRestService.getItemList(4).getResult();
        List<ItemDTO> listItem5 = itemRestService.getItemList(5).getResult();
        List<ItemDTO> listItem6 = itemRestService.getItemList(6).getResult();
        List<ItemDTO> listItem7 = itemRestService.getItemList(7).getResult();
        List<ItemDTO> listItem8 = itemRestService.getItemList(8).getResult();
        List<ItemDTO> listItem9 = itemRestService.getItemList(9).getResult();
        List<ItemDTO> listItem10 = itemRestService.getItemList(10).getResult();
        List<ItemDTO> listItem11 = itemRestService.getItemList(11).getResult();
        List<ItemDTO> listItem12 = itemRestService.getItemList(12).getResult();
        List<ItemDTO> listItem13 = itemRestService.getItemList(13).getResult();
        List<ItemDTO> listItem14 = itemRestService.getItemList(14).getResult();
        model.addAttribute("listItem1",listItem1);
        model.addAttribute("listItem2",listItem2);
        model.addAttribute("listItem3",listItem3);
        model.addAttribute("listItem4",listItem4);
        model.addAttribute("listItem5",listItem5);
        model.addAttribute("listItem6",listItem6);
        model.addAttribute("listItem7",listItem7);
        model.addAttribute("listItem8",listItem8);
        model.addAttribute("listItem9",listItem9);
        model.addAttribute("listItem10",listItem10);
        model.addAttribute("listItem11",listItem11);
        model.addAttribute("listItem12",listItem12);
        model.addAttribute("listItem13",listItem13);
        model.addAttribute("listItem14",listItem14);
        model.addAttribute("listKategori", listKategori);
        model.addAttribute("itemSize",listKategori.size());
        model.addAttribute("role",user.getRole().getRole());
        return "list-item";
    }

    @GetMapping(value = "/detail/{uuid}")
    private String getDetailItem(
            @PathVariable(name = "uuid") String uuid,
            Model model) {
        ItemDTO result = itemRestService.getItemDetail(uuid).getResult();
        model.addAttribute("item",result);
        return "view-item";
    }
}
