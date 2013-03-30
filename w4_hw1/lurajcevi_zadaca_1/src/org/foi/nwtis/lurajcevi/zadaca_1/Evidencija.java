/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Luka Rajcevic
 */
public class Evidencija implements Serializable{
    
    private String user;
    private String command;
    private Date time;
    //private int status;
    private String statusDescription;

    public Evidencija(String user, String command, Date time, /*int status,*/ String statusDescription) {
        this.user = user;
        this.command = command;
        this.time = time;
        //this.status = status;
        this.statusDescription = statusDescription;
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

    /*
    public int getStatus() {
        return status;
    }
    */
    public String getStatusDescription() {
        return statusDescription;
    }
}