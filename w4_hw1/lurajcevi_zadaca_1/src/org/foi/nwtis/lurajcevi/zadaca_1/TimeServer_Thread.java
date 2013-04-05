
package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;

/**
 * Handles server connections. Gets commands from user/admin and handles them appropriately.
 * @author Luka Rajcevic
 * 
 */
public class TimeServer_Thread extends Thread{
    
    private String setTimeRegex = "^USER ([a-zA-Z0-9_]+)\\; PASSWD ([a-zA-Z0-9_]+)\\; SETTIME (\\d\\d.\\d\\d.\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d)\\; *$";
    private String adminCommandRegex = "^USER ([a-zA-Z0-9_]+)\\; PASSWD ([a-zA-Z0-9_]+)\\; (START|STOP|CLEAN|PAUSE)\\; *$";
    private String userRegex = "^USER ([a-zA-Z0-9_]+)\\; GETTIME\\; *$";
    
    private Pattern p;
    private Matcher m;
    private boolean status;
    private static long msecServerDifference = 0;
    
    private Socket client;
    private String serializeFileName;
    private Konfiguracija config;
    private Log dnevnik;
    private DateFormat df;

    /**
     * constructor for TimeServer_Thread class
     * @param client - socket 
     * @param serializeFileName - file name of the serialized data
     * @param config - configuration file name
     */
    public TimeServer_Thread(Socket client, String serializeFileName, Konfiguracija config) {
        this.client = client;
        this.serializeFileName = serializeFileName;
        this.config = config;
        df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    }
    
    /**
     * overridden thread start method
     */
    @Override
    public synchronized void start() {
        super.start(); 
    }
    
    /**
     * overridden thread run method. handles connections from user/administrator
     */
    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        StringBuilder command;
        String response;
        int character;
        Record rec;
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
            if (command.indexOf("GETTIME") != -1){
                p = Pattern.compile(userRegex);
                m = p.matcher(command);
                status = m.matches();
                if (status){
                    if (TimeServer.isPaused() || TimeServer.isStopped())
                        response = "ERROR: Server is not accepting requests at the moment.";
                    else{
                        response = "OK " +  df.format(getServerTime());
                    }
                }
                else{
                    response = "ERROR: Bad command type.";
                }
                rec = new Record(m.group(1), strCommand, getServerTime(), response);
                RecordSerialization.record.add(rec);
            }
            else if (command.indexOf("START") != -1){
                if (isMatchingRegex(command)){
                    if (verifyCredentials(m.group(1), m.group(2))){
                        if (TimeServer.isPaused()){
                            TimeServer.setPaused(false);
                            response = "OK";
                            System.out.println("Server is starting!");
                        }
                        else{
                            response = "ERROR: server is already started.";
                        }
                    }
                    else{
                        response = "ERROR: Wrong username or password.";
                    }
                }
                else{
                    response = "ERROR: bad command.";
                }
            }
            else if (command.indexOf("STOP") != -1){
                if (isMatchingRegex(command) && verifyCredentials(m.group(1), m.group(2))){
                    if (!TimeServer.isStopped()){
                        TimeServer.setStopped(true);
                        response = "OK";
                        System.out.println("Server is stopping!");
                        interrupt();
                    }
                    else{
                        response = "ERROR: Server is already stopped.";
                    }
                }
                else{
                    response = "ERROR: Wrong username or password.";
                }
            }
            else if (command.indexOf("PAUSE") != -1){
                if (isMatchingRegex(command) && verifyCredentials(m.group(1), m.group(2))){
                    if (!TimeServer.isPaused()){
                        TimeServer.setPaused(true);
                        response = "OK";
                        System.out.println("Server is pausing!");
                    }
                    else{
                        response = "ERROR: Server is already paused.";
                    }
                }
                else{
                    response = "ERROR: wrong username or password.";
                }
            }
            else if (command.indexOf("CLEAN") != -1){
                if (isMatchingRegex(command) && verifyCredentials(m.group(1), m.group(2))){
                   RecordSerialization.clearSerializationFile(serializeFileName);
                   response = "OK";
                }
                else{
                    response = "ERROR: wrong username or password.";
                }
            }
            else if (command.indexOf("SETTIME") != -1){
                p = Pattern.compile(setTimeRegex);
                m = p.matcher(command);
                status = m.matches();
                if (status){
                    if (verifyCredentials(m.group(1), m.group(2))){
                        msecServerDifference += getMsecDifference(df.format(getServerTime()), m.group(3));
                        System.out.println("New time is: " + getServerTime());
                        response = "OK";
                    }
                    else{
                        response = "ERROR: wrong username or password.";
                    }
                    rec = new Record(m.group(1), strCommand, getServerTime(), response);
                    RecordSerialization.record.add(rec);
                }
                else
                    response = "ERROR: bad command ";
            }
            else{
                response = "Wrong input.";
            }
            os.write(response.getBytes());
            os.flush();
        } 
        catch (IOException exc) {
            System.out.println("ERROR");
        } catch (ParseException ex) {
            Logger.getLogger(TimeServer_Thread.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally{
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
            } catch (IOException ex) {
                Logger.getLogger(TimeServer_Thread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR");
            }
        }
    }
    
    /**
     * overrides standard Thread interrupt method
     */
    @Override
    public void interrupt() {
        super.interrupt(); 
    }
    
    /**
     * verifies admin credentials
     * @param username - admins' username
     * @param password - admins' password
     * @return true or false
     */
    public boolean verifyCredentials(String username, String password){
        if (username.equals(config.dajPostavku("admin")) && password.equals(config.dajPostavku("lozinka"))){
            return true;
        }
        return false;
    }
    
    /**
     * matches admin commands with appropriate regex
     * @param command - admin command
     * @return true or false
     */
    public boolean isMatchingRegex(StringBuilder command){
        p = Pattern.compile(adminCommandRegex);
        m = p.matcher(command);
        status = m.matches();
        if (status){
            return true;
        }
        return false;
    }
    
    /**
     * gets difference in miliseconds between 2 dates
     * @param date1 - first date
     * @param date2 - second date
     * @return - long difference 
     * @throws ParseException - if date is not in correct format
     */
    public long getMsecDifference(String date1, String date2) throws ParseException{
        Date d1 = df.parse(date1);
        Date d2 = df.parse(date2);
        long diff = d2.getTime() - d1.getTime();        
        return diff;
    }
    
    public synchronized static Date getServerTime(){
        return new Date(new Date().getTime() + msecServerDifference);
    }
 
}
