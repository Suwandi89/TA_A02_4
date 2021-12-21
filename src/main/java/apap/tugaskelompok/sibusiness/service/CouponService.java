package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.rest.CouponDTO;
import apap.tugaskelompok.sibusiness.rest.Pair;

import java.util.HashMap;
import java.util.List;

public interface CouponService {
    Pair<List<CouponDTO>,List<String>> getAllCoupon();
    String generateCouponCode(CouponModel coupon,String useDay);
}
