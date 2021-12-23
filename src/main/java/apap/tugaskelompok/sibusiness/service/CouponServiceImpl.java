package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.models.CouponTypeModel;
import apap.tugaskelompok.sibusiness.repository.CouponDB;
import apap.tugaskelompok.sibusiness.repository.CouponTypeDB;
import apap.tugaskelompok.sibusiness.rest.CouponDTO;
import apap.tugaskelompok.sibusiness.rest.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class CouponServiceImpl implements CouponService{
    @Autowired
    private CouponDB couponDb;

    @Autowired
    private CouponTypeDB couponTypeDB;

    @Override
    public CouponModel getCouponById(Long couponId) {
        Optional<CouponModel> coupon=couponDb.findByIdCoupon(couponId);
        if(coupon.isPresent()){
            CouponModel couponModel=coupon.get();
            return couponModel;
        }
        return null;
    }

    @Override
    public Pair<List<CouponDTO>,List<String>> getAllCoupon() {
        Iterable<CouponTypeModel> couponTypeModels= couponTypeDB.findAll();
        List<CouponTypeModel> couponTypeModelList=new ArrayList<>();
        couponTypeModels.forEach(couponTypeModelList::add);
        List<CouponDTO> newCouponList=new ArrayList<>();
        List<String> dayUsage=new ArrayList<>();

        for(CouponTypeModel couponTypeModel:couponTypeModelList){
            System.out.println(couponTypeModel.getUseDay());

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

    @Override
    public  void addCoupon(CouponModel coupon){
        couponDb.save(coupon);
        System.out.println(coupon.getListCouponType());
    }

    @Override
    public CouponModel acceptCoupon(Long id){
        CouponModel coupon = couponDb.getById(id);
        coupon.setStatus(true);
        return coupon;
    }

    @Override
    public void rejectCoupon(Long id) {
        couponDb.deleteById(id);
    }
    public void updateCoupon(CouponModel coupon, String useDay) {
        if(coupon.getListCouponType().size()==1){
            couponDb.save(coupon);
            return;
        }
        Iterable<CouponTypeModel> couponTypeModels= couponTypeDB.findAllByUseDay(useDay);
        List<CouponTypeModel> couponTypes=new ArrayList<>();
        couponTypeModels.forEach(couponTypes::add);

        CouponTypeModel couponType=couponTypes.get(0);
        CouponModel oldCoupon=getCouponById(coupon.getIdCoupon());

        oldCoupon.getListCouponType().remove(couponType);
        CouponModel newCoupon=new CouponModel();
        newCoupon.setCouponName(coupon.getCouponName());
        newCoupon.setCreator(oldCoupon.getCreator());
        System.out.println(newCoupon.getCreator().getUsername());
        newCoupon.setDiscountAmount(coupon.getDiscountAmount());
        newCoupon.setStatus(coupon.getStatus());
        newCoupon.setExpiryDate(coupon.getExpiryDate());
        List<CouponTypeModel> listCouponType=new ArrayList<>();
        listCouponType.add(couponType);
        newCoupon.setListCouponType(listCouponType);

        couponType.getListCoupon().remove(oldCoupon);
        couponType.getListCoupon().add(newCoupon);
        couponTypeDB.save(couponType);
        couponDb.save(newCoupon);

    }

    @Override
    public void deleteCoupon(CouponModel coupon, String useDay){
        Iterable<CouponTypeModel> couponTypeModels= couponTypeDB.findAllByUseDay(useDay);
        List<CouponTypeModel> couponTypes=new ArrayList<>();
        couponTypeModels.forEach(couponTypes::add);

        CouponTypeModel couponType=couponTypes.get(0);

        couponType.getListCoupon().remove(coupon);
        coupon.getListCouponType().remove(couponType);
        if (coupon.getListCouponType().size()==0) {
            couponDb.delete(coupon);
        }

    }
}
