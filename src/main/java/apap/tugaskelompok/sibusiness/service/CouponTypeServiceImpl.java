package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.CouponTypeModel;
import apap.tugaskelompok.sibusiness.models.UserModel;
import apap.tugaskelompok.sibusiness.repository.CouponTypeDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CouponTypeServiceImpl implements CouponTypeService {
    @Autowired
    CouponTypeDB couponTypeDB;

    @Override
    public List<CouponTypeModel> getListCouponType(){
        return couponTypeDB.findAll();
    }
}
