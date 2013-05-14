/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.rest.serveri;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.ws.klijenti.WeatherBugKlijent;

/**
 * REST Web Service
 *
 * @author Luka Rajcevic
 */
public class MeteoREST {

    private String zip;

    /**
     * Creates a new instance of MeteoREST
     */
    private MeteoREST(String zip) {
        this.zip = zip;
    }

    /**
     * Get instance of the MeteoREST
     */
    public static MeteoREST getInstance(String zip) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of MeteoREST class.
        return new MeteoREST(zip);
    }

    /**
     * Retrieves representation of an instance of org.foi.nwtis.lurajcevi.rest.serveri.MeteoREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        WeatherBugKlijent klijent = new WeatherBugKlijent();
        LiveWeatherData podaci = klijent.dajMeteoPodatke(zip);
        String ret = "<br/><table class='tablica'>";
        ret += "<tr><th class='zaglavlje'>Grad</th><th class='zaglavlje'>Dužina</th><th class='zaglavlje'>Širina</th><th class='zaglavlje'>Temperatura</th><th class='zaglavlje'>Vlaga</th><tr>";
        ret += "<tr class ='neparni'><td>" + podaci.getCity() + "</td><td>" + podaci.getLatitude()+ "</td><td>" + podaci.getLongitude()+ "</td><td>" + podaci.getHumidity() + "</td><td>" + podaci.getTemperature()+ "</td></tr>";
        ret += "</table>";
        return ret;
    }

    /**
     * PUT method for updating or creating an instance of MeteoREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }

    /**
     * DELETE method for resource MeteoREST
     */
    @DELETE
    public void delete() {
    }
}
