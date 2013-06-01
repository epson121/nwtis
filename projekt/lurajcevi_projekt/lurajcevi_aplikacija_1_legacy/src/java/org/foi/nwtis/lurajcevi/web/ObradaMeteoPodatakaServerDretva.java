
package org.foi.nwtis.lurajcevi.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @document ObradaMeteoPodatakaServerDretva
 * @author Luka Rajcevic
 */
public class ObradaMeteoPodatakaServerDretva extends Thread{
    
    private Socket client;
    
    private Pattern p;
    private Matcher m;
    private boolean status;
    
    private String userRegex = "^USER ([a-zA-Z0-0_]+)\\; GET ZIP (\\d\\d\\d\\d\\d)\\; *$"; 
    private String adminRegex = "^USER ([a-zA-Z0-0_]+)\\; PASSWD ([a-zA-Z0-0_]+)\\; (PAUSE\\;|START\\;|STOP\\;|ADD ZIP (\\d\\d\\d\\d\\d)) *$)"; 
    public ObradaMeteoPodatakaServerDretva(Socket client){
        this.client = client;
    }
    
    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        StringBuilder command;
        String response;
        int character;
        try {
            is = client.getInputStream();
            os = client.getOutputStream();
            command = new StringBuilder();
            while (true){
                character = is.read();
                if (character == -1 )
                    break;
                command.append((char) character);
            }
            String strCommand = command.toString().trim();
            
            //TODO handle command
            //change boolean variables
            
            //os.write(response.getBytes());
            os.flush();
        } 
        catch (IOException exc) {
            System.out.println("ERROR");
        } 
        finally{
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
            } catch (IOException ex) {
                Logger.getLogger(ObradaMeteoPodatakaServerDretva.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR");
            }
        }
    }

    @Override
    public void interrupt() {
    }
    
}
