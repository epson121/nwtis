
package org.foi.nwtis.lurajcevi.web.zrna;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 
 */
@ManagedBean(name = "emailPostavke")
@SessionScoped
public class EmailPovezivanje {
    
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
