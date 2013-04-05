
package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;

/**
 * Class that handles client thread job. Communicates with server, 
 * writes to log, etc.
 * @author Luka Rajcevic
 */
public class TimeClient_Thread extends Thread {
    
    private int port;
    private String configFileName;
    private String serverIP;
    private String user;
    private static int threadCount = 1;
    private int threadTryCount = 0;
    private int threadMaxTryCount;
    
    private Konfiguracija config;
    private Log log;
    private SimpleDateFormat df;

    /**
     * Constructor for TimeClient_Thread class
     * @param port - servers port number
     * @param configFileName - configuration file name
     * @param serverIP - IP address of the server
     * @param user - client username
     */
    public TimeClient_Thread(int port, String configFileName, String serverIP, String user) {
        this.port = port;
        this.configFileName = configFileName;
        this.serverIP = serverIP;
        this.user = user;
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(configFileName);
            log = new Log(config.dajPostavku("dnevnik"));
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(TimeAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        threadMaxTryCount = Integer.parseInt(config.dajPostavku("brojPokusaja"));
        df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        
        setName("#" + threadCount++);
    }
    
    /**
     * Overridden thread start() method
     */
    @Override
    public synchronized void start() {
        super.start(); 
        log.openLog();
        log.writeToLog("Thread name:" + getName() + "\nStart time:" + df.format(new Date()) + "\n");
        log.closeLog();
    }

    /**
     * Overridden thread run method, Communicates with server.
     */
    @Override
    public void run() {
        String command = "USER " + user + "; GETTIME;";
        InputStream is = null;
        OutputStream os = null;
        Socket server = null;
        int character;
        long start, duration = 0;
        while (true){
            try{
                start = System.currentTimeMillis();
                server = new Socket(serverIP, port);
                os = server.getOutputStream();
                is = server.getInputStream();

                os.write(command.getBytes());
                os.flush();
                server.shutdownOutput();
                
                StringBuilder response = new StringBuilder();
                while ((character = is.read()) != -1){
                    response.append((char) character);
                }
                if (response.indexOf("ERROR") != -1)
                    threadTryCount++;
                System.out.println("Thread " + getName() + " received response: " +  response);
                
                log.openLog();
                if (response.indexOf("OK") != -1){
                    String serverTime = response.toString().substring(3);
                    log.writeToLog("Thread name:" + getName() + "\nThread time: " + 
                    df.format(new Date()) + "\nServer time: " + 
                    serverTime + "\nResponse: " +
                    response + "\n--------------------------------\n");
                }
                else{
                    log.writeToLog("Thread name:" + getName() + "\nThread time: " + 
                    df.format(new Date()) + "\nServer time: ERROR." + "\nResponse: " +
                    response + "\n--------------------------------\n");
                }
                log.closeLog();
                
                duration = System.currentTimeMillis() - start;
            }
            catch (IOException ex) {
                System.out.println("ERROR: server is not responding.");
                threadTryCount++;
            }
            finally{
                try{
                    if (os != null)
                        os.close();
                    if (is != null)
                        is.close();
                    if (server != null)
                        server.close();
                }
                catch (IOException ex){
                }
            }
            if (threadTryCount >= threadMaxTryCount){
                System.out.println(getName() + " is stopping due to " + 
                                   "maximum number of tries achieved.");
                log.openLog();
                log.writeToLog(getName() + " is stopping due to " + 
                                   "maximum number of tries achieved.\n");
                log.closeLog();
                break;
            }
            try {
                int interval;
                interval = (int) ((int) (Integer.parseInt(config.dajPostavku("interval")) * 1000) - duration);
                System.out.println("Spavanje: " + interval);
                sleep(interval);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeClient_Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Overridden thread interrupt method
     */
    @Override
    public void interrupt() {
        super.interrupt(); 
    }

}
