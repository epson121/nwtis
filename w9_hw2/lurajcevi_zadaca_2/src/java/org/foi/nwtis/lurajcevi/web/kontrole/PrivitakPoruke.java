
package org.foi.nwtis.lurajcevi.web.kontrole;

/**
 *
 * @author Luka Rajcevic
 */
public class PrivitakPoruke {

    private int broj;
    private String vrsta;
    private int velicina;
    private String datoteka;

    public PrivitakPoruke(int broj, String vrsta, int velicina, String datoteka) {
        this.broj = broj;
        this.vrsta = vrsta;
        this.velicina = velicina;
        this.datoteka = datoteka;
    }

    public int getBroj() {
        return broj;
    }

    public String getDatoteka() {
        return datoteka;
    }

    public int getVelicina() {
        return velicina;
    }

    public String getVrsta() {
        return vrsta;
    }
    
    
}