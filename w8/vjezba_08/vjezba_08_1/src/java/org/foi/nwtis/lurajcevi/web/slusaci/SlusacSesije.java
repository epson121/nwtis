/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web.slusaci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import org.foi.nwtis.lurajcevi.web.kontrola.Korisnik;

/**
 * Web application lifecycle listener.
 *
 * @author nwtis_2
 */
@WebListener()
public class SlusacSesije implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().compareTo("korisnik") == 0){
            Korisnik korisnik = (Korisnik) event.getValue();
            System.out.println("Dodajem korisnika: " + korisnik.getKorisnik());
            List<Korisnik> aktivniKorisnici = (List<Korisnik>) event.getSession().getServletContext().getAttribute("aktivniKorisnici");
            if (aktivniKorisnici == null){
                aktivniKorisnici = new ArrayList<>();
            }
            aktivniKorisnici.add(korisnik);
            event.getSession().getServletContext().setAttribute("aktivniKorisnici", aktivniKorisnici);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().compareTo("korisnik") == 0){
            Korisnik korisnik = (Korisnik) event.getValue();
            System.out.println("Brisem korisnika: " + korisnik.getKorisnik());
            List<Korisnik> aktivniKorisnici = (List<Korisnik>) event.getSession().getServletContext().getAttribute("aktivniKorisnici");
            aktivniKorisnici.remove(korisnik);
            event.getSession().getServletContext().setAttribute("aktivniKorisnici", aktivniKorisnici);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
