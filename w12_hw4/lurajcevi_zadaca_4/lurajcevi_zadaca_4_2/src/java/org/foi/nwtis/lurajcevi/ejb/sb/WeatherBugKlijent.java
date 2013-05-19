/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.ejb.sb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.xml.ws.WebServiceRef;
import net.wxbug.api.LiveWeatherData;
import net.wxbug.api.UnitType;
import net.wxbug.api.WeatherBugWebServices;

/**
 *
 * @author Luka Rajcevic
 * 
 */
@Stateless
@LocalBean
public class WeatherBugKlijent {
    @WebServiceRef(wsdlLocation = "META-INF/wsdl/api.wxbug.net/weatherservice.asmx.wsdl")
    private WeatherBugWebServices service;
    
    private String weatherBugCode = "A5537364377";

    private LiveWeatherData getLiveWeatherByUSZipCode(java.lang.String zipCode, net.wxbug.api.UnitType unittype, java.lang.String aCode) {
        net.wxbug.api.WeatherBugWebServicesSoap port = service.getWeatherBugWebServicesSoap();
        return port.getLiveWeatherByUSZipCode(zipCode, unittype, aCode);
    }
    
    public LiveWeatherData dajMeteoPodatke(String zip){
        //TODO preuzeti iz web xml
        return getLiveWeatherByUSZipCode(zip, UnitType.METRIC, weatherBugCode);
    }
    
}
