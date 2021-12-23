package apap.tugaskelompok.sibusiness.repository;


import apap.tugaskelompok.sibusiness.models.CouponModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponDB extends JpaRepository<CouponModel,Long> {
    Optional<CouponModel> findByIdCoupon(Long idCoupon);

}
