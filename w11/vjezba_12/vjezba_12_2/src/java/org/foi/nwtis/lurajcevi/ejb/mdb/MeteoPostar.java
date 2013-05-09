/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.ejb.mdb;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author nwtis_2
 */
@MessageDriven(mappedName = "jms/NWTiS_vjezba_12", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MeteoPostar implements MessageListener {
    
    public MeteoPostar() {
    }
    
    @Override
    public void onMessage(Message message) {
        if (message != null){
            try {
                System.out.println("ID poruke: " + message.getJMSMessageID());
                System.out.println("Vrijeme: " + new Date(message.getJMSTimestamp()));
                System.out.println(message.toString());
            } catch (JMSException ex) {
                Logger.getLogger(MeteoPostar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
