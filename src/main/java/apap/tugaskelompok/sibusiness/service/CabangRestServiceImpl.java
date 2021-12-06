package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.rest.CabangDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class CabangRestServiceImpl implements CabangRestService{
    private final WebClient webClient;
    public CabangRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.baseUrl(CabangDTO.cabangUrl).build();
    }

    @Override
    public Mono<String> addCabangRequest(CabangDTO cabangDummy) {
        return this.webClient.post().uri("/rest/cabang/full").syncBody(cabangDummy).retrieve().bodyToMono(String.class);
    }
}
