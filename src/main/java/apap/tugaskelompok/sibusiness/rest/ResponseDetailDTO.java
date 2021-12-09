package apap.tugaskelompok.sibusiness.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDetailDTO {
    private ItemDTO result;

    public ItemDTO getResult() {
        return result;
    }

    public void setResult(ItemDTO result) {
        this.result = result;
    }
}
