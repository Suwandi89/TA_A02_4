package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.rest.CabangDTO;
import reactor.core.publisher.Mono;

import java.util.HashMap;

public interface CabangRestService {
    HashMap addCabangRequest(CabangDTO cabangDummy);
}
