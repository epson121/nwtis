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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Flags;
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
    
    
    private String regexPoruke = "^USER ([a-zA-Z0-9_]+) PASSWORD ([a-zA-Z0-9_]+) GALERY ([a-zA-Z0-9_]+)*$";
    private String regUser = "^USER ([a-zA-Z_]+)*$";
    private String regPass = "^PASSWORD ([a-zA-Z_]+)*$";
    private String regGalery = "^GALERY ([a-zA-Z_]+)*$";
    private Matcher m;
    private Pattern p;
    private boolean isAuthenticated = false;
    private boolean isValid = false;
    private String porukaGalerija;
    
    BufferedImage image = null;
    
    private int interval;
    private static int counter = 1;
    private SimpleDateFormat df;
    
    //brojaci poruka
    private static int sveukupanBrojPoruka;
    private static int ukupanBrojPoruka;
    private static int brojIspravnihPoruka;
    private static int brojNeispravnihPoruka;
    private static int brojOstalihPoruka;
    private static int brojPreuzetihDatoteka;
    
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
        
        sveukupanBrojPoruka = 0;
        ukupanBrojPoruka = 0;
        brojIspravnihPoruka = 0;
        brojNeispravnihPoruka = 0;
        brojOstalihPoruka = 0;
        brojPreuzetihDatoteka = 0;
        
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
                //TODO brisanje poruke kod prolaza
                for (int messageNumber = 0; messageNumber < messages.length; messageNumber++) {
                    sveukupanBrojPoruka += 1;
                    ukupanBrojPoruka += 1;
                    message = messages[messageNumber];
                    messagecontentObject = message.getContent();

                    if (message.getSubject().startsWith(trazeniPredmet)){
                        
                        if (messagecontentObject instanceof Multipart) {
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
                                String messageContent[] = null;
                                String[] podaci = null;
                                if (contentType.startsWith("text/plain") || contentType.startsWith("TEXT/PLAIN")){
                                    
                                    messageContent = part.getContent().toString().split("\n");
                                    System.out.println("MESSAGE CONTENT: " + messageContent);
                                    podaci = isMatchingRegex(messageContent[0], messageContent[1], messageContent[2]);
                                    if (podaci != null){
                                        if (verifyInDatabase(podaci[0], podaci[1])){
                                            isAuthenticated = true;
                                            System.out.println("MATCHES OK");
                                        }
                                    } else{
                                        System.out.println("MATCHES NOT OK");
                                        brojNeispravnihPoruka += 1;
                                        Folder f = store.getFolder(nwtis_neispravnePoruke);
                                        if (!f.exists()){ 
                                            f.create(Folder.HOLDS_MESSAGES);
                                        }
                                        f.appendMessages(new Message[] {message});
                                        if (f.isOpen())
                                            f.close(true);
                                        message.setFlag(Flags.Flag.DELETED, true);
                                        continue;
                                    }
                                } else if (isAuthenticated && (contentType.startsWith("image/") 
                                           || contentType.startsWith("IMAGE/"))) {
                                    String fileName = part.getFileName();
  
                                    File parent = new File(resourcesPath + File.separator + nwtis_galerije + File.separator + podaci[2] + File.separator);
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
                                    brojPreuzetihDatoteka += 1;
                                    isValid = true;
                                } else if (isAuthenticated && (contentType.startsWith("application/octet-stream")
                                           || contentType.startsWith("APPLICATION/OCTET-STREAM"))){
                                    String fileName = part.getFileName();
                                    File f = new File(resourcesPath + File.separator + nwtis_galerije + File.separator + podaci[2] + File.separator + fileName );
                                    DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
                                    com.sun.mail.util.BASE64DecoderStream test = (com.sun.mail.util.BASE64DecoderStream) part.getContent();
                                    
                                    byte[] buffer = new byte[1024];
                                    int bytesRead;
                                    while((bytesRead = test.read(buffer)) != -1){
                                        output.write(buffer,0,bytesRead);
                                    }
                                    test.close();
                                    output.close(); 
                                    System.out.println("Got octet filetype.");
                                    brojPreuzetihDatoteka += 1;
                                    isValid = true;
                                }
                            }
                        } else {
                            printData("Saving to folder : neispravne");
                            brojNeispravnihPoruka += 1;
                            Folder f = store.getFolder(nwtis_neispravnePoruke);
                            if (!f.exists()){ 
                                f.create(Folder.HOLDS_MESSAGES);
                            }
                            f.appendMessages(new Message[] {message});
                            if (f.isOpen())
                                f.close(true);
                            isAuthenticated = false;
                            //message.setFlag(Flags.Flag.DELETED, true);
                            continue;
                        }
                    }
                    else{
                        printData("Saving to folder : ostalePoruke");
                        brojOstalihPoruka += 1;
                        Folder f = store.getFolder(nwtis_ostalePoruke);
                        if (!f.exists()){ 
                            f.create(Folder.HOLDS_MESSAGES);
                        }
                        f.appendMessages(new Message[] {message});
                        if (f.isOpen())
                            f.close(true);
                        message.setFlag(Flags.Flag.DELETED, true);
                        continue;
                    }
                    isAuthenticated = false;
                    if (isValid){
                        printData("Saving to folder : ispravne");
                        brojIspravnihPoruka += 1;
                        Folder f = store.getFolder(nwtis_ispravnePoruke);
                        if (!f.exists()){ 
                            f.create(Folder.HOLDS_MESSAGES);
                        }
                        f.appendMessages(new Message[] {message});
                        isValid = false;
                        if (f.isOpen())
                            f.close(true);
                    }
                    message.setFlag(Flags.Flag.DELETED, true);
                }
                Folder[] f = store.getDefaultFolder().list();
                for(Folder fd : f){
                    System.out.println("FOLDER >> " + fd.getName());
                    System.out.println("BROJ PORUKA U FOLDERU: " + fd.getMessageCount());
                }
                String vrijemePocetak = df.format(new Date(start));
                String vrijemeKraj = df.format(new Date(System.currentTimeMillis()));
                duration = System.currentTimeMillis() - start;
                String tekstPoruke = "Obrada započela u: " + vrijemePocetak + 
                                     "\nObrada završila u: " + vrijemeKraj + 
                                     "\nTrajanje obrade u ms: " + duration + 
                                     "\nSveukupan broj poruka: " + sveukupanBrojPoruka + 
                                     "\nUkupan broj poruka: " + ukupanBrojPoruka +
                                     "\nBroj ispravnih poruka: " + brojIspravnihPoruka +
                                     "\nBroj neispravnih poruka: " + brojNeispravnihPoruka + 
                                     "\nBroj ostalih poruka: " + brojOstalihPoruka + 
                                     "\nBroj preuzetih datoteka: ";
                ukupanBrojPoruka = 0;
                //saljiPoruku("server", nwtis_porukaAdresa, nwtis_porukaPredmet, tekstPoruke, session, store);
                if (folder.isOpen())
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
                long sleepTime;
                sleepTime = ((int) (interval * 1000) - duration);
                System.out.println("Spavanje: " + sleepTime);
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(0);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //deleteAllMessages();

    }
    
    public void deleteAllMessages(){
        
        Session session;
        Store store;
        Folder folder;
        Message[] messages;
        List<String> folders = new ArrayList<String>();
        folders.add("inbox");
        folders.add("Drafts");
        folders.add("NWTiS_IspravnePoruke");
        folders.add("NWTiS_NeispravnePoruke");
        folders.add("NWTiS_OstalePoruke");
        folders.add("Poslano");
        folders.add("Trash");
        for (String f : folders){
            try{
                session = Session.getDefaultInstance(System.getProperties(), null);
                store = session.getStore("imap");
                store.connect(emailPosluzitelj, korisnickoIme, korisnickaLozinka);
                // Get a handle on the default folder
                folder = store.getDefaultFolder();
                folder = folder.getFolder(f);
                
                //Reading the Email Index in Read / Write Mode
                folder.open(Folder.READ_WRITE);
                messages = folder.getMessages();
                System.out.println("FOLDER " + f + " DELETED.");
                //TODO brisanje poruke kod prolaza
                for (int messageNumber = 0; messageNumber < messages.length; messageNumber++) {
                    messages[messageNumber].setFlag(Flags.Flag.DELETED, true);
                }
                folder.close(true);
                store.close();
            } catch (NoSuchProviderException ex) {
                Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private String[] isMatchingRegex(String e1, String e2, String e3 ){
        Pattern p1 = Pattern.compile(regUser);
        Matcher m1 = p1.matcher(e1.trim());
        Pattern p2 = Pattern.compile(regPass);
        Matcher m2 = p2.matcher(e2.trim());
        Pattern p3 = Pattern.compile(regGalery);
        Matcher m3 = p3.matcher(e3.trim());
        System.out.println("E1 " + e1);
        System.out.println("E2 " + e2);
        System.out.println("E3 " + e3);
        if (m1.matches() && m2.matches() && m3.matches()){
            System.out.println("MATCHES.");
            String[] values = {m1.group(1), m2.group(1), m3.group(1)};
            return values;
        }
        return null;
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
                String pass = rs.getString("lozinka");
                if (ime.equals(username) && pass.equals(password)){
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
        return false;
    }
    
     public void saljiPoruku(String from, String to, String subject, String msg, Session session, Store store){
        try {

            MimeMessage message = new MimeMessage(session);
            Address fromAddress = new InternetAddress(from);
            message.setHeader("Content-Type", "text/html");
            message.setFrom(fromAddress);
            Address[] toAddresses = InternetAddress.parse(to);
            message.setRecipients(Message.RecipientType.TO, toAddresses);
            message.setSubject(subject);
            message.setText(msg);
            message.setSentDate(new Date());
            message.setContentID(new Date().toString() + "." + new Date().getTime());
            Transport.send(message);
            
            Folder f = store.getFolder(nwtis_porukaPoslanaMapa);
            if (!f.exists()){ 
                f.create(Folder.HOLDS_MESSAGES);
            }
            f.appendMessages(new Message[] {message});
            
        } catch (MessagingException ex) {
            //TODO baca error
        } 
    }    
}
