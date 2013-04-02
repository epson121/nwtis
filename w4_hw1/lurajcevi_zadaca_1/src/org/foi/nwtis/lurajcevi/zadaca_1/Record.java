/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Luka Rajcevic
 */
public class Record implements Serializable{
    
    private String user;
    private String command;
    private Date time;
    private String response;

    public Record(String user, String command, Date time, /*int status,*/ String response) {
        this.user = user;
        this.command = command;
        this.time = time;
        //this.status = status;
        this.response = response;
    }
    
    public String getUser() {
        return user;
    }

    public String getCommand() {
        return command;
    }

    public Date getTime() {
        return time;
    }

    public String getResponse() {
        return response;
    }
    
}