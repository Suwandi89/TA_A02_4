package apap.tugaskelompok.sibusiness.controller;

import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.models.CouponTypeModel;
import apap.tugaskelompok.sibusiness.models.UserModel;
import apap.tugaskelompok.sibusiness.repository.CouponDB;
import apap.tugaskelompok.sibusiness.repository.CouponTypeDB;
import apap.tugaskelompok.sibusiness.repository.UserDB;
import apap.tugaskelompok.sibusiness.rest.CabangDTO;
import apap.tugaskelompok.sibusiness.rest.CouponDTO;
import apap.tugaskelompok.sibusiness.rest.ItemDTO;
import apap.tugaskelompok.sibusiness.rest.Pair;
import apap.tugaskelompok.sibusiness.service.CouponService;
import apap.tugaskelompok.sibusiness.service.CouponTypeService;
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
    UserService userService;

    @Autowired
    UserDB userDB;

    @Autowired
    CouponDB couponDB;

    @Autowired
    CouponTypeDB couponTypeDB;

    @Autowired
    CouponTypeService couponTypeService;

    @GetMapping(value = "/list-coupon")
    private String getAllKupon(
            Model model) {
        Pair<List<CouponDTO>, List<String>> pair=couponService.getAllCoupon();
        List<CouponDTO> listCoupon=pair.key;
        List<String> useDay=pair.value;
        List<CouponModel> kuponList = couponDB.findAllByStatus(true);
        model.addAttribute("listCoupon",listCoupon);
        model.addAttribute("useDay",useDay);
        model.addAttribute("listCoupon",kuponList);

        return "list-kupon";
    }

    //  Add coupon get
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    private String addCouponFormPage(Model model){
        CouponModel coupon = new CouponModel();
        List<CouponTypeModel> listCouponType = couponTypeService.getListCouponType();
        model.addAttribute("listCouponType", listCouponType);
        model.addAttribute("coupon", coupon);
        return "form-add-coupon";
    }

    // Add coupon post
    @PostMapping(value = "/add")
    private String addCouponSubmit(@ModelAttribute CouponModel coupon, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userService.getUserByUsername(auth.getName());
        coupon.setCreator(user);

        if(auth.getAuthorities().toString().contains("[Staff_Marketing]")){
            coupon.setStatus(true);
        } else if (auth.getAuthorities().toString().equalsIgnoreCase("[Staff_Product]")){
            coupon.setStatus(false);
        }

        couponService.addCoupon(coupon);

        model.addAttribute("coupon", coupon);

        return "add-coupon-berhasil";
    }

    @GetMapping("/request")
    public String listRequest(Model model){
        List<CouponModel> listCoupon = couponDB.findAllByStatus(false);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userService.getUserByUsername(auth.getName());
        model.addAttribute("listAll", listCoupon);
        model.addAttribute("role", user.getRole());
        return "viewall-couponRequest";
    }

    @GetMapping("/accept/{id}")
    public String approveCoupon(
            @PathVariable Long id,
            Model model
    ){
        couponService.acceptCoupon(id);
        List<CouponModel> listCoupon = couponDB.findAllByStatus(false);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userService.getUserByUsername(auth.getName());
        model.addAttribute("listAll", listCoupon);
        model.addAttribute("role", user.getRole());
        return "viewall-couponRequest";
    }

    @GetMapping("/reject/{id}")
    public String rejectCoupon(
            @PathVariable Long id,
            Model model
    ){
        couponService.rejectCoupon(id);
        List<CouponModel> listCoupon = couponDB.findAllByStatus(false);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userService.getUserByUsername(auth.getName());
        model.addAttribute("listAll", listCoupon);
        model.addAttribute("role", user.getRole());
        return "viewall-couponRequest";
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

    @GetMapping(value = "/delete/{idCoupon}/{useDay}")
    public String deleteCoupon(
            @PathVariable(name = "idCoupon") Long idCoupon,
            @PathVariable(name = "useDay") String useD,
            Model model
    ){
        CouponModel coupon = couponService.getCouponById(idCoupon);
        couponService.deleteCoupon(coupon, useD);
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
}
