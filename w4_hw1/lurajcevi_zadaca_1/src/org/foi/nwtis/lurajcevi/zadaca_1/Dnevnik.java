/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Luka Rajcevic
 */
public class Dnevnik {
    
    private String fileName;
    File outputFile = null;
    FileOutputStream out = null;
    
    public Dnevnik(String fileName) {
        this.fileName = fileName;
        outputFile = new File (this.fileName);
    }
    
    public boolean otvoriDnevnik(){
        try {
            out = new FileOutputStream(outputFile, true);
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dnevnik.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean upisiZapis(String zapis){
        byte[] z = zapis.getBytes();
        try {
            out.write(z);
        } catch (IOException ex) {
            Logger.getLogger(Dnevnik.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
     public boolean zatvoriDnevnik(){
        try {
            if (out != null)
                out.close();
        } 
        catch (IOException ex) {
            Logger.getLogger(Dnevnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
     
    
     
    

}
