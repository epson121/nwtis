
package org.foi.nwtis.lurajcevi.zrna;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @document Lokalizacija
 * @author Luka Rajcevic
 */
@ManagedBean(name = "lokalizacija")
@SessionScoped
public class Lokalizacija implements Serializable {
    
    /*******************************
     * VARIJABLE
     * ****************************
     */
    private Map<String, Object> jezici;
    private String odabraniJezik;
    private Locale odabraniLocale;
   
    
    /*******************************
     * KONSTRUKTOR
     * ****************************
     */
    
     /**
      * Konstruktor lokalizacije
      */
    public Lokalizacija() {
        jezici = new HashMap<String, Object>();
        jezici.put("Hrvatski", new Locale("hr"));
        jezici.put("English", Locale.ENGLISH);
    }
    
     /********************************
     * POMOĆNE METODE
     * ******************************
     */
    
    /**
     * Metoda koja se poziva nakon odabira jezika iz ponude radio gumbova
     * postavlja lokalizaciju na razini cijele aplikacije (na razini sesije)
     * @return "OK"
     * @throws IOException 
     */
    public Object odaberiJezik() throws IOException {
        for (Map.Entry<String, Object> entry : jezici.entrySet()) {
            if (entry.getValue().toString().equals(odabraniJezik)) {
                setOdabraniLocale((Locale) entry.getValue());
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
        return "OK";
    }
    
     /********************************
     * GETTERI I SETTERI
     * ******************************
     */
    
    public Map<String, Object> getJezici() {
        return jezici;
    }

    public void setJezici(Map<String, Object> jezici) {
        this.jezici = jezici;
    }

    public String getOdabraniJezik() {
        return odabraniJezik;
    }

    public void setOdabraniJezik(String odabraniJezik) {
        this.odabraniJezik = odabraniJezik;
    }

    public Locale getOdabraniLocale() {
        return odabraniLocale;
    }

    public void setOdabraniLocale(Locale odabraniLocale) {
        this.odabraniLocale = odabraniLocale;
    }
    
}
