
package org.foi.nwtis.lurajcevi.ws.klijenti;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import net.wxbug.api.LiveWeatherData;
import net.wxbug.api.UnitType;

/**
 *
 * @author Luka Rajcevic
 */
public class WeatherBugKlijent extends HttpServlet{
    private String zip;
    private LiveWeatherData meteoPodatak;
    private static String ws_code;

    public WeatherBugKlijent() {
    }
 
    //TODO preuzeti weatherbug kod iz konteksta
    public LiveWeatherData dajMeteoPodatke(String zip){
        return getLiveWeatherByUSZipCode(zip, UnitType.METRIC, "A5537364377");
    }
    
    private static LiveWeatherData getLiveWeatherByUSZipCode(java.lang.String zipCode, net.wxbug.api.UnitType unittype, java.lang.String aCode) {
        net.wxbug.api.WeatherBugWebServices service = new net.wxbug.api.WeatherBugWebServices();
        net.wxbug.api.WeatherBugWebServicesSoap port = service.getWeatherBugWebServicesSoap12();
        return port.getLiveWeatherByUSZipCode(zipCode, unittype, aCode);
    }
    
    public static void main(String[] args) {
        WeatherBugKlijent klijent = new WeatherBugKlijent();
        LiveWeatherData podaci = klijent.dajMeteoPodatke(args[0]);
        System.out.println(podaci.getCity() + ", " + podaci.getTemperature());
    }

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
