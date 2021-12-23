package apap.tugaskelompok.sibusiness.repository;

import apap.tugaskelompok.sibusiness.models.CouponTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponTypeDB extends JpaRepository<CouponTypeModel,Long> {
    Iterable<CouponTypeModel> findAllByUseDay(String useDay);
}
