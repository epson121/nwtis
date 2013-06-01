
package org.foi.nwtis.lurajcevi.web;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;

/**
 * @document ObradaMeteoPodatakaServer
 * @author Luka Rajcevic
 */
public class ObradaMeteoPodatakaServer {
    
    private int port;
    private static boolean paused = false;
    private static boolean stopped = false;
    
    public ObradaMeteoPodatakaServer(Konfiguracija conf){
        this.port = Integer.parseInt(conf.dajPostavku("port"));
        
    }
    
    public void pokreniServer(){
         try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(1000);
            while (!stopped){
                try{
                Socket client = server.accept();
                System.out.println("--------------------------------------------");
                System.out.println("Request has been received. Now responding...");
                ObradaMeteoPodatakaServerDretva opServerDretva = 
                        new ObradaMeteoPodatakaServerDretva(client);
                }
                catch (SocketTimeoutException ex){
                    //do nothing
                }
            }
        } catch (Exception e) {
        }
    }

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean paused) {
        ObradaMeteoPodatakaServer.paused = paused;
    }

    public static boolean isStopped() {
        return stopped;
    }

    public static void setStopped(boolean stopped) {
        ObradaMeteoPodatakaServer.stopped = stopped;
    }
    
    
}
