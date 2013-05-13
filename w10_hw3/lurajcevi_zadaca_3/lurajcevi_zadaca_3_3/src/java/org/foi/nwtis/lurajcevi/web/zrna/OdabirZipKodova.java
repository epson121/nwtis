
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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.lurajcevi.rest.klijenti.MeteoRESTKlijent;
import org.foi.nwtis.lurajcevi.ws.klijenti.MeteoWSKlijent;


/**
 *
 * @author Luka Rajcevic
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
    
    public List<String> getZipKodovi() {
        //TODO optimiziraj
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
    
    public String dodajZipKod(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (zipKodDodaj == null){
            context.addMessage(null, new FacesMessage("Već ste dodali taj kod!"));
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
    
    public  String brisiZipKod(){
        if (zipKodBrisi == null)
            return "";
        odabraniZipKodovi.remove(zipKodBrisi);
        return "";
    }
    
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
        if (zipKodBrisi != null){
            postojiREST = true;
            dajMeteoRESTPodatke();
        } else{
            postojiREST =  false;
        }
        return "";
    }
    
    public String dajMeteoRESTPodatke() {
        MeteoRESTKlijent mrk = new MeteoRESTKlijent(zipKodBrisi);
        meteoRESTPodaci = mrk.getHtml();
        return "";
    }
    
    public void provjeriZip(FacesContext context, UIComponent component, Object value) {
        
        if (odabraniZipKodovi.size() < 5){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Potrebno je minimalno 5 zip kodova.");
            message.setDetail("Potrebno je minimalno 5 zip kodova.");
            context.addMessage(null, message);
            throw new ValidatorException(message);
            
        } else if (odabraniZipKodovi.size() > 7){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Dozvoljeno je maksimalno 7 zip kodova.");
            message.setDetail("Dozvoljeno je maksimalno 7 zip kodova.");
            context.addMessage(null, message);
            throw new ValidatorException(message);
        }
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
