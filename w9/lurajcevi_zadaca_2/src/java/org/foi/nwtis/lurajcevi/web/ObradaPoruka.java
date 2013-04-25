/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;

/**
 *
 * @author nwtis_2
 */
public class ObradaPoruka extends Thread{
 
    private BP_Konfiguracija bpKonf;
    private String emailPosluzitelj;
    private String emailPort;
    private String korisnickoIme;
    private String korisnickaLozinka;
    private String trazeniPredmet;
    private int interval;
    
    public ObradaPoruka(){
        super();
    }
    
    @Override
    public synchronized void start() {
        //TODO preuzmi podatke iz konfiguracije
        super.start(); 
        emailPosluzitelj = "localhost";
        emailPort = "143";
        korisnickoIme = "servis@nastava.nwtis.foi.hr";
        korisnickaLozinka = "123456";
        trazeniPredmet = "NWTiS";
        interval = 300;
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
        while(true){
            
            try {
      printData("--------------processing mails started-----------------");
      session = Session.getDefaultInstance(System.getProperties(), null);
 
      printData("getting the session for accessing email.");
      store = session.getStore("imap");
 
      store.connect(emailPosluzitelj, korisnickoIme, korisnickaLozinka);
      printData("Connection established with IMAP server.");
 
      // Get a handle on the default folder
      folder = store.getDefaultFolder();
 
      printData("Getting the Inbox folder.");
 
      // Retrieve the "Inbox"
      folder = folder.getFolder("inbox");
 
      //Reading the Email Index in Read / Write Mode
      folder.open(Folder.READ_WRITE);
 
      // Retrieve the messages
      messages = folder.getMessages();
 
      // Loop over all of the messages
      for (int messageNumber = 0; messageNumber < messages.length; messageNumber++) {
           // Retrieve the next message to be read
	   message = messages[messageNumber];
 
           // Retrieve the message content
           messagecontentObject = message.getContent();
 
           // Determine email type
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
 
                   if (contentType.startsWith("text/plain")) {
			printData("---------reading content type text/plain  mail -------------");
		   } else {
			// Retrieve the file name
			String fileName = part.getFileName();
			printData("retrive the fileName="+ fileName);
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
 
            // Close the folder
            folder.close(true);

            // Close the message store
            store.close();
        } catch(AuthenticationFailedException e) {
           printData("Not able to process the mail reading.");
           e.printStackTrace();
        } catch(FolderClosedException e) {
           printData("Not able to process the mail reading.");
           e.printStackTrace();
        } catch(FolderNotFoundException e) {
           printData("Not able to process the mail reading.");
           e.printStackTrace();
        }  catch(NoSuchProviderException e) {
           printData("Not able to process the mail reading.");
           e.printStackTrace();
        } catch(ReadOnlyFolderException e) {
           printData("Not able to process the mail reading.");
           e.printStackTrace();
        } catch(StoreClosedException e) {
           printData("Not able to process the mail reading.");
           e.printStackTrace();
        } catch (Exception e) {
           printData("Not able to process the mail reading.");
           e.printStackTrace();
        }
            try {
                //TODO korekcija vremena spavanja za izvrsavanje iteracije
                sleep(interval * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ObradaPoruka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
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
