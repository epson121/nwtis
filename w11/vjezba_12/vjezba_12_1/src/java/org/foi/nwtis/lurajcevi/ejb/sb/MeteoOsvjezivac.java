/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.ejb.sb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.ws.WebServiceRef;
import net.wxbug.api.LiveWeatherData;
import net.wxbug.api.UnitType;
import net.wxbug.api.WeatherBugWebServices;

/**
 *
 * @author nwtis_2
 */
@Singleton
@LocalBean
public class MeteoOsvjezivac {
    @Resource(mappedName = "jms/NWTiS_vjezba_12")
    private Queue nWTiS_vjezba_12;
    @Resource(mappedName = "jms/NWTiS_QF_vjezba_12")
    private ConnectionFactory nWTiS_QF_vjezba_12;
    @EJB
    private MeteoPrognosticar meteoPrognosticar;
    @WebServiceRef(wsdlLocation = "META-INF/wsdl/api.wxbug.net/weatherservice.asmx.wsdl")
    private WeatherBugWebServices service;
    private String myWeatherBugCode = "A5537364377";
    
    @Schedule(minute = "*/2", hour="*", persistent = false)
    public void myTimer() {
        System.out.println("Timer event: " + new Date());
        List<String> zipKodovi = meteoPrognosticar.dajZipKodove();
        List<LiveWeatherData> meteoPodaci = new ArrayList<LiveWeatherData>();
        if (zipKodovi != null){
            for (String zip : zipKodovi){
                LiveWeatherData mp = getLiveWeatherByUSZipCode(zip, UnitType.METRIC, myWeatherBugCode);
                meteoPodaci.add(mp);
            }
        }
        meteoPrognosticar.setMeteoPodaci(meteoPodaci);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private LiveWeatherData getLiveWeatherByUSZipCode(java.lang.String zipCode, net.wxbug.api.UnitType unittype, java.lang.String aCode) {
        net.wxbug.api.WeatherBugWebServicesSoap port = service.getWeatherBugWebServicesSoap12();
        return port.getLiveWeatherByUSZipCode(zipCode, unittype, aCode);
    }

    private Message createJMSMessageForjmsNWTiS_vjezba_12(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    public void sendJMSMessageToNWTiS_vjezba_12(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = nWTiS_QF_vjezba_12.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(nWTiS_vjezba_12);
            messageProducer.send(createJMSMessageForjmsNWTiS_vjezba_12(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
    
    
}
