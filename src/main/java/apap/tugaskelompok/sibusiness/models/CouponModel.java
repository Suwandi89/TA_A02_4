package apap.tugaskelompok.sibusiness.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "coupon")
public class CouponModel implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="status", nullable = false)
    private Boolean status;

    @NotNull
    @Size(max = 16)
    @Column(name = "coupon_code", nullable = false)
    private String couponCode;

    @NotNull
    @Size(max = 20)
    @Column(name = "coupon_name", nullable = false)
    private String couponName;

    @NotNull
    @Column(name = "discount_amount", nullable = false)
    private Float discountAmount;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime expiryDate;

}
