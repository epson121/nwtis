
package org.foi.nwtis.lurajcevi.ws.klijenti;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import net.wxbug.api.LiveWeatherData;
import net.wxbug.api.UnitType;

/**
 *
 * @author Luka Rajcevic
 */
public class WeatherBugKlijent {
    
    /*******************************************
    * VARIJABLE
    ******************************************* 
    */
    private String zip;
    private LiveWeatherData meteoPodatak;

    /*******************************************
    * KONSTRUKTOR
    ******************************************* 
    */
    public WeatherBugKlijent() {
    }
    
    /*******************************************
    * POMOĆNE METODE
    ******************************************* 
    */
    
    /**
     * služi za dohvaćanje meteoroloških podataka. Dohvaća API key iz web.xml datoteke
     * te ga koristi prilikom slanja zahtjeva
     * @param zip
     * @return 
     */
    public LiveWeatherData dajMeteoPodatke(String zip){
        String wb_code = null;
        try {
           Context env = (Context) new InitialContext().lookup("java:comp/env");
           wb_code = (String) env.lookup("wb_code");
        } catch (NamingException ex) {
            Logger.getLogger(WeatherBugKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getLiveWeatherByUSZipCode(zip, UnitType.METRIC, wb_code);
    }
    
    /**
     * Metoda koja koristi webs ervis za sohvaćanje meteoroloških podataka
     * preko danog zip koda, jedinice mjere i APi ključa
     * @param zipCode - dani zip kod
     * @param unittype - tip jedinice mjere (metrički ili engleski sustav)
     * @param aCode - api key
     * @return 
     */
    private static LiveWeatherData getLiveWeatherByUSZipCode(java.lang.String zipCode, net.wxbug.api.UnitType unittype, java.lang.String aCode) {
        net.wxbug.api.WeatherBugWebServices service = new net.wxbug.api.WeatherBugWebServices();
        net.wxbug.api.WeatherBugWebServicesSoap port = service.getWeatherBugWebServicesSoap12();
        return port.getLiveWeatherByUSZipCode(zipCode, unittype, aCode);
    }

    /*******************************************
    * GETTERI I SETTERI
    ******************************************* 
    */
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public LiveWeatherData getMeteoPodatak() {
        meteoPodatak = dajMeteoPodatke(zip);
        return meteoPodatak;
    }

}
