
package org.foi.nwtis.lurajcevi.web.zrna;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.lurajcevi.rest.klijenti.MeteoRESTKlijent;
import org.foi.nwtis.lurajcevi.ws.klijenti.MeteoWSKlijent;


/**
 *
 * @author Luka Rajcevic
 * backing bean za odabirZipKodova.
 */
@ManagedBean(name = "odabirZipKodova")
@SessionScoped
public class OdabirZipKodova implements Serializable{
    
    /*******************************************
     * VARIJABLE
     ******************************************* 
     */
    private List<String> zipKodovi = new ArrayList<>(); 
    private String zipKodDodaj;
    private List<String> odabraniZipKodovi = new ArrayList<>();
    private String zipKodBrisi;
    private List<LiveWeatherData> meteoWSPodaci = new ArrayList<>();
    private String meteoRESTPodaci;
    private boolean prazno = true;
    private boolean postojiREST = false;
    
     /*******************************************
     * KONSTRUKTOR
     ******************************************* 
     */
    public OdabirZipKodova() {
    }
    
     /*******************************************
     * POMOĆNE METODE
     ******************************************* 
     */
    /**
     * Sluzi za dohvaćanje zip kodova iz mycities tablice. Podaci se spremaju
     * u listu Stringova. Metoda je optimizirana na način da se novi podaci
     * dohvaćaju samo kada dolazi novi zahtjev, a ne prilikom svakog refresha 
     * stranice.
     * @return lista stringova, zipKodovi
     */
    public List<String> getZipKodovi() {
        if (!zipKodovi.isEmpty())
            return zipKodovi;
        BP_Konfiguracija bpKonf = (BP_Konfiguracija) ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getAttribute("BP_Konfiguracija");
        String upit = "SELECT zip FROM mycities";
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        try (
                Connection veza = DriverManager.getConnection(url, korisnik, lozinka);
                Statement instr = veza.createStatement();
                ResultSet rs = instr.executeQuery(upit);
            ) 
            {
            while (rs.next()) {
               String zip = rs.getString("zip");
               zipKodovi.add(zip);
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        return zipKodovi;
    }
    
    /**
     * Metoda koja dodaje odabrani zip kod u listu odabranih zip kodova za koje
     * se traze podaci o meteorološkoj situaciji.
     * @return "" prazan string, ostaje se na istoj stranici
     */
    public String dodajZipKod(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (zipKodDodaj == null){
            context.addMessage(null, new FacesMessage("Niste odabrali kod."));
            return "";
        }
        for (String novi : odabraniZipKodovi){
            if (novi.equals(zipKodDodaj)){
                context.addMessage(null, new FacesMessage("Već ste dodali taj kod!"));
                return "";
            } 
        }
        odabraniZipKodovi.add(zipKodDodaj);
        return "";
    }
    
    /**
     * Metoda koja briše odabrani zip kod iz liste odabranih zip kodova.
     * @return "" prazan string, ostaje se na istoj stranici
     */
    public  String brisiZipKod(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (zipKodBrisi == null){
            context.addMessage(null, new FacesMessage("Niste odabrali kod za brisanje."));
            return "";
        }
        odabraniZipKodovi.remove(zipKodBrisi);
        return "";
    }
    
    /**
     * Dohvaća meteorološke podatke koristeći web servis iz ws.klijenti paketa
     * @return "" ostaje se na istoj stranici
     */
    public String dajMeteoWSPodatke(){
        FacesContext context = FacesContext.getCurrentInstance();
        meteoWSPodaci.clear();
        if (odabraniZipKodovi.size() < 5){
            prazno = true;
            context.addMessage(null, new FacesMessage("Potrebno je minimalno 5 kodova."));
            return "";
        } else if (odabraniZipKodovi.size() > 10){
            prazno = true;
            context.addMessage(null, new FacesMessage("Dozvoljeno je maksimalno 10 kodova."));
            return "";
        } else{
            prazno = false;
        }
        for (String zip : odabraniZipKodovi){
            LiveWeatherData podatak = MeteoWSKlijent.dajMeteoWSPodatkeZaZip(zip);
            meteoWSPodaci.add(podatak);
        }
        return "";
    }
    
    /**
     * Dohvaća meteorološke podatke koristeći REST servis implementiran u 3_1 zadatku
     * @return "" ostaje se na istoj stranici
     */
    public String dajMeteoRESTPodatke() {
        FacesContext context = FacesContext.getCurrentInstance();
         if (zipKodBrisi != null){
            postojiREST = true;
            MeteoRESTKlijent mr = new MeteoRESTKlijent(zipKodBrisi);
            meteoRESTPodaci = mr.getHtml();
        } else{
            postojiREST =  false;
            context.addMessage(null, new FacesMessage("Niste odabrali ZIP kod."));
        }
        return "";
    }
    
    /*******************************************
     * GETTERI I SETTERI
     ******************************************* 
     */
    
    public void setZipKodovi(List<String> zipKodovi) {
        this.zipKodovi = zipKodovi;
    }

    public String getZipKodDodaj() {
        return zipKodDodaj;
    }

    public void setZipKodDodaj(String zipKodDodaj) {
        this.zipKodDodaj = zipKodDodaj;
    }

    public List<String> getOdabraniZipKodovi() {
        return odabraniZipKodovi;
    }

    public void setOdabraniZipKodovi(List<String> odabraniZipKodovi) {
        this.odabraniZipKodovi = odabraniZipKodovi;
    }

    public String getZipKodBrisi() {
        return zipKodBrisi;
    }

    public void setZipKodBrisi(String zipKodBrisi) {
        this.zipKodBrisi = zipKodBrisi;
    }

    public List<LiveWeatherData> getMeteoWSPodaci() {
        return meteoWSPodaci;
    }

    public void setMeteoWSPodaci(List<LiveWeatherData> meteoWSPodaci) {
        this.meteoWSPodaci = meteoWSPodaci;
    }

    public String getMeteoRESTPodaci() {
        return meteoRESTPodaci;
    }

    public void setMeteoRESTPodaci(String meteoRESTPodaci) {
        this.meteoRESTPodaci = meteoRESTPodaci;
    }

    public boolean isPrazno() {
        return prazno;
    }

    public void setPrazno(boolean prazno) {
        this.prazno = prazno;
    }

    public boolean isPostojiREST() {
        return postojiREST;
    }

    public void setPostojiREST(boolean postojiREST) {
        this.postojiREST = postojiREST;
    }
    
    
    
}
