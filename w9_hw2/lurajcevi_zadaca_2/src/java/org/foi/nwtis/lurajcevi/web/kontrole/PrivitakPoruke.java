
package org.foi.nwtis.lurajcevi.web.kontrole;

/**
 * klasa koja predstavlja entitet privitakPoruke
 * @author Luka Rajcevic
 */
public class PrivitakPoruke {
     /*******************************
     * VARIJABLE
     * ****************************
     */
    private int broj;
    private String vrsta;
    private int velicina;
    private String datoteka;
    
    
    /*******************************
     * KONSTRUKTOR
     * ****************************
     */
    
    /**
     * konstruktor klase privitakPoruke
     * @param broj - broj privitaka
     * @param vrsta - vrsta privitka
     * @param velicina - velicina privitka
     * @param datoteka - naziv datoteke
     */
    public PrivitakPoruke(int broj, String vrsta, int velicina, String datoteka) {
        this.broj = broj;
        this.vrsta = vrsta;
        this.velicina = velicina;
        this.datoteka = datoteka;
    }
    
    /********************************
     * GETTERI I SETTERI
     * ******************************
     */
    
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
