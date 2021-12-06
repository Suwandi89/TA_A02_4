package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.CouponModel;

import java.util.HashMap;
import java.util.List;

public interface CouponRestService {
    List<HashMap> getCouponByDay(String useDay);
    String generateCouponCode(CouponModel coupon, String useDay);
}
