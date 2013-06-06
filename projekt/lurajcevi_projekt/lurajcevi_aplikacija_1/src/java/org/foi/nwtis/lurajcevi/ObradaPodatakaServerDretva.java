
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
 * @document ObradaPodatakaServerDretva
 * @author Luka Rajcevic
 */
public class ObradaPodatakaServerDretva extends Thread{
    // standard
    private Socket client;
    private Konfiguracija config;
    
    //patters and matchers
    private Pattern p;
    private Matcher m;
    private boolean status;
    
    //regex
    private String userRegex = "^USER ([a-zA-Z0-9_]+)\\; GET ZIP (\\d\\d\\d\\d\\d)\\; *$"; 
    private String adminRegex = "^USER ([a-zA-Z0-9_]+)\\; PASSWD ([a-zA-Z0-9_]+)\\; (PAUSE\\;|START\\;|STOP\\;|TEST ZIP (\\d\\d\\d\\d\\d)\\;|GET ZIP (\\d\\d\\d\\d\\d)\\;|ADD ZIP (\\d\\d\\d\\d\\d);)\\ *$";
    
    // nazivi baza podataka
    private String activeZipCodes = "lurajcevi_activezipcodes";
    private String zip_podaci = "lurajcevi_podaci_zip";
    private String admin_podaci = "lurajcevi_admin_podaci";
    private String dnevnik_servera = "lurajcevi_dnevnik_servera";
    
    // mail podaci
    String trajanjePrethodnogStanja;
    int brojPrimljenihKomandi = 0;
    int brojNeispravnihKomandi = 0;
    int brojIzvrsenihKomandi = 0;
    
    public ObradaPodatakaServerDretva(Socket client, Konfiguracija config){
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
        String username = "";
        String response = "ERROR. Wrong command or username/password!";
        int character;
        long start, duration = 0;
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
            start = System.currentTimeMillis();
            brojPrimljenihKomandi++;
            if (isMatchingRegex(strCommand, adminRegex)){
                brojIzvrsenihKomandi++;
                username = m.group(1);
                String password = m.group(2);
                System.out.println("GRUPA 3:" + m.group(3));
                System.out.println("GRUPA 4:" + m.group(4));
                System.out.println("GRUPA 5:" + m.group(5));
                System.out.println("GRUPA 6:" + m.group(6));
                if (DBConnector.provjeriKorisnika(admin_podaci, username, password)){
                    String instr = m.group(3);
                    System.out.println("INSTR: " + instr);
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
                            interrupt();
                        } else {
                            response = "OK 42";
                        }
                    }  else if (instr.contains("ADD")){
                        String zip = m.group(6);
                        System.out.println("ZIP6: " + zip);
                        if (DBConnector.unesiUBazu(activeZipCodes, zip, username)){
                            response = "OK 10";
                        } else {
                            response = "OK 42";
                        }
                    } else if (instr.contains("TEST")){
                        String zip = m.group(4);
                        System.out.println("ZIP4: " + zip);
                        if (DBConnector.provjeriZip(activeZipCodes, zip)){
                            response = "OK 10";
                        } else {
                            response = "OK 44";
                        }
                    }
                }
            } else if (isMatchingRegex(strCommand, userRegex)){
                brojIzvrsenihKomandi++;
                username = m.group(1);
                String zip = m.group(2);
                if (DBConnector.provjeriZip(activeZipCodes, zip)){
                    response = "OK 10 " +  DBConnector.dohvatiPodatkeOZipKodu(zip_podaci, zip);
                } else {
                    response = "OK 43";
                }
            } else {
                brojNeispravnihKomandi++;
            }
            duration = System.currentTimeMillis() - start;
            DBConnector.unesiUDnevnikSocketServera("lurajcevi_dnevnik_servera", strCommand, response.substring(0, 5), username);
            //saljiPoruku(config.dajPostavku("primatelj"), config.dajPostavku("predmet"), duration);
            os.write(response.getBytes());
            os.flush();
        } 
        catch (IOException exc) {
            exc.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaPodatakaServerDretva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ObradaPodatakaServerDretva.class.getName()).log(Level.SEVERE, null, ex);
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
     public void saljiPoruku(String to, String subject, long trajanje){
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
            message.setText("Ukupan broj zahtjeva: " + brojPrimljenihKomandi +
                            "\nBroj izvršenih komandi: " + brojIzvrsenihKomandi +
                            "\nBroj neispravnih komandi: " + brojNeispravnihKomandi +
                            "\nTrajanje obrade: " + trajanje);
            Transport.send(message);
        } catch (AddressException ex) {
            Logger.getLogger(ObradaPodatakaServerDretva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ObradaPodatakaServerDretva.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
