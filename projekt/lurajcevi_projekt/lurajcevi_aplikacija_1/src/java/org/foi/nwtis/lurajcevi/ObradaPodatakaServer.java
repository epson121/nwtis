
package org.foi.nwtis.lurajcevi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.slusaci.SlusacAplikacije;


/**
 * @document ObradaPodatakaServer
 * @author Luka Rajcevic
 */
public class ObradaPodatakaServer extends Thread{
    
    private ObradaPodatakaServerDretva ops = null;
    private Konfiguracija config = null;
    
    public ObradaPodatakaServer(Konfiguracija config){
        this.config = config;
    }
    
    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(Integer.parseInt(config.dajPostavku("port")));
            server.setSoTimeout(1000);
            
            while (!SlusacAplikacije.isStopped()){
                try{
                    System.out.println("listening");
                    Socket client = server.accept();
                    System.out.println("--------------------------------------------");
                    System.out.println("Request has been received. Now responding...");
                    ops = new ObradaPodatakaServerDretva(client, config);
                    ops.start();
                } catch(SocketTimeoutException ste){
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ObradaPodatakaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
