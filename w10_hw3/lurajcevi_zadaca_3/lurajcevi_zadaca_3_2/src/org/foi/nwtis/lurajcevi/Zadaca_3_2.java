
package org.foi.nwtis.lurajcevi;

import java.util.ArrayList;
import java.util.List;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.rest.klijenti.MeteoRESTKlijent;

/**
 *
 * @author Luka Rajcevic
 */
public class Zadaca_3_2 {
    
    /**
     * main metoda ove klase, ovisno o danim parametrima poziva ili web servis, 
     * ili REST servis.
     * @param args 
     */
    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("Trebam minimalno dva argumenta.");
            return;
        }
        if (args[0].equals("1")){
            List<String> zipovi = new ArrayList<>();
            for(int i = 1; i < args.length; i++){
                zipovi.add(args[i]);
            }
            List<LiveWeatherData> podaci = dajMeteoWSPodatkeZaViseZip(zipovi);
            for (LiveWeatherData data : podaci){
                System.out.println("Grad: " + data.getCity());
                System.out.println("Temperatura: " + data.getTemperature());
                System.out.println("Vlaznost: " + data.getHumidity());
            }
        } else if (args[0].equals("2")){
            MeteoRESTKlijent klijent = new MeteoRESTKlijent(args[1]);
            System.out.println(klijent.getHtml());
        }
    }

    private static LiveWeatherData dajMeteoWSPodatkeZaZip(java.lang.String zip) {
        org.foi.nwtis.lurajcevi.ws.serveri.MeteoWS_Service service = new org.foi.nwtis.lurajcevi.ws.serveri.MeteoWS_Service();
        org.foi.nwtis.lurajcevi.ws.serveri.MeteoWS port = service.getMeteoWSPort();
        return port.dajMeteoWSPodatkeZaZip(zip);
    }

    private static java.util.List<net.wxbug.api.LiveWeatherData> dajMeteoWSPodatkeZaViseZip(java.util.List<java.lang.String> zipovi) {
        org.foi.nwtis.lurajcevi.ws.serveri.MeteoWS_Service service = new org.foi.nwtis.lurajcevi.ws.serveri.MeteoWS_Service();
        org.foi.nwtis.lurajcevi.ws.serveri.MeteoWS port = service.getMeteoWSPort();
        return port.dajMeteoWSPodatkeZaViseZip(zipovi);
    }
    
    
    
}
