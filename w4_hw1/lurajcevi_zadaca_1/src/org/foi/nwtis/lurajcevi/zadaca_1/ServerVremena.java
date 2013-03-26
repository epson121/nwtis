/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.Serializable;
import java.net.ServerSocket;
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
public class ServerVremena implements Serializable{
    private int port;
    private String configFileName;
    private boolean load;
    private String serializeFileName;
    private Konfiguracija config;

    /**
     * 
     * @param port - port number
     * @param configFileName - name of the configuration file
     * @param load - boolean value (is load parameter included)
     * @param serializeFileName  - serialization file name
     */
    public ServerVremena(int port, String configFileName, boolean load, String serializeFileName) {
        this.port = port;
        this.configFileName = configFileName;
        this.load = load;
        this.serializeFileName = serializeFileName;
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(configFileName);
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(ServerVremena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startServerVremena(){
        
        try {
            ServerSocket server = new ServerSocket(port);
            while (true){
                Socket client = server.accept();
                System.out.println("Klijent se javio");
                ServerVremenaDretva svd = new ServerVremenaDretva(  client 
                                                                  , serializeFileName
                                                                  , config);
                svd.start();
            }
        } catch (Exception e) {
        }
        
    }
    
    
}
