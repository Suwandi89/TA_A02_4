package apap.tugaskelompok.sibusiness.rest;

public class CabangDTO {
    final public static String cabangUrl="http://siretail-a02.herokuapp.com";

    public String namaCabang;
    public String alamatCabang;
    public String noTelpCabang;
    public Integer ukuranCabang;
    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public void setAlamatCabang(String alamatCabang) {
        this.alamatCabang = alamatCabang;
    }

    public void setNoTelpCabang(String noTelpCabang) {
        this.noTelpCabang = noTelpCabang;
    }

    public void setUkuranCabang(Integer ukuranCabang) {
        this.ukuranCabang = ukuranCabang;
    }
}
