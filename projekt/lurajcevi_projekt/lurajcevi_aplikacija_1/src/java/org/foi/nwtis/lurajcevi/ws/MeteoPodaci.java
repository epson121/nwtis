
package org.foi.nwtis.lurajcevi.ws;

import java.io.Serializable;

/**
 * @document MeteoPodaci
 * @author Luka Rajcevic
 */
public class MeteoPodaci implements Serializable {
    
    private String zip_trazeni;
    private String zip_vraceni;
    private String temperatura;
    private String vlaga;
    private String geo_duzina;
    private String geo_sirina;
    private String grad;
    private String vjetar;
    private String tlak;
    private String datum;

    public MeteoPodaci(String zip_trazeni, String zip_vraceni, String temperatura, String vlaga, String geo_duzina, String geo_sirina, String grad, String vjetar, String tlak, String datum) {
        this.zip_trazeni = zip_trazeni;
        this.zip_vraceni = zip_vraceni;
        this.temperatura = temperatura;
        this.vlaga = vlaga;
        this.geo_duzina = geo_duzina;
        this.geo_sirina = geo_sirina;
        this.grad = grad;
        this.vjetar = vjetar;
        this.tlak = tlak;
        this.datum = datum;
    }
    
    

    public String getZip_trazeni() {
        return zip_trazeni;
    }

    public void setZip_trazeni(String zip_trazeni) {
        this.zip_trazeni = zip_trazeni;
    }

    public String getZip_vraceni() {
        return zip_vraceni;
    }

    public void setZip_vraceni(String zip_vraceni) {
        this.zip_vraceni = zip_vraceni;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getVlaga() {
        return vlaga;
    }

    public void setVlaga(String vlaga) {
        this.vlaga = vlaga;
    }

    public String getGeo_duzina() {
        return geo_duzina;
    }

    public void setGeo_duzina(String geo_duzina) {
        this.geo_duzina = geo_duzina;
    }

    public String getGeo_sirina() {
        return geo_sirina;
    }

    public void setGeo_sirina(String geo_sirina) {
        this.geo_sirina = geo_sirina;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getVjetar() {
        return vjetar;
    }

    public void setVjetar(String vjetar) {
        this.vjetar = vjetar;
    }

    public String getTlak() {
        return tlak;
    }

    public void setTlak(String tlak) {
        this.tlak = tlak;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
    
}
