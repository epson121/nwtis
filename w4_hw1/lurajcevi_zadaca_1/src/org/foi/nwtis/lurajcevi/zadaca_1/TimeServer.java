
package org.foi.nwtis.lurajcevi.zadaca_1;
 
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;

/**
 * class for Server. Starts server Threads.
 * @author Luka Rajcevic
 */
public class TimeServer {
    private int port;
    private String configFileName;
    private boolean load;
    private String serializeFileName;
    private Konfiguracija config;
    private Record ev;
    
    private static boolean paused = false;
    private static boolean stopped = false;

    /**
     * 
     * @param port - port number
     * @param configFileName - name of the configuration file
     * @param load - boolean value (is load parameter included)
     * @param serializeFileName  - serialization file name
     */
    public TimeServer(int port, String configFileName, boolean load, String serializeFileName) {
        this.port = port;
        this.configFileName = configFileName;
        this.load = load;
        this.serializeFileName = serializeFileName;
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(configFileName);
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(TimeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (load){
            RecordSerialization.deserializeFromFile(serializeFileName);
        }
    }
    
    /**
     * Starts the server thread on connection
     */
    public void startTimeServer(){
        
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(1000);
            while (!stopped){
                try{
                Socket client = server.accept();
                System.out.println("--------------------------------------------");
                System.out.println("Request has been received. Now responding...");
                TimeServer_Thread svd = new TimeServer_Thread(  client 
                                                                , serializeFileName
                                                                , config);
                svd.start();
                }
                catch (SocketTimeoutException ex){
                    //do nothing
                }
            }
            RecordSerialization.serializeToFile(serializeFileName);
        } catch (Exception e) {
        }
        
    }
    
    /**
     * checks if server is paused
     * @return true or false
     */
    public static boolean isPaused() {
        return paused;
    }

    /**
     * sets server to paused or unpaused
     * @param paused - true or false
     */
    public static synchronized void setPaused(boolean paused) {
        TimeServer.paused = paused;
    }
    
    /**
     * checks if server is stopped
     * @return true or false
     */
    public static boolean isStopped() {
        return stopped;
    }
    
    /**
     * sets server to stopped
     * @param stopped - true
     */
    public static synchronized void setStopped(boolean stopped) {
        TimeServer.stopped = stopped;
    }
    
    
}
