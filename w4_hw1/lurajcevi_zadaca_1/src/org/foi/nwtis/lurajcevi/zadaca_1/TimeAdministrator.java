
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
 * Handles administrator commands from command line
 * @author Luka Rajcevic
 */
public class TimeAdministrator {
    
    private Konfiguracija config;
    private Log log;
    private int port;
    private String configFileName;
    private String serverIP;
    private String user;
    
    private String password;
    private String adminCommand;
    private String time;
    
    /**
     * 
     * @param port - port number of the server
     * @param configFileName - configuration file name
     * @param serverIP - ip adress of the server
     * @param user - username of the administrator
     * @param password - password of the administrator
     * @param adminCommand - administrators' command
     * @param time - if command is SETTIME, command line time
     */
    public TimeAdministrator(int port, String configFileName, String serverIP, String user, String password, String adminCommand, String time) {
        this.port = port;
        this.configFileName = configFileName;
        this.serverIP = serverIP;
        this.user = user;
        this.password = password;
        this.adminCommand = adminCommand;
        this.time = time;
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(configFileName);
            log = new Log(config.dajPostavku("dnevnik"));
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(TimeAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Handles  communication between administrator and server
     * Sends commands and receives responses
     */
    public void startAdministratorVremena(){
        Socket server = null;
        InputStream is = null;
        OutputStream os = null;
        StringBuilder response;
        int character;
            try {
                log.openLog();
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
                log.writeToLog("admin " + user + " has executed command : \n" + command + 
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
                    Logger.getLogger(TimeAdministrator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
      

}
