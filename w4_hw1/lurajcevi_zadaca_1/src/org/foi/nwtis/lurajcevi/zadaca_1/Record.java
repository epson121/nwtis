
package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.Serializable;
import java.util.Date;

/**
 * Holds attributes that are to be written in a Record file.
 * @author Luka Rajcevic
 * 
 */
public class Record implements Serializable{
    
    private String user;
    private String command;
    private Date time;
    private String response;

    /**
     * Constructor for the Record class
     * @param user - user attribute
     * @param command - command attribute
     * @param time - time attribute 
     * @param response - response attribute
     */
    public Record(String user, String command, Date time, /*int status,*/ String response) {
        this.user = user;
        this.command = command;
        this.time = time;
        this.response = response;
    }
    /**
     * 
     * @return object user (String)
     */
    public String getUser() {
        return user;
    }
    
    /**
     * 
     * @return object command (String)
     */
    public String getCommand() {
        return command;
    }
    
    /**
     * 
     * @return object time (Date)
     */
    public Date getTime() {
        return time;
    }
    
    /**
     * 
     * @return object response (String)
     */
    public String getResponse() {
        return response;
    }
    
}