package apap.tugaskelompok.sibusiness.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO {
    private List<ItemDTO> result;

    public List<ItemDTO> getResult() {
        return result;
    }

    public void setResult(List<ItemDTO> result) {
        this.result = result;
    }
}
