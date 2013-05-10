
package org.foi.nwtis.lurajcevi.web.zrna;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Klasa (bean) koji vrši slanje poruke unesene u formi u web aplikaciji
 * @author Luka Rajcevic
 */
@ManagedBean(name = "slanjePoruke")
@RequestScoped
public class SlanjePoruke {
    /*******************************
     * KONSTRUKTOR
     * ****************************
     */
    private String emailPosluzitelj;
    private String korisnickoIme;
    private String posiljateljPoruke;
    private String primateljPoruke;
    private String predmetPoruke;
    private String tipPoruke;
    private String poruka;
    private boolean uspjesnoPoslano;
    private boolean neuspjesnoPoslano;
    private String mailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                             + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern p;
    private Matcher m;
    
    /*******************************
     * KONSTRUKTOR
     * ****************************
     */
    /**
     * Konstruktor za klasu SlanjePoruke
     */
    public SlanjePoruke() {
        EmailPovezivanje ep = (EmailPovezivanje)  FacesContext
                                                 .getCurrentInstance()
                                                 .getExternalContext()
                                                 .getSessionMap()
                                                 .get("emailPostavke");
        this.emailPosluzitelj = ep.getEmailPosluzitelj();
        this.korisnickoIme = ep.getKorisnickoIme();
    }
    
    /********************************
     * POMOĆNE METODE
     * ******************************
     */
    
    /**
     * Metoda za slanje email poruke s podacima unesenima u web aplikaciji
     * @return 
     */
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
            message.setSentDate(new Date());
            message.setSubject(getPredmetPoruke());
            message.setText(getPoruka());
            Transport.send(message);

        } catch (MessagingException ex) {
            Logger.getLogger(SlanjePoruke.class.getName()).log(Level.SEVERE, null, ex);
            setNeuspjesnoPoslano(true);
            setUspjesnoPoslano(false);
            return "NOT_OK";
        } 
        setNeuspjesnoPoslano(false);
        setUspjesnoPoslano(true);
        return "OK";
    }    
    
    public void provjeriMail (FacesContext context, UIComponent component, Object value) {
        
        String email = (String) value;
         
        p = Pattern.compile(mailRegex);
        m = p.matcher(email);
        if (!m.matches()){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Email nije ispravan.");
            message.setDetail("Email nije ispravan.");
            context.addMessage("slanjeForm:msgReceiver", message);
            throw new ValidatorException(message);
        }
    }
    
    public void provjeriPrazno (FacesContext context, UIComponent component, Object value) {
        
        String polje = (String) value;

        if (polje.equals("")){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Polje ne smije biti prazno.");
            message.setDetail("Polje ne smije biti prazno.");
            context.addMessage(null, message);
            throw new ValidatorException(message);
        }
    }
    
    /********************************
     * GETTERI I SETTERI
     * ******************************
     */
    
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

    public boolean isUspjesnoPoslano() {
        return uspjesnoPoslano;
    }

    public void setUspjesnoPoslano(boolean uspjesnoPoslano) {
        this.uspjesnoPoslano = uspjesnoPoslano;
    }

    public boolean isNeuspjesnoPoslano() {
        return neuspjesnoPoslano;
    }

    public void setNeuspjesnoPoslano(boolean neuspjesnoPoslano) {
        this.neuspjesnoPoslano = neuspjesnoPoslano;
    }
    
}
