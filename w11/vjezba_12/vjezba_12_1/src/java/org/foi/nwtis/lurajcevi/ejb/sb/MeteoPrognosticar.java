/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.ejb.sb;

import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import net.wxbug.api.LiveWeatherData;

/**
 *
 * @author nwtis_2
 */
@Stateful
@LocalBean
public class MeteoPrognosticar {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private List<String> zipKodovi;
    private List<LiveWeatherData> meteoPodaci;
    
    public List<String> getZipKodovi() {
        return zipKodovi;
    }

    public void setZipKodovi(List<String> zipKodovi) {
        this.zipKodovi = zipKodovi;
    }

    public List<LiveWeatherData> getMeteoPodaci() {
        return meteoPodaci;
    }

    public void setMeteoPodaci(List<LiveWeatherData> meteoPodaci) {
        this.meteoPodaci = meteoPodaci;
    }

    public List<String> dajZipKodove() {
        return this.zipKodovi;
    }
}
