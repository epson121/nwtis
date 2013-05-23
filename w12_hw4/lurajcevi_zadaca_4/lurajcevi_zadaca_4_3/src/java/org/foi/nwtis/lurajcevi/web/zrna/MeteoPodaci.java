/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.web.zrna;

/**
 * @document MeteoPodaci
 * @author Luka Rajcevic
 */
public class MeteoPodaci {
    
     /**
     * *****************************************
     * VARIJABLE 
     ******************************************
     */
    private String zipKod;
    private String grad;
    private String Temperatura;
    private String Vlaga;
    private double duzina;
    private double sirina;
    
     /**
     * *****************************************
     * KONSTRUKTOR 
     ******************************************
     */
    
    /**
     * Konstruktor klase. Kreira novi objekt tipa MeteoPodaci
     * @param zipKod - vrijednost zip koda za meteo podatak
     * @param grad - vrijednost grada za meteo podatak
     * @param Temperatura - vrijednost temp. meteo podatka
     * @param Vlaga - vrijedost vlage za meteo podatak
     * @param duzina - vrijednost geo. duzine za meteo podatak
     * @param sirina - vrijednost geo. sirine za meteo podatak
     */
    public MeteoPodaci(String zipKod, String grad, String Temperatura, String Vlaga, double duzina, double sirina) {
        this.zipKod = zipKod;
        this.grad = grad;
        this.Temperatura = Temperatura;
        this.Vlaga = Vlaga;
        this.duzina = duzina;
        this.sirina = sirina;
    }
    
     /**
     * *****************************************
     * GETTERI I SETTERI 
     ******************************************
     */
    public String getZipKod() {
        return zipKod;
    }

    public void setZipKod(String zipKod) {
        this.zipKod = zipKod;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(String Temperatura) {
        this.Temperatura = Temperatura;
    }

    public String getVlaga() {
        return Vlaga;
    }

    public void setVlaga(String Vlaga) {
        this.Vlaga = Vlaga;
    }

    public double getDuzina() {
        return duzina;
    }

    public void setDuzina(double duzina) {
        this.duzina = duzina;
    }

    public double getSirina() {
        return sirina;
    }

    public void setSirina(double sirina) {
        this.sirina = sirina;
    }
    
    

}
