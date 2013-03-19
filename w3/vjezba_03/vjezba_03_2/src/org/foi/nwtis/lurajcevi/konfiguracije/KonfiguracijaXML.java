/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.konfiguracije;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nwtis_2
 */
public class KonfiguracijaXML extends KonfiguracijaApstraktna {

    public KonfiguracijaXML(String datoteka) {
        super(datoteka);
    }

    @Override
    public void ucitajKonfiguraciju() throws NemaKonfiguracije {
        if (this.datoteka == null || this.datoteka.length() == 0){
            throw new NemaKonfiguracije("Neispravan naziv datoteke.");
        }
        try {
            postavke.loadFromXML(new FileInputStream(this.datoteka));
        } catch (IOException ex) {
            throw new NemaKonfiguracije(ex.getMessage());
        }
    }

    @Override
    public void ucitajKonfiguraciju(String datoteka) throws NemaKonfiguracije {
           if (this.datoteka == null || datoteka.length() == 0){
            throw new NemaKonfiguracije("Neispravan naziv datoteke.");
        }
        try {
            postavke.loadFromXML(new FileInputStream(datoteka));
        } catch (IOException ex) {
            throw new NemaKonfiguracije(ex.getMessage());
        }
    }

    @Override
    public void spremiKonfiguraciju() throws NeispravnaKonfiguracija {
        try {
            postavke.storeToXML(new FileOutputStream(this.datoteka), "NWTIS lurajcevi");
        } catch (IOException ex) {
            throw new NeispravnaKonfiguracija(ex.getMessage());
        }
    }

    @Override
    public void spremiKonfiguraciju(String datoteka) throws NeispravnaKonfiguracija {
         try {
            postavke.storeToXML(new FileOutputStream(datoteka), "NWTIS lurajcevi");
        } catch (IOException ex) {
            throw new NeispravnaKonfiguracija(ex.getMessage());
        }
    }
    
}
