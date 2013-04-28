
package org.foi.nwtis.lurajcevi.web.zrna;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Luka Rajcevic
 */
@ManagedBean(name = "emailPostavke")
@SessionScoped
public class EmailPovezivanje implements Serializable {
    
    private String emailPosluzitelj;
    private String korisnickoIme;
    private String lozinka;
    
    public EmailPovezivanje() {
    }
    
    public String provjeriPodatke(){
        //TODO provjeriti podatke iz konfiguracijske datoteke
        return "OK";
    }
    
    public String saljiPoruku(){
        return "OK";
    }
    
    public String citajPoruke(){
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
    
}
