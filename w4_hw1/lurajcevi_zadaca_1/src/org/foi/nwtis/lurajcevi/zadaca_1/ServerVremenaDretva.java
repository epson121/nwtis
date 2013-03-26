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
    
    private String FILE_NAME = "dnevnik.txt";
    private Socket client;
    private String serializeFileName;
    private Konfiguracija config;
    private Dnevnik dnevnik;

    public ServerVremenaDretva(Socket client, String serializeFileName, Konfiguracija config) {
        this.client = client;
        this.serializeFileName = serializeFileName;
        this.config = config;
        dnevnik = new Dnevnik(FILE_NAME);
        dnevnik.otvoriDnevnik();
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
        dnevnik.upisiZapis("preparing to read characters!\n");
        System.out.println("preparing to read characters!");
        try {
            is = client.getInputStream();
            os = client.getOutputStream();
            command = new StringBuilder();
            
            while (is.available() > 0){
                character = is.read();
                if (character == -1 )
                    break;
                command.append((char) character);
            }
            String strCommand = command.toString().trim();
            if (strCommand.endsWith("GETTIME")){
                String response = "OK " +  new Date();
                os.write(response.getBytes());
                os.flush();
            }
            dnevnik.upisiZapis("DONE\n");
            System.out.println("DONE");
        } 
        catch (IOException e) {
            System.out.println("ERROR");
        } 
        finally{
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
                dnevnik.zatvoriDnevnik();
            } catch (IOException ex) {
                Logger.getLogger(ServerVremenaDretva.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR");
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt(); 
    }
    
    

}
