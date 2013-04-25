/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web.zrna;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.ejb.Local;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author asd
 */
@ManagedBean(name = "lokalizacija")
@SessionScoped
public class Lokalizacija {

    private Map<String, Object> jezici;
    private String odabraniJezik;
    private Locale odabraniLocale;

    public Lokalizacija() {
        jezici = new HashMap<String, Object>();
        jezici.put("Hrvatski", new Locale("hr"));
        jezici.put("English", Locale.ENGLISH);
        jezici.put("Deutsch", Locale.GERMAN);
    }

    public Object odaberiJezik() {
        for (Map.Entry<String, Object> entry : jezici.entrySet()) {
            if (entry.getValue().toString().equals(odabraniJezik)) {
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
        return "OK";
    }

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
