package org.foi.nwtis.lurajcevi.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.FolderNotFoundException;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.ReadOnlyFolderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.StoreClosedException;
import javax.mail.internet.InternetAddress;
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
    private int interval;
    private static int counter = 1;

    public ObradaPoruka(String mailServer, String mailPort, String username, 
                        String password, String subject, int interval) {
        emailPosluzitelj = mailServer;
        emailPort = mailPort;
        korisnickaLozinka = password;
        korisnickoIme = username;
        trazeniPredmet = subject;
        this.interval = interval;
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
                            printData("Found Email with Attachment");
                            sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
                            // If the "personal" information has no entry, check the address for the sender information
                            printData("If the personal information has no entry, check the address for the sender information.");
                            if (sender == null) {
                                sender = ((InternetAddress) message.getFrom()[0]).getAddress();
                                printData("sender in NULL. Printing Address:" + sender);
                            }
                            printData("Sender -." + sender);
                            // Get the subject information
                            subject = message.getSubject();
                            printData("subject=" + subject);
                            // Retrieve the Multipart object from the message
                            multipart = (Multipart) message.getContent();
                            printData("Retrieve the Multipart object from the message");
                            // Loop over the parts of the email
                            for (int i = 0; i < multipart.getCount(); i++) {
                                // Retrieve the next part
                                part = multipart.getBodyPart(i);
                                // Get the content type
                                contentType = part.getContentType();
                                // Display the content type
                                printData("Content: " + contentType);
                                if (contentType.startsWith("text/plain")
                                        || contentType.startsWith("TEXT/PLAIN")) {
                                    System.out.println("PART is:\n" + part);

                                } else {
                                    // Retrieve the file name
                                    String fileName = part.getFileName();
                                    if (contentType.startsWith("image/")){
                                        System.out.println("Got image!!!");
                                    }
                                    printData("retrive the fileName=" + fileName);
                                }
                            }
                        } else {
                            printData("Found Mail Without Attachment");
                            sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
                            // If the "personal" information has no entry, check the address for the sender information
                            printData("If the personal information has no entry, check the address for the sender information.");
                            if (sender == null) {
                                sender = ((InternetAddress) message.getFrom()[0]).getAddress();
                                printData("sender in NULL. Printing Address:" + sender);
                            }
                            // Get the subject information
                            subject = message.getSubject();
                            printData("subject=" + subject);
                        }
                        
                    }
                    else{
                        //keyword not in subject
                    }
                
                }
                duration = System.currentTimeMillis() - start;
                //TODO send message
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
}
