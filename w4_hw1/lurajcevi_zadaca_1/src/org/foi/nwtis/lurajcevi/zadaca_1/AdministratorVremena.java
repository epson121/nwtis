/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;

/**
 * 
 * @author Luka Rajcevic
 */
public class AdministratorVremena {
    
    private Konfiguracija config;
    private Dnevnik log;
    private int port;
    private String configFileName;
    private String serverIP;
    private String user;
    
    private String password;
    private String adminCommand;
    private String time;
    

    public AdministratorVremena(int port, String configFileName, String serverIP, String user, String password, String adminCommand, String time) {
        this.port = port;
        this.configFileName = configFileName;
        this.serverIP = serverIP;
        this.user = user;
        this.password = password;
        this.adminCommand = adminCommand;
        this.time = time;
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(configFileName);
            log = new Dnevnik(config.dajPostavku("dnevnik"));
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(AdministratorVremena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startAdministratorVremena(){
        Socket server = null;
        InputStream is = null;
        OutputStream os = null;
        StringBuilder response;
        int character;
            try {
                log.otvoriDnevnik();
                server = new Socket(serverIP, port);
                is = server.getInputStream();
                os = server.getOutputStream();

                if (time != null)
                    adminCommand += " " + time;
                String command = "USER " + user + "; PASSWD " + password + 
                                 "; " + adminCommand + ";";
                os.write(command.getBytes());
                os.flush();
                server.shutdownOutput();

                response = new StringBuilder();
                while ((character = is.read()) != -1){
                    response.append((char) character);
                }
                log.upisiZapis("admin " + user + " has executed command : \n" + command + 
                               "\nwith response:\n" + response + "\n\n");
                
                System.out.println("Admin got a response: " + response);
            }
            catch (IOException ex) {
                System.out.println("Server is not responding. Exiting now.");
            }
            finally{
                try {
                    if (is != null)
                        is.close();
                    if (os != null)
                        os.close();
                    if (server != null)
                        server.close();
                } catch (IOException ex) {
                    Logger.getLogger(AdministratorVremena.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
      

}
