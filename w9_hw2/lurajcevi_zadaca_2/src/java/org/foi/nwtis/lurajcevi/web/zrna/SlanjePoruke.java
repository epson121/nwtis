
package org.foi.nwtis.lurajcevi.web.zrna;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Luka Rajcevic
 */
@ManagedBean(name = "slanjePoruke")
@RequestScoped
public class SlanjePoruke {
    
    private String emailPosluzitelj;
    private String korisnickoIme;
    
    private String posiljateljPoruke;
    private String primateljPoruke;
    private String predmetPoruke;
    private String tipPoruke;
    private String poruka;
    
    public SlanjePoruke() {
        EmailPovezivanje ep = (EmailPovezivanje)  FacesContext
                                                 .getCurrentInstance()
                                                 .getExternalContext()
                                                 .getSessionMap()
                                                 .get("emailPostavke");
        this.emailPosluzitelj = ep.getEmailPosluzitelj();
        this.korisnickoIme = ep.getKorisnickoIme();
    }
    
    public String saljiPoruku(){
        try {
            Date d = new Date();
            String id = d + "." + d.getTime();
            
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", this.emailPosluzitelj);
            Session session = Session.getInstance(properties, null);
            
            MimeMessage message = new MimeMessage(session);
            Address fromAddress = new InternetAddress(getPosiljateljPoruke());
            message.setFrom(fromAddress);
            Address[] toAddresses = InternetAddress.parse(getPrimateljPoruke());
            message.setRecipients(Message.RecipientType.TO,toAddresses);
            message.setHeader("Message-ID", id);
            message.setSubject(getPredmetPoruke());
            message.setText(getPoruka());
            Transport.send(message);

        } catch (MessagingException ex) {
            Logger.getLogger(SlanjePoruke.class.getName()).log(Level.SEVERE, null, ex);
            return "NOT_OK";
        } 
        return "OK";
    }    

    public String getPosiljateljPoruke() {
        return this.korisnickoIme;
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
