package apap.tugaskelompok.sibusiness.restcontroller;

import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.service.CouponRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class CouponRestController {
    @Autowired
    private CouponRestService couponRestService;

    @GetMapping(value = "/coupon")
    private List<HashMap> retrieveCabang() {
        try {
            Date date=java.util.Calendar.getInstance().getTime();
            String useDay = new SimpleDateFormat("EEEE").format(date);
            return couponRestService.getCouponByDay(useDay);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "UseDay Tidak Memiliki Kupon"
            );
        }
    }
}