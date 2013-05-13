/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NeispravnaKonfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;
import sun.security.util.Length;

/**
 *
 * @author nwtis_2
 */
public class Vjezba_03_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 1){
            try {
                Konfiguracija konfig = KonfiguracijaApstraktna.preuzmiKonfiguraciju(args[0]);
                for (Enumeration e = konfig.dajPostavke(); e.hasMoreElements();){
                    String p = (String) e.nextElement();
                    System.out.println(p + " - " + konfig.dajPostavku(p));
                }
            } catch (NemaKonfiguracije ex) {
                Logger.getLogger(Vjezba_03_4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (args.length == 2){
            try {
                Konfiguracija konfig = KonfiguracijaApstraktna.preuzmiKonfiguraciju(args[0]);
                System.out.println(konfig.dajPostavku(args[1]));
            } catch (NemaKonfiguracije ex) {
                Logger.getLogger(Vjezba_03_4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (args.length == 3){
            try {
                Konfiguracija konfig = KonfiguracijaApstraktna.preuzmiKonfiguraciju(args[0]);
                if (konfig.postojiPostavka(args[1]))
                    konfig.azurirajPostavku(args[1], args[2]);
                else
                    konfig.spremiPostavku(args[1], args[2]);
                konfig.spremiKonfiguraciju();
            } catch (NemaKonfiguracije | NeispravnaKonfiguracija ex) {
                Logger.getLogger(Vjezba_03_4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("komande: datoteka [postavka [vrijednost]]");
        }
    }
}
