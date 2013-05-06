package org.foi.nwtis.lurajcevi.web;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Address;
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
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.lurajcevi.web.zrna.SlanjePoruke;

/**
 *
 * @author Luka Rajcevic
 */
public class ObradaPoruka extends Thread {

    private BP_Konfiguracija bpKonf;
    private Konfiguracija config;
    private String emailPosluzitelj;
    private String emailPort;
    private String korisnickoIme;
    private String korisnickaLozinka;
    private String trazeniPredmet;
    
    private String resourcesPath;
    private String nwtis_galerije;
    private String nwtis_ostalePoruke;
    private String nwtis_ispravnePoruke;
    private String nwtis_neispravnePoruke;
    private String nwtis_porukaAdresa;
    private String nwtis_porukaPredmet;
    private String nwtis_porukaPoslanaMapa;
    
    
    private String regexPoruke = "^USER ([a-zA-Z_]+)\nPASSWORD ([a-zA-Z0-9_]+)GALERY ([a-zA-Z0-9_]+)*$";
    private String regUser = "^USER ([a-zA-Z_]+)";
    private String regPass = "^PASSWORD ([a-zA-Z_]+)";
    private String regGalery = "^GALERY ([a-zA-Z_]+)";
    private Matcher m;
    private Pattern p;
    private boolean isAuthenticated = false;
    private boolean isValid = false;
    private String porukaGalerija;
    
    BufferedImage image = null;
    
    private int interval;
    private static int counter = 1;
    private SimpleDateFormat df;
    
