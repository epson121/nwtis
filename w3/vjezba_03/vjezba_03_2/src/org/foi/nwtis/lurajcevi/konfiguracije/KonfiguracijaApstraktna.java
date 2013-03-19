/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.konfiguracije;

import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author nwtis_2
 */
public abstract class KonfiguracijaApstraktna implements Konfiguracija {
    
    protected String datoteka;
    protected Properties postavke;

    public KonfiguracijaApstraktna(String datoteka) {
        this.datoteka = datoteka;
        this.postavke = new Properties();
    }
    
    @Override
    public void dodajKonfiguraciju(Properties postavke) {
        postavke.putAll(postavke);
    }

    @Override
    public void dodajKonfiguraciju(Konfiguracija konfig) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void kopirajKonfiguraciju(Properties postavke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void kopirajKonfiguraciju(Konfiguracija konfig) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Properties dajSvePostavke() {
        return postavke;
    }

    @Override
    public Enumeration dajPostavke() {
        return postavke.propertyNames();
    }

    @Override
    public boolean obrisiSvePostavke() {
        if (postavke.isEmpty())
            return false;
        else{
            postavke.clear();
            return true;
    }   }

    @Override
    public String dajPostavku(String postavka) {
        return postavke.getProperty(postavka);
    }

    @Override
    public boolean spremiPostavku(String postavka, String vrijednost) {
        if (!postojiPostavka(postavka)){
            postavke.setProperty(postavka, vrijednost);
            return true;
        }
        return false;
    }

    @Override
    public boolean azurirajPostavku(String postavka, String vrijednost) {
        if (postojiPostavka(postavka)){
            postavke.setProperty(postavka, vrijednost);
            return true;
        }
        return false;
    }

    @Override
    public boolean postojiPostavka(String postavka) {
        return postavke.containsKey(postavka);
    }

    @Override
    public boolean obrisiPostavku(String postavka) {
        if (postojiPostavka(postavka)){
            postavke.remove(postavka);
            return true;
        }
        return false;
    }
    
    public static Konfiguracija kreirajKonfiguraciju(String datoteka) throws NeispravnaKonfiguracija{
        Konfiguracija konfig = null;
        if (datoteka.endsWith(".txt")){
            konfig = new KonfiguracijaTxt(datoteka);
            konfig.spremiKonfiguraciju();
        }
        else if(datoteka.endsWith(".xml")){
            konfig = new KonfiguracijaXML(datoteka);
            konfig.spremiKonfiguraciju();
        }
        else{
            throw new NeispravnaKonfiguracija("Nepoznat tip datoteke.");
        }
        return konfig;
    }
            
    public static Konfiguracija preuzmiKonfiguraciju(String datoteka) throws NemaKonfiguracije{
        Konfiguracija konfig = null;
        if (datoteka.endsWith(".txt")){
            konfig = new KonfiguracijaTxt(datoteka);
            konfig.ucitajKonfiguraciju();
        }
        else if(datoteka.endsWith(".xml")){
            konfig = new KonfiguracijaXML(datoteka);
            konfig.ucitajKonfiguraciju();
        }
        else{
            throw new NemaKonfiguracije("Nepoznat tip datoteke.");
        }
        return konfig;
    }
    
}
