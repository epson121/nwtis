
package org.foi.nwtis.lurajcevi.web.zrna;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Luka Rajcevic
 */
@ManagedBean(name = "pregledSvihPoruka")
@SessionScoped
public class PregledSvihPoruka {
    //TODO kreirati varijable za povezivanje na sandučić korisnika, 
    //popis poruka, popis mapa, odabrana mapa, odabrana poruka
    
    private String emailPosluzitelj;
    private String korisnickoIme;
    private String lozinka;
    
    private List<String> popisMapa;
    private String odabranaMapa;
    
    public PregledSvihPoruka() {
        
    }
    
    public String odaberiMapu(){
        return "";
    }
    
    public String pregledPoruke(){
        //TODO pregled izabrane poruke
        //vraca OK, NOT_OK ili ERROR
        return "OK";
    }

    public String getEmailPosluzitelj() {
        return emailPosluzitelj;
    }

    public void setEmailPosluzitelj(String emailPosluzitelj) {
        this.emailPosluzitelj = emailPosluzitelj;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getOdabranaMapa() {
        return odabranaMapa;
    }

    public void setOdabranaMapa(String odabranaMapa) {
        this.odabranaMapa = odabranaMapa;
    }

    public List<String> getPopisMapa() {
        return popisMapa;
    }

    public void setPopisMapa(List<String> popisMapa) {
        popisMapa = new ArrayList<String>();
        popisMapa.add("mapa1");
        popisMapa.add("mapa2");
        popisMapa.add("mapa3");
    }
    
    
    
}