    /**
     * Constructor za ObradaPoruka
     */
    public ObradaPoruka(Konfiguracija config, BP_Konfiguracija bpKonf,
                        String resourcesPath) {
        emailPosluzitelj = config.dajPostavku("emailPosluzitelj");
        emailPort = config.dajPostavku("emailPort");
        korisnickoIme = config.dajPostavku("username");
        korisnickaLozinka = config.dajPostavku("password");
        trazeniPredmet = config.dajPostavku("trazeniPredmet");
        this.interval = Integer.parseInt(config.dajPostavku("interval"));
        this.bpKonf = bpKonf;
        this.resourcesPath = resourcesPath;
        
        nwtis_galerije = config.dajPostavku("NWTiS_galerije");
        nwtis_ispravnePoruke = config.dajPostavku("NWTiS_IspravnePoruke");
        nwtis_ostalePoruke = config.dajPostavku("NWTiS_OstalePoruke");
        nwtis_neispravnePoruke = config.dajPostavku("NWTiS_NeispravnePoruke");
        nwtis_porukaAdresa = config.dajPostavku("NWTiS_PorukaAdresa");
        nwtis_porukaPredmet = config.dajPostavku("NWTiS_PorukaPredmet");
        nwtis_porukaPoslanaMapa = config.dajPostavku("NWTiS_PorukaPoslanaMapa");
        
        df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        setName("Th " + counter);
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
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
        long start, duration = 0;
        while (true) {
            try {
                start = System.currentTimeMillis();
                printData("--------------processing mails started-----------------");
                session = Session.getDefaultInstance(System.getProperties(), null);

                store = session.getStore("imap");

                store.connect(emailPosluzitelj, korisnickoIme, korisnickaLozinka);
                printData("Connection established with IMAP server.");
                
                // Get a handle on the default folder
                folder = store.getDefaultFolder();
                folder = folder.getFolder("inbox");
                
                //Reading the Email Index in Read / Write Mode
                folder.open(Folder.READ_WRITE);
                // Retrieve the messages
                messages = folder.getMessages();
                
                if (messages.length > 0){
                    System.out.println("IMA PORUKA.");
                }
                else{
                    System.out.println("NEMA PORUKA");
                }
                
                for (int messageNumber = 0; messageNumber < messages.length; messageNumber++) {
                    message = messages[messageNumber];
                    messagecontentObject = message.getContent();
                    
                    if (message.getSubject().startsWith(trazeniPredmet)){
                        
                        if (messagecontentObject instanceof Multipart) {
                            printData("MIME poruka");
                            sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
                            
                            if (sender == null) {
                                sender = ((InternetAddress) message.getFrom()[0]).getAddress();
                                printData("sender in NULL. Printing Address:" + sender);
                            }
                            
                            printData("Sender -." + sender);
                            subject = message.getSubject();
                            printData("subject=" + subject);

                            multipart = (Multipart) message.getContent();
                            printData("Retrieve the Multipart object from the message");

                            for (int i = 0; i < multipart.getCount(); i++) {

                                part = multipart.getBodyPart(i);
                                contentType = part.getContentType();
                                printData("Content: " + contentType);
                                String[] messageContent = null;
                                if (contentType.startsWith("text/plain") || contentType.startsWith("TEXT/PLAIN")){
                                    messageContent = part.getContent().toString().split("\n");
                                    porukaGalerija = messageContent[2].split("GALERY")[1].trim();
                                    if (isMatchingRegex(regUser, messageContent[0].trim()) 
                                     && isMatchingRegex(regPass, messageContent[1].trim()) 
                                     && isMatchingRegex(regGalery, messageContent[2].trim()) 
                                     && verifyInDatabase(messageContent[0].split("USER")[1].trim(), 
                                                         messageContent[1].split("PASSWORD")[1].trim())){
                                        isAuthenticated = true;
                                        System.out.println("MATCHES OK");
                                    } else{
                                        System.out.println("MATCHES NOT OK");
                                        
                                        Folder f = store.getFolder(nwtis_neispravnePoruke);
                                        if (!f.exists()){ 
                                            f.create(Folder.HOLDS_MESSAGES);
                                        }
                                        f.appendMessages(new Message[] {message});
                                        continue;
                                    }
                                } else if (isAuthenticated && (contentType.startsWith("image/") 
                                           || contentType.startsWith("IMAGE/"))) {
                                    String fileName = part.getFileName();
                                    System.out.println("RES path: " + resourcesPath);
                                    System.out.println("GALERIJE: " + nwtis_galerije);
                                    System.out.println("messageContent[2]" + porukaGalerija);
                                    System.out.println("FILENAME: " + fileName);
                                    File parent = new File(resourcesPath + File.separator + nwtis_galerije + File.separator + porukaGalerija + File.separator);
                                    if(!parent.exists()) {
                                        parent.mkdirs();
                                    }
                                    File f = new File(parent, fileName );
                                    DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
                                    com.sun.mail.util.BASE64DecoderStream test = (com.sun.mail.util.BASE64DecoderStream) part.getContent();
                                    
                                    byte[] buffer = new byte[1024];
                                    int bytesRead;
                                    while((bytesRead = test.read(buffer)) != -1){
                                        output.write(buffer,0,bytesRead);
                                    }
                                    test.close();
                                    output.close(); 
                                    System.out.println("Got image!!!");
                                    isValid = true;
                                } else if (isAuthenticated && (contentType.startsWith("application/octet-stream")
                                           || contentType.startsWith("APPLICATION/OCTET-STREAM"))){
                                    String fileName = part.getFileName();
                                    File f = new File(resourcesPath + File.separator + nwtis_galerije + File.separator + messageContent[2] + File.separator + fileName );
                                    DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
                                    com.sun.mail.util.BASE64DecoderStream test = (com.sun.mail.util.BASE64DecoderStream) part.getContent();
                                    
                                    byte[] buffer = new byte[1024];
                                    int bytesRead;
                                    while((bytesRead = test.read(buffer)) != -1){
                                        output.write(buffer,0,bytesRead);
                                    }
                                    test.close();
                                    output.close(); 
                                    System.out.println("Found octet filetype.");
                                    isValid = true;
                                }
                            }
                        } else {
                            printData("Found Mail Without Attachment");
                            printData("Saving to folder : ostalePoruke");
                            Folder f = store.getFolder(nwtis_neispravnePoruke);
                            if (!f.exists()){ 
                                f.create(Folder.HOLDS_MESSAGES);
                            }
                            f.appendMessages(new Message[] {message});
                            isAuthenticated = false;
                            continue;
                        }
                    }
                    else{
                        printData("Found Mail Without Attachment");
                        printData("Saving to folder : ostalePoruke");
                        Folder f = store.getFolder(nwtis_ostalePoruke);
                        if (!f.exists()){ 
                            f.create(Folder.HOLDS_MESSAGES);
                        }
                        f.appendMessages(new Message[] {message});
                        continue;
                    }
                    isAuthenticated = false;
                    if (isValid){
                        Folder f = store.getFolder(nwtis_ispravnePoruke);
                        if (!f.exists()){ 
                            f.create(Folder.HOLDS_MESSAGES);
                        }
                        f.appendMessages(new Message[] {message});
                        isValid = false;
                    }
                }
                String vrijemePocetak = df.format(new Date(start));
                String vrijemeKraj = df.format(new Date(System.currentTimeMillis()));
                duration = System.currentTimeMillis() - start;
                String tekstPoruke = "Obrada započela u: " + vrijemePocetak + 
                                     "\nObrada završila u: " + vrijemeKraj + 
                                     "\nTrajanje obrade u ms: " + duration;
                saljiPoruku("server", nwtis_porukaAdresa, nwtis_porukaPredmet, tekstPoruke, store);
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
            try {
                int sleepTime;
                sleepTime = (int) ((int) (interval * 1000) - duration);
                System.out.println("Spavanje: " + sleepTime);
                sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    public BP_Konfiguracija getBpKonf() {
        return bpKonf;
    }

    public void setBpKonf(BP_Konfiguracija bpKonf) {
        this.bpKonf = bpKonf;
    }

    private void printData(String data) {
        System.out.println(data);
    }
    
    private boolean isMatchingRegex(String regex, String expression){
        p = Pattern.compile(regex);
        m = p.matcher(expression);
        if (m.matches()){
            System.out.println("String " + expression + " matches its regex.");
            return true;
        }
        return false;
    }
    
    private boolean verifyInDatabase(String username, String password){
        String upit = "SELECT kor_ime, lozinka FROM polaznici";
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        System.out.println("URL DB: " + url);
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        
        try{
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery(upit);
        
            while (rs.next()) {
                String ime = rs.getString("kor_ime");
                String prezime = rs.getString("lozinka");
                if (ime.equals(username) && lozinka.equals(password)){
                    return true;
                }
            }
        } catch(SQLException e){
            if (veza != null){
                veza = null;
            }
            if (instr != null){
                instr = null;
            }
            if (rs != null){
                rs = null;
            }
            e.printStackTrace();
        }
        //return false;
        return true;
    }
    
     public void saljiPoruku(String from, String to, String subject, String msg, Store store){
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "127.0.0.1");
            Session session = Session.getInstance(properties, null);
            
            MimeMessage message = new MimeMessage(session);
            Address fromAddress = new InternetAddress(from);
            message.setHeader("Content-Type", "text/html");
            message.setFrom(fromAddress);
            Address[] toAddresses = InternetAddress.parse(to);
            message.setRecipients(Message.RecipientType.TO,toAddresses);
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);
            
            Folder f = store.getFolder(nwtis_porukaPoslanaMapa);
            if (!f.exists()){ 
                f.create(Folder.HOLDS_MESSAGES);
            }
            f.appendMessages(new Message[] {message});
            
        } catch (MessagingException ex) {
            Logger.getLogger(SlanjePoruke.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    
}
