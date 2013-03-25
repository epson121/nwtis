/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Luka Rajcevic
 */
public class KlijentVremena {
    private int port;
    private String configFileName;
    private String serverIP;
    private String user;

    public KlijentVremena(int port, String configFileName, String serverIP, String user) {
        this.port = port;
        this.configFileName = configFileName;
        this.serverIP = serverIP;
        this.user = user;
    }
    
    public void startKlijentVremena(){
        InputStream is = null;
        OutputStream os = null;
        Socket server = null;
        try{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(KlijentVremena.class.getName()).log(Level.SEVERE, null, ex);
            }
            server = new Socket(serverIP, port);
            int character;
            is = server.getInputStream();
            os = server.getOutputStream();
            String command = "USER " + user + "; GETTIME";
            os.write(command.getBytes());
            os.flush();
            StringBuilder response = new StringBuilder();
            
            while ((character = is.read()) != -1){
                response.append((char) character);
            }
            System.out.println("debug 4");
            System.out.println("komanda " + command);
        }
        catch (UnknownHostException ex){
            System.out.println("catch");
            Logger.getLogger(KlijentVremena.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(KlijentVremena.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
                if (server != null)
                    server.close();
            }
            catch (IOException ex){
                //nothing to do
            }
                
        }
    }
}
