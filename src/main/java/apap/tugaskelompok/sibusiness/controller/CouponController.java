package apap.tugaskelompok.sibusiness.controller;

import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.models.CouponTypeModel;
import apap.tugaskelompok.sibusiness.models.UserModel;
import apap.tugaskelompok.sibusiness.rest.CabangDTO;
import apap.tugaskelompok.sibusiness.rest.CouponDTO;
import apap.tugaskelompok.sibusiness.rest.ItemDTO;
import apap.tugaskelompok.sibusiness.rest.Pair;
import apap.tugaskelompok.sibusiness.service.CouponService;
import apap.tugaskelompok.sibusiness.service.ItemRestService;
import apap.tugaskelompok.sibusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/list-coupon")
    private String getAllKupon(
            Model model) {
        Pair<List<CouponDTO>, List<String>> pair=couponService.getAllCoupon();
        List<CouponDTO> listCoupon=pair.key;
        List<String> useDay=pair.value;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("listCoupon",listCoupon);
        model.addAttribute("useDay",useDay);
        model.addAttribute("role",user.getRole().getRole());
        return "list-kupon";
    }
    @GetMapping(value = "/edit/{idCoupon}/{useDay}")
    public String editCoupon(
            @PathVariable(name = "idCoupon") Long idCoupon,
            @PathVariable(name = "useDay") String useDay,
            Model model
    ) {
        CouponModel couponModel=couponService.getCouponById(idCoupon);
        model.addAttribute("coupon",couponModel);
        model.addAttribute("useDay",useDay);
        model.addAttribute("userList",userService.getUserList());

        return "form-edit-coupon";
    }
    @PostMapping(value = "/edit/{idCoupon}/{useDay}")
    public String bukaCabang(
            @PathVariable(name = "idCoupon") Long idCoupon,
            @PathVariable(name = "useDay") String useDay,
            @ModelAttribute CouponModel coupon,
            Model model
    ){
        System.out.println(coupon.getListCouponType());
        couponService.updateCoupon(coupon,useDay);
        return "home";
    }
}
