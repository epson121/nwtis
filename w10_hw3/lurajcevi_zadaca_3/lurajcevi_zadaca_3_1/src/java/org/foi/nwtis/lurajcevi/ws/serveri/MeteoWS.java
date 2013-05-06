/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.ws.serveri;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.ws.klijenti.WeatherBugKlijent;

/**
 *
 * @author nwtis_2
 */
@WebService(serviceName = "MeteoWS")
public class MeteoWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dajMeteoWSPodatkeZaZip")
    public LiveWeatherData dajMeteoWSPodatkeZaZip(@WebParam(name = "zip") String zip) {
        WeatherBugKlijent klijent = new WeatherBugKlijent();
        return klijent.dajMeteoPodatke(zip);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dajMeteoWSPodatkeZaViseZip")
    public java.util.List<net.wxbug.api.LiveWeatherData> dajMeteoWSPodatkeZaViseZip(@WebParam(name = "zipovi") java.util.List<java.lang.String> zipovi) {
        List<LiveWeatherData> lista = new ArrayList<LiveWeatherData>();
        WeatherBugKlijent klijent = new WeatherBugKlijent();
        for (String zip : zipovi){
            LiveWeatherData podaci = klijent.dajMeteoPodatke(zip);
            lista.add(podaci);
        }
        return lista;
    }
}
