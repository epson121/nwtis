
package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * implements methods for Log (opening, closing and writing to it)
 * @author Luka Rajcevic
 */
public class Log {
    
    private String fileName;
    File outputFile = null;
    FileOutputStream out = null;
    
    /**
     * Constructor for Log class
     * @param fileName - name of the log file
     */
    public Log(String fileName) {
        this.fileName = fileName;
        outputFile = new File (this.fileName);
    }
    
    /**
     * 
     * @return true if Log successfully opened, false otherwise
     */
    public boolean openLog(){
        try {
            out = new FileOutputStream(outputFile, true);
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * 
     * @param zapis - string to be written to log
     * @return - true if successfully written, false otherwise
     */
    public boolean writeToLog(String zapis){
        byte[] z = zapis.getBytes();
        try {
            out.write(z);
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * @return true if successfully closed false otherwise
     * 
     */
    public boolean closeLog(){
       try {
           if (out != null)
               out.close();
       } 
       catch (IOException ex) {
           Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
       }
       return true;
   }

}
