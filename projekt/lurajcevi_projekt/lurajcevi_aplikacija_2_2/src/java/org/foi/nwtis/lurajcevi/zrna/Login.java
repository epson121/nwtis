/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.zrna;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviKorisniciFacade;

/**
 *
 * @author Luka Rajcevic
 */
@ManagedBean(name = "login")
@SessionScoped
public class Login implements Serializable {
    @EJB
    private LurajceviKorisniciFacade lurajceviKorisniciFacade;

    private String korisnickoIme;
    private String lozinka;
    
    private HttpSession session;
    private FacesContext context;
    private boolean uspjesno = true;
    
    /**
     * Creates a new instance of Login
     */
    public Login() {
        context = FacesContext.getCurrentInstance();
        session = (HttpSession)(context.getExternalContext().getSession(true)); 
    }

    /********************************
     * POMOĆNE METODE
     * ******************************
     */
    
    public String prijava(){
        if (session.getAttribute("korisnik") != null)
            return "NOT OK";
        if (lurajceviKorisniciFacade.provjeriKorisnika(korisnickoIme)){
                session.setAttribute("korisnik", korisnickoIme);
        }
        if (lurajceviKorisniciFacade.provjeriAdministratora(korisnickoIme)){
            session.setAttribute("admin", true);
        }
        return "OK";
    }
    
     /**
     * Sluzi za provjeru valjanosti unosa u polje. Ukoliko je unos prazan, 
     * ispisuje se poruka o grešci. Kao argumente prima reference na objekt iz forme
     * i vrijednost unesenog polja
     * @param context
     * @param component
     * @param value 
     */
    public void provjeriPrazno (FacesContext context, UIComponent component, Object value) {
        Locale l = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        String polje = (String) value;
        System.out.println("POLJE: " + polje);
        if (polje.equals("")){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            if (l.equals(Locale.ENGLISH)){
                message.setSummary("Field can not be empty.");
                message.setDetail("Field can not be empty.");
            } else {
                message.setSummary("Polje ne smije biti prazno.");
                message.setDetail("Polje ne smije biti prazno.");
            }
            context.addMessage(null, message);
            throw new ValidatorException(message);
        }
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

    public boolean isUspjesno() {
        return uspjesno;
    }

    public void setUspjesno(boolean uspjesno) {
        this.uspjesno = uspjesno;
    }
    
    
}
