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
import java.util.TreeMap;
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
    
    
    //najnoviji Meteo podaci
    private TreeMap<String, String> popisGradova;
    private TreeMap<String, String> popisGradovaOdabrano;
    private List<String> gradovi;
    private List<String> gradoviBrisanje;
    private boolean odabrano = false;
    
    
    private List<MeteoPodaci> najnovijiPodaci = new ArrayList<MeteoPodaci>();
    private List<MeteoPodaci> najnovijiPodaciOdabrano = new ArrayList<MeteoPodaci>();
    
    private int pocetak = 0, stranicenje = 3;
   
    //TODO straničenje i filtriranje AJAX
    
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
    
    /**
     * Metoda za dohvaćanje prethodne stranice u popisu poruka
     * @return ""
     */
    public String prethodnaStranica(){
        if (pocetak - stranicenje <= 0){
            pocetak = 0;
        }
        else{
            pocetak = pocetak - stranicenje;
        }
        return "";
    }
    
    /**
     * Metoda za dohvaćanje slijedeće stranice u popisu poruka
     * @return ""
     */
    public String slijedecaStranica(){
        if (popisGradovaOdabrano != null){
            if (pocetak + stranicenje < popisGradovaOdabrano.size()){
                pocetak += stranicenje;   
            }
        }
        return "";
    }
    
    /**
     * Dodaje odabrane gradove u novu listu (odabraniGradovi)
     * @return vraća se na istu stranicu
     */
    public String dodajGrad() {
        if (gradovi == null || gradovi.isEmpty()) {
            return "";
        }
        if (popisGradovaOdabrano == null) {
            popisGradovaOdabrano = new TreeMap<String, String>();
        }
        for (String g : gradovi) {
            if (!popisGradovaOdabrano.containsKey(g))
                popisGradovaOdabrano.put(g, g);
        }
        return "";
    }
    
    /**
     * brise selektirane gradove iz popisa odabranih gradova
     * @return vraća se na istu stranicu
     */
    public String obrisiGrad() {
        if (gradoviBrisanje != null) {
            for (String g : gradoviBrisanje) {
                popisGradovaOdabrano.remove(g);
            }
        }
        return "";
    }
    
    public String dohvatiMeteoPodatke(){
        pocetak = 0;
        stranicenje = 3;
        return "";
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

    public TreeMap<String, String> getPopisGradova() {
        popisGradova = new TreeMap<String, String>();
        List<MeteoPodaci> podaci = getNajnovijiPodaci();
        for (MeteoPodaci mp : podaci){
            popisGradova.put(mp.getGrad(), mp.getGrad());
        }
        return popisGradova;
    }

    public void setPopisGradova(TreeMap<String, String> popisGradova) {
        this.popisGradova = popisGradova;
    }

    public TreeMap<String, String> getPopisGradovaOdabrano() {
        return popisGradovaOdabrano;
    }

    public void setPopisGradovaOdabrano(TreeMap<String, String> popisGradovaOdabrano) {
        this.popisGradovaOdabrano = popisGradovaOdabrano;
    }

    public List<String> getGradovi() {
        return gradovi;
    }

    public void setGradovi(List<String> gradovi) {
        this.gradovi = gradovi;
    }

    public List<String> getGradoviBrisanje() {
        return gradoviBrisanje;
    }

    public void setGradoviBrisanje(List<String> gradoviBrisanje) {
        this.gradoviBrisanje = gradoviBrisanje;
    }

    public boolean isOdabrano() {
        return odabrano;
    }

    public void setOdabrano(boolean odabrano) {
        this.odabrano = odabrano;
    }

    public List<MeteoPodaci> getNajnovijiPodaciOdabrano() {
        najnovijiPodaciOdabrano = new ArrayList<MeteoPodaci>();  
        if (popisGradovaOdabrano == null )
            return najnovijiPodaciOdabrano;
        for (MeteoPodaci mp : najnovijiPodaci){            
            if (popisGradovaOdabrano.containsKey(mp.getGrad())){
                najnovijiPodaciOdabrano.add(mp);
            }
        }
        if (najnovijiPodaciOdabrano.size() > 0)
            odabrano = true;
        else
            odabrano = false;
        int korak = pocetak + stranicenje;
        if (korak > najnovijiPodaciOdabrano.size())
            korak = najnovijiPodaciOdabrano.size();
        return najnovijiPodaciOdabrano.subList(pocetak, korak);
    }

    public void setNajnovijiPodaciOdabrano(List<MeteoPodaci> najnovijiPodaciOdabrano) {
        this.najnovijiPodaciOdabrano = najnovijiPodaciOdabrano;
    }
    
}
