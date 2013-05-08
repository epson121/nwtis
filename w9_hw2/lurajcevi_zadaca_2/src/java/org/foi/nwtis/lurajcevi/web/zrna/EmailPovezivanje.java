
package org.foi.nwtis.lurajcevi.web.zrna;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.foi.nwtis.lurajcevi.web.slusaci.SlusacAplikacije;

/**
 *
 * @author Luka Rajcevic
 */
@ManagedBean(name = "emailPostavke")
@SessionScoped
public class EmailPovezivanje implements Serializable {
    
    private String emailPosluzitelj = SlusacAplikacije.config.dajPostavku("emailPosluzitelj");
    private String korisnickoIme = SlusacAplikacije.config.dajPostavku("username");
    private String lozinka = SlusacAplikacije.config.dajPostavku("password");
    
    public EmailPovezivanje() {
        
    }
    
    public boolean provjeriPodatke(){
        System.out.println("EP: " + getEmailPosluzitelj());
        if (   getEmailPosluzitelj().equals(SlusacAplikacije.config.dajPostavku("emailPosluzitelj")) 
            && getKorisnickoIme().equals(SlusacAplikacije.config.dajPostavku("username"))
            && getLozinka().equals(SlusacAplikacije.config.dajPostavku("password"))){
                return true;
        } else{
            return false;
        }
    }
    
    public String saljiPoruku(){
        if (provjeriPodatke()){
            return "OK";
        } else{
            return "NOT_OK";
        }
    }
    
    public String citajPoruke(){
        if (provjeriPodatke()){
            return "OK";
        } else{
            return "NOT_OK";
        }
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
