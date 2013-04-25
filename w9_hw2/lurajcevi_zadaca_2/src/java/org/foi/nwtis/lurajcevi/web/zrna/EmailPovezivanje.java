/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web.zrna;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 
 */
@ManagedBean
@SessionScoped
public class EmailPovezivanje {
    
    private String emailPosluzitelj;
    private String korisnickoIme;
    private String lozinka;
    
    public EmailPovezivanje() {
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
