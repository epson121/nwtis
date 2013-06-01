
package org.foi.nwtis.lurajcevi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.foi.nwtis.lurajcevi.db.DBConnector;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.slusaci.SlusacAplikacije;


/**
 * @document ObradaPodatakaServer
 * @author Luka Rajcevic
 */
public class ObradaPodatakaServer extends Thread{
    
    private Socket client;
    private Konfiguracija config;
    
    private Pattern p;
    private Matcher m;
    private boolean status;
    
    private String userRegex = "^USER ([a-zA-Z0-9_]+)\\; GET ZIP (\\d\\d\\d\\d\\d)\\; *$"; 
    private String adminRegex = "^USER ([a-zA-Z0-9_]+)\\; PASSWD ([a-zA-Z0-9_]+)\\; (PAUSE\\;|START\\;|STOP\\;|TEST ZIP (\\d\\d\\d\\d\\d)\\;|GET ZIP (\\d\\d\\d\\d\\d)\\;|ADD ZIP (\\d\\d\\d\\d\\d)\\; *$)";
    
    // nazivi baza podataka
    private String activeZipCodes = "lurajcevi_activezipcodes";
    private String zip_podaci = "lurajcevi_zip_podaci";
    private String admin_podaci = "lurajcevi_admin_podaci";
    
    public ObradaPodatakaServer(Socket client, Konfiguracija config){
        this.client = client;
        this.config = config;
    }
    
    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        StringBuilder command;
        String response = "ERROR. Wrong command or username/password!";
        int character;
        try {
            is = client.getInputStream();
            os = client.getOutputStream();
            command = new StringBuilder();
            while (true){
                character = is.read();
                if (character == -1 )
                    break;
                command.append((char) character);
            }
            String strCommand = command.toString().trim();
            if (isMatchingRegex(strCommand, adminRegex)){
                String username = m.group(1);
                String password = m.group(2);
                if (DBConnector.provjeriKorisnika(admin_podaci, username, password)){
                    //TODO start time
                    String instr = m.group(3);
                    if (instr.equals("PAUSE;")){
                        if (!SlusacAplikacije.isPaused()){
                            SlusacAplikacije.setPaused(true);
                            response = "OK 10";
                        } else {
                            response = "OK 40";
                        }
                    } else if (instr.equals("START;")){
                        if (SlusacAplikacije.isPaused()){
                            SlusacAplikacije.setPaused(false);
                            response = "OK 10";
                        } else {
                            response = "OK 41";
                        }
                    } else if (instr.equals("STOP;")){
                        if (!SlusacAplikacije.isStopped()){
                            SlusacAplikacije.setStopped(true);
                            response = "OK 10";
                        } else {
                            response = "OK 42";
                        }
                    }  else if (instr.contains("ADD ZIP")){
                        String zip = m.group(4);
                        if (DBConnector.unesiUBazu(activeZipCodes, zip, username)){
                            response = "OK 10";
                        } else {
                            response = "OK 42";
                        }
                    } else if (instr.contains("TEST ZIP")){
                        String zip = m.group(4);
                        if (DBConnector.provjeriZip(activeZipCodes, zip)){
                            response = "OK 10";
                        } else {
                            response = "OK 44";
                        }
                    }
                    //TODO stop time
                }
            } else if (isMatchingRegex(strCommand, userRegex)){
                String username = m.group(1);
                String zip = m.group(2);
                if (DBConnector.provjeriZip(activeZipCodes, zip)){
                    response = "OK 10 " +  DBConnector.dohvatiPodatkeOZipKodu(zip_podaci, zip);
                } else {
                    response = "OK 43";
                }
            }
            saljiPoruku(config.dajPostavku("primatelj"), config.dajPostavku("predmet"));
            os.write(response.getBytes());
            os.flush();
        } 
        catch (IOException exc) {
            exc.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaPodatakaServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ObradaPodatakaServer.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally{
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void interrupt() {
    }
    
    /**
     * matches admin commands with appropriate regex
     * @param command - admin command
     * @return true or false
     */
    public boolean isMatchingRegex(String command, String regex){
        p = Pattern.compile(regex);
        m = p.matcher(command);
        status = m.matches();
        if (status){
            return true;
        }
        return false;
    }
    
        /**
     * prima sve potrebne argumente za slanje email poruke
     * @param from - posiljatelj
     * @param to - primatelj
     * @param subject - naslov
     * @param msg - tekst poruke
     * @param session - sesija koja salje poruku
     * @param store  - store koji salje poruku
     */
     public void saljiPoruku(String to, String subject){
        try {
            Date d = new Date();
            String id = d + "." + d.getTime();
            
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "127.0.0.1");
            Session session = Session.getInstance(properties, null);
            MimeMessage message = new MimeMessage(session);
            Address fromAddress = new InternetAddress("system_message");
            message.setFrom(fromAddress);
            Address[] toAddresses = InternetAddress.parse(to);
            message.setRecipients(Message.RecipientType.TO,toAddresses);
            message.setHeader("Message-ID", id);
            /*
             * s informacijama o komandi (vrijeme izvršavanja, trajanje prethodnog
             * stanja, broj primljenih, neispravnih i izvršenih korisničkih komandi).
             * 
             */
            message.setHeader("Content-Type", "text/html");
            message.setSentDate(new Date());
            message.setSubject(subject);
            message.setText("PORUKA");
            Transport.send(message);
        } catch (AddressException ex) {
            Logger.getLogger(ObradaPodatakaServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ObradaPodatakaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
