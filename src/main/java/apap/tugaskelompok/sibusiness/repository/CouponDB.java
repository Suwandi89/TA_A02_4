package apap.tugaskelompok.sibusiness.repository;


import apap.tugaskelompok.sibusiness.models.CouponModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponDB extends JpaRepository<CouponModel,Long> {
    List<CouponModel> findAll();
    List<CouponModel> findAllByStatus(boolean status);
    Optional<CouponModel> findByIdCoupon(Long idCoupon);
}
