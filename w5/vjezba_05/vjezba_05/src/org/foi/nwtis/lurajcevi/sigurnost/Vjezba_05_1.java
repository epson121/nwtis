/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.sigurnost;

import java.io.File;
import java.util.Enumeration;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;

/**
 *
 * @author nwtis_2
 */
public class Vjezba_05_1 {
    
   
    
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Error.");
            return;
        }
        File datoteka = new File(args[0]);
        if (!datoteka.exists()){
            System.out.println("So such file. Exiting now.");
            return;
        }
        
        try {
            Konfiguracija config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(args[0]);
            for (Enumeration e = config.dajPostavke(); e.hasMoreElements();){
                String k = (String) e.nextElement();
                String v = config.dajPostavku(k);
                System.out.println("K: " + k + " V: " + v);
            }
        } catch (NemaKonfiguracije ex) {
            System.out.println("No such file. Exiting now.");
        }
        
       
    }
    
}
