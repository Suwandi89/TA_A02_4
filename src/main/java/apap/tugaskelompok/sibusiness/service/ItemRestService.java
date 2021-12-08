package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.rest.ResponseDTO;
import apap.tugaskelompok.sibusiness.rest.ResponseDetailDTO;

public interface ItemRestService {
    ResponseDTO getItemList(int idKategori);
    ResponseDetailDTO getItemDetail(String uuid);
}
