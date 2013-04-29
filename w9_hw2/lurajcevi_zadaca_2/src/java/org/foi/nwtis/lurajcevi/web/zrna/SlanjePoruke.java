/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web.zrna;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

/**
 *
 * @author Luka Rajcevic
 */
@ManagedBean(name = "slanjePoruke")
@RequestScoped
public class SlanjePoruke {
    
    private String posiljateljPoruke;
    private String primateljPoruke;
    private String predmetPoruke;
    private String tipPoruke;
    private String poruka;
    
    public SlanjePoruke() {
        
    }
    
    public String saljiPoruku(){
        //TODO ako je uspjesno, ispisuje se poruka, ako nije isto tako
        //TODO  Dodati varijablu za poruku u MB i mjesto u obrascu
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "127.0.0.1");
            Session session = Session.getInstance(properties, null);
            
            MimeMessage message = new MimeMessage(session);
            Address fromAddress = new InternetAddress(posiljateljPoruke);
            message.setFrom(fromAddress);
            Address[] toAddresses = InternetAddress.parse(primateljPoruke);
            message.setRecipients(Message.RecipientType.TO,toAddresses);
            message.setSubject(predmetPoruke);
            message.setText(poruka);
            Transport.send(message);

        } catch (MessagingException ex) {
            Logger.getLogger(SlanjePoruke.class.getName()).log(Level.SEVERE, null, ex);
            return "NOT_OK";
        } 
        return "OK";
    }    

    public String getPosiljateljPoruke() {
        return posiljateljPoruke;
    }

    public void setPosiljateljPoruke(String posiljateljPoruke) {
        this.posiljateljPoruke = posiljateljPoruke;
    }

    public String getPrimateljPoruke() {
        return primateljPoruke;
    }

    public void setPrimateljPoruke(String primateljPoruke) {
        this.primateljPoruke = primateljPoruke;
    }

    public String getPredmetPoruke() {
        return predmetPoruke;
    }

    public void setPredmetPoruke(String predmetPoruke) {
        this.predmetPoruke = predmetPoruke;
    }

    public String getTipPoruke() {
        return tipPoruke;
    }

    public void setTipPoruke(String tipPoruke) {
        this.tipPoruke = tipPoruke;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    } 
    
}
