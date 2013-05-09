
package org.foi.nwtis.lurajcevi.web.zrna;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.FolderNotFoundException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.ReadOnlyFolderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.StoreClosedException;
import javax.mail.internet.InternetAddress;
import org.foi.nwtis.lurajcevi.web.kontrole.Poruka;
import org.foi.nwtis.lurajcevi.web.kontrole.PrivitakPoruke;

/**
 *
 * @author Luka Rajcevic
 */
@ManagedBean(name = "pregledSvihPoruka")
@SessionScoped
public class PregledSvihPoruka implements Serializable{
    private String emailPosluzitelj;
    private String korisnickoIme;
    private String lozinka;
    
    private List<Poruka> popisPoruka = new ArrayList<Poruka>();
    private Poruka odabranaPoruka;
    private String porukaID;
    private List<String> popisMapa;
    private String odabranaMapa;
    
    
    public PregledSvihPoruka() {
        EmailPovezivanje ep = (EmailPovezivanje)  FacesContext
                                                 .getCurrentInstance()
                                                 .getExternalContext()
                                                 .getSessionMap()
                                                 .get("emailPostavke");
        this.emailPosluzitelj = ep.getEmailPosluzitelj();
        this.korisnickoIme = ep.getKorisnickoIme();
        this.lozinka = ep.getLozinka();
    }
    
    public String pregledPoruke(){
        odabranaPoruka = null;
        for(Poruka p : popisPoruka) {
            if (p.getId() != null){
                if(p.getId().equals(porukaID)) {
                    odabranaPoruka = p;
                    return "OK";
                }
            } else{
                return "ERROR";
            }
        }
        return "NOT_OK";
    }
    
    public String odaberiMapu(){
        getPopisPoruka();
        return "";
    }
    
    private void preuzmiPoruke() {
        Session session = null;
        Store store = null;
        Folder folder = null;
        Message message = null;
        Message[] messages = null;
        Object messagecontentObject = null;
        String sender = null;
        String subject = null;
        Multipart multipart = null;
        Part part = null;
        String contentType = null;

        try {
            session = Session.getDefaultInstance(System.getProperties(), null);

            store = session.getStore("imap");

            store.connect(emailPosluzitelj, korisnickoIme, lozinka);

            folder = store.getDefaultFolder();
            if (odabranaMapa != null)
                folder = folder.getFolder(odabranaMapa);
            else
                folder = folder.getFolder("inbox");

            folder.open(Folder.READ_ONLY);

            messages = folder.getMessages();
            for (int messageNumber = 0; messageNumber < messages.length; messageNumber++) {
                message = messages[messageNumber];
                messagecontentObject = message.getContent();

                if (messagecontentObject instanceof Multipart) {
                    sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
                    
                    if (sender == null) {
                        sender = ((InternetAddress) message.getFrom()[0]).getAddress();
                    }
                    subject = message.getSubject();

                    multipart = (Multipart) message.getContent();

                    List<PrivitakPoruke> privitciPoruke = new ArrayList<PrivitakPoruke>();
                    String tekstPoruke = "";
                    for (int i = 0; i < multipart.getCount(); i++) {
                        // Retrieve the next part
                        part = multipart.getBodyPart(i);

                        // Get the content type
                        contentType = part.getContentType();
                        
                        // Display the content type
                        String fileName = "";
                        if (contentType.startsWith("text/plain") || contentType.startsWith("TEXT/PLAIN") ||
                            contentType.startsWith("text/html") || contentType.startsWith("TEXT/HTML")) {
                            tekstPoruke = part.getContent().toString();
                        } else {
                            // Retrieve the file name
                            fileName = part.getFileName();
                        }
                        PrivitakPoruke privitak = new PrivitakPoruke(i, contentType, part.getSize(), fileName);
                        privitciPoruke.add(privitak);

                    }
                    String[] zaglavlje = message.getHeader("Message-ID");
                    porukaID = "";
                    if (zaglavlje != null && zaglavlje.length > 0) {
                        porukaID = zaglavlje[0];
                    }

                    Poruka poruka = new Poruka(porukaID, message.getSentDate(), sender, subject, message.getContentType(),
                            message.getSize(), privitciPoruke.size(), message.getFlags(), privitciPoruke, true, true, tekstPoruke);

                    popisPoruka.add(poruka);
                    
                } else {
                    sender = ((InternetAddress) message.getFrom()[0]).getPersonal();

                    if (sender == null) {
                        sender = ((InternetAddress) message.getFrom()[0]).getAddress();
                    }

                    subject = message.getSubject();
                    porukaID = "";
                    String[] zaglavlje = message.getHeader("Message-ID");
                    if (zaglavlje != null && zaglavlje.length > 0) {
                        porukaID = zaglavlje[0];
                    }
                     Poruka poruka = new Poruka(porukaID, message.getSentDate(), sender, subject, message.getContentType(),
                            message.getSize(), 0, message.getFlags(), null, true, true, message.getContent().toString());
                     popisPoruka.add(poruka);
                }
                
            }
            folder.close(true);
            store.close();
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
        } catch (FolderClosedException e) {
            e.printStackTrace();
        } catch (FolderNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (ReadOnlyFolderException e) {
            e.printStackTrace();
        } catch (StoreClosedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEmailPosluzitelj() {
        return emailPosluzitelj;
    }

    public void setEmailPosluzitelj(String emailPosluzitelj) {
        this.emailPosluzitelj = emailPosluzitelj;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getOdabranaMapa() {
        return odabranaMapa;
    }

    public void setOdabranaMapa(String odabranaMapa) {
        this.odabranaMapa = odabranaMapa;
    }

    public List<String> getPopisMapa() {
        popisMapa = new ArrayList<String>();
        dohvatiMape();
        return popisMapa;
    }

    public void setPopisMapa(List<String> popisMapa) {
        this.popisMapa = popisMapa;
    }
    
    public void dohvatiMape(){
        popisMapa = new ArrayList<String>();
        try {
            Session session = null;
            Store store = null;
            
            session = Session.getDefaultInstance(System.getProperties(), null);
            store = session.getStore("imap");

            store.connect(emailPosluzitelj, korisnickoIme, lozinka);
            Folder[] f = store.getDefaultFolder().list();
                for(Folder fd : f)
                    popisMapa.add(fd.getName());
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(PregledSvihPoruka.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(PregledSvihPoruka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Poruka> getPopisPoruka() {
        popisPoruka.clear();
        preuzmiPoruke();
        return popisPoruka;
    }

    public void setPopisPoruka(List<Poruka> popisPoruka) {
        this.popisPoruka = popisPoruka;
    }

    public Poruka getOdabranaPoruka() {
        return odabranaPoruka;
    }

    public void setOdabranaPoruka(Poruka odabranaPoruka) {
        this.odabranaPoruka = odabranaPoruka;
    }

    public String getPorukaID() {
        return porukaID;
    }

    public void setPorukaID(String porukaID) {
        this.porukaID = porukaID;
    }
    
}