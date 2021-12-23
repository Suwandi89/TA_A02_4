package apap.tugaskelompok.sibusiness.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "coupon_type")
public class CouponTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCoupontype;

    @NotNull
    @Size(max = 50)
    @Column(name = "use_day", nullable = false)
    private String useDay;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "coupon_coupontype",
            joinColumns = @JoinColumn(name = "id_coupontype"),
            inverseJoinColumns = @JoinColumn(name= "id_coupon"))
    List<CouponModel> listCoupon;
}
