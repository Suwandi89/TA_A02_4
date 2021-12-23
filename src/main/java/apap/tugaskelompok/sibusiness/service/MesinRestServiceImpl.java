package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.rest.MesinDTO;
import apap.tugaskelompok.sibusiness.rest.ResponseMesinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MesinRestServiceImpl implements MesinRestService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseMesinDTO getMesinList(){
        final String mesinUrl="https://a02-5-sifactory.herokuapp.com/api/mesin";
        return restTemplate.getForObject(mesinUrl, ResponseMesinDTO.class);
    }

}
