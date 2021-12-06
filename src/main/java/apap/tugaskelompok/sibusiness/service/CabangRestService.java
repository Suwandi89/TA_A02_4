package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.CouponModel;
import apap.tugaskelompok.sibusiness.rest.CabangDTO;
import reactor.core.publisher.Mono;

public interface CabangRestService {
    Mono<String> addCabangRequest(CabangDTO cabangDummy);
}
