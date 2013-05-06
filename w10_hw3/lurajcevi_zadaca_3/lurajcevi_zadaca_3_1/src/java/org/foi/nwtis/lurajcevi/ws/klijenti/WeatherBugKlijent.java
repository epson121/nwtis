
package org.foi.nwtis.lurajcevi.ws.klijenti;

import net.wxbug.api.LiveWeatherData;
import net.wxbug.api.UnitType;

/**
 *
 * @author nwtis_2
 */
public class WeatherBugKlijent {
    private String zip;
    private LiveWeatherData meteoPodatak;
    
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
        LiveWeatherData podaci = klijent.dajMeteoPodatke("90001");
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
