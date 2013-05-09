/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.JMSException;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.ejb.sb.MeteoOsvjezivac;
import org.foi.nwtis.lurajcevi.ejb.sb.MeteoPrognosticar;

/**
 *
 * @author nwtis_2
 */
@Named(value = "meteoPrognoza")
@SessionScoped
public class MeteoPrognoza implements Serializable {
    @EJB
    private MeteoOsvjezivac meteoOsvjezivac;
    @EJB
    private MeteoPrognosticar meteoPrognosticar;
    
    private String odabraniZipKod;
    private List<String> zipKodovi;  
    private List<LiveWeatherData> meteoPodaci;
    
    public MeteoPrognoza() {
    }
    
    public String dodajZipKod(){
        zipKodovi = meteoPrognosticar.dajZipKodove();
        if(zipKodovi == null){
            zipKodovi = new ArrayList<String>();
        }
        zipKodovi.add(odabraniZipKod);
        meteoPrognosticar.setZipKodovi(zipKodovi);
        String poruka = "Dodan zip kod " + odabraniZipKod;
        try {
            meteoOsvjezivac.sendJMSMessageToNWTiS_vjezba_12(poruka);
        } catch (JMSException ex) {
            Logger.getLogger(MeteoPrognoza.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR";
        }
        return "OK";
    }

    public String getOdabraniZipKod() {
        return odabraniZipKod;
    }

    public void setOdabraniZipKod(String odabraniZipKod) {
        this.odabraniZipKod = odabraniZipKod;
    }

    public List<String> getZipKodovi() {
        return zipKodovi;
    }

    public void setZipKodovi(List<String> zipKodovi) {
        this.zipKodovi = zipKodovi;
    }

    public List<LiveWeatherData> getMeteoPodaci() {
        meteoPodaci = meteoPrognosticar.getMeteoPodaci();
        return meteoPodaci;
    }

    public void setMeteoPodaci(List<LiveWeatherData> meteoPodaci) {
        this.meteoPodaci = meteoPodaci;
    }
    
}
