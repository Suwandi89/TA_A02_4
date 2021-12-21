package apap.tugaskelompok.sibusiness.controller;

import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.rest.CouponDTO;
import apap.tugaskelompok.sibusiness.rest.ItemDTO;
import apap.tugaskelompok.sibusiness.rest.Pair;
import apap.tugaskelompok.sibusiness.service.CouponService;
import apap.tugaskelompok.sibusiness.service.ItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping(value = "/list-coupon")
    private String getAllKupon(
            Model model) {
        Pair<List<CouponDTO>, List<String>> pair=couponService.getAllCoupon();
        List<CouponDTO> listCoupon=pair.key;
        List<String> useDay=pair.value;
        model.addAttribute("listCoupon",listCoupon);
        model.addAttribute("useDay",useDay);

        return "list-kupon";
    }
}
