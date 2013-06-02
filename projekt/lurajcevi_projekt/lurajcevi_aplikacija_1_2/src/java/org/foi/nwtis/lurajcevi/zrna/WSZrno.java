/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import net.wxbug.api.MeteoPodaci;
import org.foi.nwtis.lurajcevi.ws.klijent.WSKlijent;

/**
 *
 * @author Luka Rajcevic
 */
@Named(value = "wSZrno")
@SessionScoped
public class WSZrno implements Serializable {

    private List<String> zipovi;
    private MeteoPodaci podatak;
    private List<MeteoPodaci> intervalno;
    private List<String> topPodaci;
    private List<MeteoPodaci> posljednjihNZaZip;
    
    /**
     * Creates a new instance of WSZrno
     */
    public WSZrno() {
        WSKlijent wk = new WSKlijent();
        zipovi = wk.dohvatiPodatke();
        podatak = wk.dohvatiMeteoZaZip("90000");
        intervalno = wk.dohvatiPodatkeIntervalno("90000", "2013-06-02 18:05:47.000", "2013-06-02 18:10:47.000");
        topPodaci = wk.dohvatiZipoveSTopPodataka(2);
        posljednjihNZaZip = wk.dohvatiPosljednjihN(9, "90000");
        System.out.println("#####1");
        for (String e : zipovi){
            System.out.println("Z: " + e);
        }
        System.out.println("#####2");
        System.out.println("PODACI: ");
        System.out.println("GRAD: " + podatak.getGrad());
        System.out.println("TEMP: " + podatak.getTemperatura());
        System.out.println("VRIJEME: " + podatak.getDatum());
        System.out.println("#####3");
        for (MeteoPodaci podatak : intervalno){
            System.out.println("GRAD: " + podatak.getGrad());
            System.out.println("TEMP: " + podatak.getTemperatura());
            System.out.println("VRIJEME: " + podatak.getDatum());
        }
        System.out.println("#####4");
        for (MeteoPodaci podatak : posljednjihNZaZip){
            System.out.println("GRAD: " + podatak.getGrad());
            System.out.println("TEMP: " + podatak.getTemperatura());
            System.out.println("VRIJEME: " + podatak.getDatum());
        }
        System.out.println("#####5");
        for (String podatak : topPodaci){
            System.out.println("POD::::   " + podatak);
        }
    }

    
    
    public List<String> getZipovi() {
        return zipovi;
    }

    public void setZipovi(List<String> zipovi) {
        this.zipovi = zipovi;
    }
   
    
}
