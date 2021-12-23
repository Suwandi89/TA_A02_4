package apap.tugaskelompok.sibusiness.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMesinDTO {
    private List<MesinDTO> result;

    public List<MesinDTO> getResult() {
        return result;
    }

    public void setResult(List<MesinDTO> result) {
        this.result = result;
    }
}
