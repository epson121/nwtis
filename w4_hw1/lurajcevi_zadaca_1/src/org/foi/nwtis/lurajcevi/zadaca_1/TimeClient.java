/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;

/**
 * 
 * @author Luka Rajcevic
 */
public class TimeClient {
    private int port;
    private String configFileName;
    private String serverIP;
    private String user;
    
    private Konfiguracija config;

    /**
     * 
     * @param port - port number
     * @param configFileName - name of the configuration file
     * @param serverIP - ip adress of the server
     * @param user - username
     */
    public TimeClient(int port, String configFileName, String serverIP, String user) {
        this.port = port;
        this.configFileName = configFileName;
        this.serverIP = serverIP;
        this.user = user;
         try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(configFileName);
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(TimeAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 
     */
    
    public void startTimeClient(){
        
        int threadCount = Integer.parseInt(config.dajPostavku("brojDretvi"));
        int pauseTime = (int) (new java.util.Random().nextDouble() * Integer.parseInt(config.dajPostavku("pauza"))); 
        
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++){
            Thread thr = new TimeClient_Thread(port, configFileName, serverIP, user);
            threads.add(thr);
            thr.start();
            try {
                Thread.sleep(pauseTime * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
