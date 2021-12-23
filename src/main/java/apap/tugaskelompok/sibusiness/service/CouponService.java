package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.rest.CouponDTO;
import apap.tugaskelompok.sibusiness.rest.Pair;

import java.util.HashMap;
import java.util.List;

public interface CouponService {
    CouponModel getCouponById(Long couponId);
    Pair<List<CouponDTO>,List<String>> getAllCoupon();
    String generateCouponCode(CouponModel coupon,String useDay);
    void updateCoupon(CouponModel coupon,String useDay);
}
