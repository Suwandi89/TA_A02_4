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

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class CouponRestController {
    @Autowired
    private CouponRestService couponRestService;

    @GetMapping(value = "/coupon/{useDay}")
    private List<HashMap> retrieveCabang(@PathVariable("useDay") String useDay) {
        try {
            return couponRestService.getCouponByDay(useDay);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "UseDay" + useDay + " Tidak Memiliki Kupon"
            );
        }
    }
}