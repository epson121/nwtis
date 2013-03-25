/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;

/**
 * 
 * @author Luka Rajcevic
 */
public class ServerVremenaDretva extends Thread{
    
    private Socket client;
    private String serializeFileName;
    private Konfiguracija config;

    public ServerVremenaDretva(Socket client, String serializeFileName, Konfiguracija config) {
        this.client = client;
        this.serializeFileName = serializeFileName;
        this.config = config;
    }

    @Override
    public synchronized void start() {
        super.start(); 
        System.out.println("Thread started");
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        StringBuilder command;
        int character;
        try {
            is = client.getInputStream();
            os = client.getOutputStream();
            command = new StringBuilder();
            
            while ((character = is.read()) != -1){
                command.append((char) character);
                System.out.println("char: " + character);
            }
            System.out.println("command: " + command);
            //TODO provjera da li komanda zadovoljava regex i generiranje odgovora
            
            String response = "OK " +  new Date();
            os.write(response.getBytes());
            os.flush();
        } 
        catch (IOException e) {
            
        } 
        finally{
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerVremenaDretva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt(); 
    }
    
    

}
