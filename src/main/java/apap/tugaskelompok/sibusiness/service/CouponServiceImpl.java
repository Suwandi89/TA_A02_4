package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.models.CouponTypeModel;
import apap.tugaskelompok.sibusiness.repository.CouponDB;
import apap.tugaskelompok.sibusiness.repository.CouponTypeDB;
import apap.tugaskelompok.sibusiness.rest.CouponDTO;
import apap.tugaskelompok.sibusiness.rest.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class CouponServiceImpl implements CouponService{
    @Autowired
    private CouponDB couponDb;

    @Autowired
    private CouponTypeDB couponTypeDB;

    @Override
    public Pair<List<CouponDTO>,List<String>> getAllCoupon() {
        Iterable<CouponTypeModel> couponTypeModels= couponTypeDB.findAll();
        List<CouponTypeModel> couponTypeModelList=new ArrayList<>();
        couponTypeModels.forEach(couponTypeModelList::add);
        List<CouponDTO> newCouponList=new ArrayList<>();
        List<String> dayUsage=new ArrayList<>();

        for(CouponTypeModel couponTypeModel:couponTypeModelList){
            if(couponTypeModel.getListCoupon().size()!=0){
                for(CouponModel couponModel: couponTypeModel.getListCoupon()){
                    CouponDTO couponDTO=new CouponDTO();
                    couponDTO.setCouponCode(generateCouponCode(couponModel,couponTypeModel.getUseDay()));
                    couponDTO.setIdCoupon(couponModel.getIdCoupon());
                    couponDTO.setCouponName(couponModel.getCouponName());
                    couponDTO.setDiscountAmount(couponModel.getDiscountAmount());
                    couponDTO.setExpiryDate(couponModel.getExpiryDate());
                    couponDTO.setStatus(couponModel.getStatus());
                    couponDTO.setCreator(couponModel.getCreator());
                    newCouponList.add(couponDTO);
                    dayUsage.add(couponTypeModel.getUseDay());
                }
            }
        }

        Pair<List<CouponDTO>,List<String>> pair=new Pair(newCouponList,dayUsage);
        return pair;
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
