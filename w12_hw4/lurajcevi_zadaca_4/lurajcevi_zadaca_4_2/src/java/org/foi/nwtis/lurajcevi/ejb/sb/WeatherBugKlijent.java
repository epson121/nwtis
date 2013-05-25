
package org.foi.nwtis.lurajcevi.ejb.sb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.ws.WebServiceRef;
import net.wxbug.api.LiveWeatherData;
import net.wxbug.api.UnitType;
import net.wxbug.api.WeatherBugWebServices;

/**
 *
 * @author Luka Rajcevic
 * Klasa koja koristi poziv metode web servisa kako bi dohvatila meteorološke
 * podatke za dani zip kod.
 */
@Stateless
@LocalBean
public class WeatherBugKlijent {
    @WebServiceRef(wsdlLocation = "META-INF/wsdl/api.wxbug.net/weatherservice.asmx.wsdl")
    private WeatherBugWebServices service;
    
    private String weatherBugCode = "A5537364377";
    /**
     * Dohvaća meteo podatke za dani zip kod
     * @param zip - zip kod za koji se dohvaćaju meteoroloski podaci
     * @return 
     */
    public LiveWeatherData dajMeteoPodatke(String zip){
        //TODO preuzeti iz web xml
        /*
        String wb_code = null;
        try {
           Context env = (Context) new InitialContext().lookup("java:comp/env");
           wb_code = (String) env.lookup("wb_code");
        } catch (NamingException ex) {
            Logger.getLogger(WeatherBugKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return getLiveWeatherByUSZipCode(zip, UnitType.METRIC, weatherBugCode);
    }
    
    /**
     * poziv funkcije web servisa
     * @param zipCode - zip kod za koji se dohvaćaju meteo podaci
     * @param unittype - jedinični sustav (ENGLISH, METRIC...)
     * @param aCode - api kod za pristup servisu
     * @return 
     */
    private LiveWeatherData getLiveWeatherByUSZipCode(java.lang.String zipCode, net.wxbug.api.UnitType unittype, java.lang.String aCode) {
        net.wxbug.api.WeatherBugWebServicesSoap port = service.getWeatherBugWebServicesSoap();
        return port.getLiveWeatherByUSZipCode(zipCode, unittype, aCode);
    }
    
    
    
}
