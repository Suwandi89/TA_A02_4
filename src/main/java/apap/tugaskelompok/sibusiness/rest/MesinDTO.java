package apap.tugaskelompok.sibusiness.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class MesinDTO {
    private String namaMesin;
    private int idKategori;
    private String tanggalDibuat;
    private int kapasitas;

    public String getNamaMesin() {
        return namaMesin;
    }

    public void setNamaMesin(String namaMesin) {
        this.namaMesin = namaMesin;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public String getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(String tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }
}
