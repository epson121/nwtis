/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NeispravnaKonfiguracija;

/**
 *
 * @author nwtis_2
 */
public class Vjezba_03_3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Trebam 1 argument.");
            return;
        }
        try {
            Konfiguracija konfig = KonfiguracijaApstraktna.kreirajKonfiguraciju(args[0]);
        } catch (NeispravnaKonfiguracija ex) {
            Logger.getLogger(Vjezba_03_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
