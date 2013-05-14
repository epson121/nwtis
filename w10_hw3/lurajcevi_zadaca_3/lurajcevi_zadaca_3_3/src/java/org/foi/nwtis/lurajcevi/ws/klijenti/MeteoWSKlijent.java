
package org.foi.nwtis.lurajcevi.ws.klijenti;

import net.wxbug.api.LiveWeatherData;

/**
 *
 * @author Luka Rajčević
 */
public class MeteoWSKlijent {

    /**
     * Dohvaća meteorološke podatke za dani zip kod, koristeći web servise
     * @param zip
     * @return 
     */
    public static LiveWeatherData dajMeteoWSPodatkeZaZip(java.lang.String zip) {
        org.foi.nwtis.lurajcevi.ws.serveri.MeteoWS_Service service = new org.foi.nwtis.lurajcevi.ws.serveri.MeteoWS_Service();
        org.foi.nwtis.lurajcevi.ws.serveri.MeteoWS port = service.getMeteoWSPort();
        return port.dajMeteoWSPodatkeZaZip(zip);
    }
    
}
