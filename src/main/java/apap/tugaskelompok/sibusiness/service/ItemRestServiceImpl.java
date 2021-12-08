package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.rest.ResponseDTO;
import apap.tugaskelompok.sibusiness.rest.ResponseDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemRestServiceImpl implements ItemRestService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseDTO getItemList(int idKategori){
        final String itemUrl="https://si-item.herokuapp.com/api/item/kategori/";
        String urlTarget=itemUrl+Integer.toString(idKategori);
        return restTemplate.getForObject(urlTarget, ResponseDTO.class);
    }

    @Override
    public ResponseDetailDTO getItemDetail(String uuid){
        final String itemUrl="https://si-item.herokuapp.com/api/item/";
        String urlTarget=itemUrl+uuid;
        return restTemplate.getForObject(urlTarget, ResponseDetailDTO.class);
    }
}
