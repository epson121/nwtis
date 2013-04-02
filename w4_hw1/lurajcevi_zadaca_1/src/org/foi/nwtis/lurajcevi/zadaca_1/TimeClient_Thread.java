/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * 
 * @author Luka Rajcevic
 */
public class TimeClient_Thread extends Thread {
    
    private int port;
    private String configFileName;
    private String serverIP;
    private String user;
    private static int threadCount = 1;
    private int threadTryCount = 0;
    private int threadMaxTryCount = 5;
    
    private Konfiguracija config;
    private Log log;
    private SimpleDateFormat df;

    public TimeClient_Thread(int port, String configFileName, String serverIP, String user) {
        this.port = port;
        this.configFileName = configFileName;
        this.serverIP = serverIP;
        this.user = user;
        setName("#" + threadCount++);
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(configFileName);
            log = new Log(config.dajPostavku("dnevnik"));
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(TimeAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        threadMaxTryCount = Integer.parseInt(config.dajPostavku("brojPokusaja"));
        df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    }

    @Override
    public synchronized void start() {
        super.start(); 
        log.otvoriDnevnik();
        log.upisiZapis("Thread name:" + getName() + "\nStart time:" + df.format(new Date()) + "\n");
        log.zatvoriDnevnik();
    }

    @Override
    public void run() {
        String command = "USER " + user + "; GETTIME;";
        InputStream is = null;
        OutputStream os = null;
        Socket server = null;
        int character;
        long start = 0, duration = 0;
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
                //TODO write to dnevnik zapisuje u dnevnik svoju oznaku, vlastito vrijeme, vrijeme od servera, tekst poruke
                log.otvoriDnevnik();
                log.upisiZapis("Thread name:" + getName() + "\nThread time: " + 
                               df.format(new Date()) + "\nServer time: " + 
                               df.format(TimeServer_Thread.getServerTime()) + "\nResponse: " +
                               response + "\n--------------------------------\n");
                log.zatvoriDnevnik();
                
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

    @Override
    public void interrupt() {
        super.interrupt(); 
    }
    
    
    
    

}
