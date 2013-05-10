
package org.foi.nwtis.lurajcevi.web.zrna;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.foi.nwtis.lurajcevi.web.slusaci.SlusacAplikacije;

/**
 * Managed bean za postavljanje email postavki
 * Postavljaju se ime posluzitelja, korisnicko ime i lozinka
 * @author Luka Rajcevic
 */
@ManagedBean(name = "emailPostavke")
@SessionScoped
public class EmailPovezivanje implements Serializable {
    
    /*******************************
     * VARIJABLE
     * ****************************
     */
    
    private String emailPosluzitelj = SlusacAplikacije.config.dajPostavku("emailPosluzitelj");
    private String korisnickoIme = SlusacAplikacije.config.dajPostavku("username");
    private String lozinka = SlusacAplikacije.config.dajPostavku("password");
    private boolean nevaljaniPodaci = false;
    private Matcher m;
    private Pattern p;
    private String mailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                             + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    
    /*******************************
     * KONSTRUKTOR
     * ****************************
     */
    
    public EmailPovezivanje() {
        
    }
    
    /********************************
     * POMOĆNE METODE
     * ******************************
     */
   
    /**
     * Metoda koja vrši navigaciju na stranicu za slanje poruke, ukoliko je provjera 
     * podataka uspješna
     * @return "OK" ili null
     */
    public String saljiPoruku(){
        if (verifyInDatabase(korisnickoIme, lozinka)){
            setNevaljaniPodaci(false);
            return "OK";
        } 
        else {
            setNevaljaniPodaci(true);
            return null;
        }
    }
    /**
     * Metoda koja vrši navigaciju na stranicu za citanje poruka, ukoliko je provjera 
     * podataka uspješna
     * @return "OK" ili null
     */
    public String citajPoruke(){
        if (verifyInDatabase(korisnickoIme, lozinka)){
            setNevaljaniPodaci(false);
            return "OK";
        } else{
            setNevaljaniPodaci(true);
            return null;
        }
    }
    
     public void provjeriMail (FacesContext context, UIComponent component, Object value) {
        
        String email = (String) value;
         
        p = Pattern.compile(mailRegex);
        m = p.matcher(email);
        if (!m.matches()){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Email nije ispravan.");
            message.setDetail("Email nije ispravan.");
            context.addMessage(null, message);
            throw new ValidatorException(message);
        }
    }
    
    public void provjeriPrazno (FacesContext context, UIComponent component, Object value) {
        
        String polje = (String) value;

        if (polje.equals("")){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Polje ne smije biti prazno.");
            message.setDetail("Polje ne smije biti prazno.");
            context.addMessage(null, message);
            throw new ValidatorException(message);
        }
    }
    
    /**
     * Provjerava korisnicko ime i lozinku dobivene u parametrima unutar baze
     * @param username korisnicko ime
     * @param password lozinka
     * @return true ili false
     */
    private boolean verifyInDatabase(String username, String password){
        String upit = "SELECT kor_ime, lozinka FROM polaznici";
        String url = SlusacAplikacije.bpKonf.getServer_database() + SlusacAplikacije.bpKonf.getUser_database();
        System.out.println("URL DB: " + url);
        String korisnik = SlusacAplikacije.bpKonf.getUser_username();
        String lozinka = SlusacAplikacije.bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        String uname = username.split("@")[0];
        try{
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery(upit);
        
            while (rs.next()) {
                String ime = rs.getString("kor_ime");
                String pass = rs.getString("lozinka");
                if (ime.equals(uname) && pass.equals(password)){
                    return true;
                }
            }
        } catch(SQLException e){
            if (veza != null){
                veza = null;
            }
            if (instr != null){
                instr = null;
            }
            if (rs != null){
                rs = null;
            }
            e.printStackTrace();
        }
        return false;
    }
    
     /********************************
     * GETTERI I SETTERI
     * ******************************
     */
    
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

    public boolean getNevaljaniPodaci() {
        return nevaljaniPodaci;
    }

    public void setNevaljaniPodaci(boolean nevaljaniPodaci) {
        this.nevaljaniPodaci = nevaljaniPodaci;
    }

  
    
}
