package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.rest.CabangDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.HashMap;

@Service
@Transactional
public class CabangRestServiceImpl implements CabangRestService{
    private final WebClient webClient;
    public CabangRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.baseUrl(CabangDTO.cabangUrl).build();
    }

    @Override
    public HashMap addCabangRequest(CabangDTO cabangDummy) {
        return this.webClient.post().uri("/api/cabang").syncBody(cabangDummy).retrieve().bodyToMono(HashMap.class).block();
    }
}
