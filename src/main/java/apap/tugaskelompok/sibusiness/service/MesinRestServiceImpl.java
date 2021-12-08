package apap.tugaskelompok.sibusiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
public class MesinRestServiceImpl implements MesinRestService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object getMesinList(){
        final String mesinUrl="https://4590e8aa-1477-4a66-8aee-85f0e49d6320.mock.pstmn.io/rest/list_mesin";
        return restTemplate.getForObject(mesinUrl, Object.class);
    }

}
