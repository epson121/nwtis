/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * @author Luka Rajcevic
 */
public class KlijentVremena {
    private int port;
    private String configFileName;
    private String serverIP;
    private String user;

    /**
     * 
     * @param port - port number
     * @param configFileName - name of the configuration file
     * @param serverIP - ip adress of the server
     * @param user - username
     */
    public KlijentVremena(int port, String configFileName, String serverIP, String user) {
        this.port = port;
        this.configFileName = configFileName;
        this.serverIP = serverIP;
        this.user = user;
    }
    /**
     * 
     */
    public void startKlijentVremena(){
        String command = "USER " + user + "; GETTIME";
        InputStream is = null;
        OutputStream os = null;
        Socket server = null;
        try{
            server = new Socket(serverIP, port);
            int character;
            os = server.getOutputStream();
            is = server.getInputStream();
            byte[] bytes = command.getBytes();
            os.write(bytes);
            os.flush();
            StringBuilder response = new StringBuilder();
            
            while ((character = is.read()) != -1 || is.available() > 0){
                response.append((char) character);
            }
            System.out.println(response);
        }
        catch (IOException ex) {
            //Logger.getLogger(KlijentVremena.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR, Exception has occured");
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
                //nothing to do
            }
                
        }
    }
}
