
package org.foi.nwtis.lurajcevi.web.zrna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;

/**
 *
 * @author nwtis_2
 */
@ManagedBean
@SessionScoped
public class OdabirZipKodova {
    
    private List<String> zipKodovi = new ArrayList<>(); 
    private String zipKodDodaj;
    private List<String> odabraniZipKodovi = new ArrayList<>();
    private String zipKodBrisi;
    private List<LiveWeatherData> meteoWSPodaci = new ArrayList<>();
    private String meteoRESTPodaci;
    
    public OdabirZipKodova() {
    }

    public List<String> getZipKodovi() {
        //TODO optimiziraj
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
    
    public String dodajZipKod(){
        //TODO provjeriti da li se dodaje isti ip kod vise puta
        odabraniZipKodovi.add(zipKodDodaj);
        return "";
    }
    
    public  String brisiZipKod(){
        odabraniZipKodovi.remove(zipKodBrisi);
        return "";
    }
    
    
    
}
