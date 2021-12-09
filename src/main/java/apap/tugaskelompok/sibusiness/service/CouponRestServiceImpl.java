package apap.tugaskelompok.sibusiness.service;


import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.models.CouponTypeModel;
import apap.tugaskelompok.sibusiness.repository.CouponDB;
import apap.tugaskelompok.sibusiness.repository.CouponTypeDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class CouponRestServiceImpl implements CouponRestService{
    @Autowired
    private CouponDB couponDb;

    @Autowired
    private CouponTypeDB couponTypeDb;
    @Override
    public List<HashMap> getCouponByDay(String useDay) {
        Iterable<CouponTypeModel> couponTypeModels= couponTypeDb.findAllByUseDay(useDay);
        List<CouponTypeModel> couponTypes=new ArrayList<>();
        couponTypeModels.forEach(couponTypes::add);

        CouponTypeModel couponType=couponTypes.get(0);
        List<CouponModel> coupons=couponType.getListCoupon();

        List<HashMap> arr=new ArrayList<>();
        for(CouponModel coupon:coupons){
            HashMap<String,String> couponHash=new HashMap<>();
            couponHash.put("ID Coupon", coupon.getIdCoupon().toString());
            couponHash.put("Coupon Code", generateCouponCode(coupon,useDay));
            couponHash.put("Coupon Name", coupon.getCouponName());
            couponHash.put("Discount Amount", coupon.getDiscountAmount().toString());
            couponHash.put("Expiry Date", coupon.getExpiryDate().toString());
            arr.add(couponHash);
        }
        return arr;
    }

    @Override
    public String generateCouponCode(CouponModel coupon,String useDay) {
        String result="DISC";
        Integer disc= Math.round(coupon.getDiscountAmount());
        String discAm=disc.toString();
        if(discAm.length()==1){
            result+="00"+discAm;
        }
        else if(discAm.length()==2){
            result+="0"+discAm;
        }
        else{
            result+=discAm;
        }
        result+= useDay.substring(0,3).toUpperCase(Locale.ROOT);
        result+=coupon.getExpiryDate().format(DateTimeFormatter.ofPattern("ddmmyy"));
        return result;
    }
}
