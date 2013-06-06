/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.foi.nwtis.lurajcevi.ws.MeteoPodaci;
import org.foi.nwtis.lurajcevi.ws.WSKlijent;

/**
 *
 * @author Luka Rajcevic
 */
@Named(value = "zipKodoviPodaci")
@SessionScoped
public class ZipKodoviPodaci implements Serializable {

    private List<String> aktivniZipKodovi;
    
    private List<MeteoPodaci> najnovijiPodaci;
   
          
    
    /**
     * Creates a new instance of ZipKodoviPodaci
     */
    public ZipKodoviPodaci() {        
    }
    
    /********************************
     * POMOĆNE METODE
     * ******************************
     */
    
    public String dohvatiNajnovijePodatkeZaZip(){
        return "OK";
    }

    
    /********************************
     * GETTERI I SETTERI
     * ******************************
     */
    
    public List<String> getAktivniZipKodovi() {
        if (aktivniZipKodovi != null)
            aktivniZipKodovi.clear();
        aktivniZipKodovi = WSKlijent.dohvatiAktivneZipove();
        return aktivniZipKodovi;
    }

    public void setAktivniZipKodovi(List<String> aktivniZipKodovi) {
        this.aktivniZipKodovi = aktivniZipKodovi;
    }

    public List<MeteoPodaci> getNajnovijiPodaci() {
        List<String> kodovi = getAktivniZipKodovi();
        if (najnovijiPodaci == null)
            najnovijiPodaci = new ArrayList<MeteoPodaci>();
        najnovijiPodaci.clear();
        for (String s : kodovi){
            najnovijiPodaci.add(WSKlijent.dohvatiPodatkeOZipKodu(s));
        }
        return najnovijiPodaci;
    }

    public void setNajnovijiPodaci(List<MeteoPodaci> najnovijiPodaci) {
        this.najnovijiPodaci = najnovijiPodaci;
    }
    
    
    
}
