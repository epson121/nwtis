/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.klijent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;

/**
 *
 * @author Luka Rajcevic
 */
public class Lurajcevi_aplikacija_1_1 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        try {
            InputStream is = null;
            OutputStream os = null;
            Socket server = null;
            int character;
            
            String serverIP = KonfiguracijaApstraktna.preuzmiKonfiguraciju("lurajcevi_projekt_config.xml").dajPostavku("server_ip");
            int port = Integer.parseInt(KonfiguracijaApstraktna.preuzmiKonfiguraciju("lurajcevi_projekt_config.xml").dajPostavku("port"));
            while (true){
                try{
                    server = new Socket("127.0.0.1", 20000);
                    os = server.getOutputStream();
                    is = server.getInputStream();
                    Scanner s = new Scanner(System.in);
                    String command = s.nextLine();
                    os.write(command.getBytes());
                    os.flush();
                    server.shutdownOutput();
                    
                    StringBuilder response = new StringBuilder();
                    while ((character = is.read()) != -1){
                        response.append((char) character);
                    }
                   
                    System.out.println("RESPONSE: " + response);
                    
                }
                catch (IOException ex) {
                    System.out.println("ERROR: server is not responding.");
                    ex.printStackTrace();
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
               
                
            }
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(Lurajcevi_aplikacija_1_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
