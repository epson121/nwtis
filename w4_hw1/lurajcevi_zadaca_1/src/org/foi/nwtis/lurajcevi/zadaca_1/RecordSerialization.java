
package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements static methods serialization handling (writing, reading, cleaning,
 * converting to text files)
 * @author Luka Rajcevic
 */
public class RecordSerialization implements Serializable {
    
    /**
     * stores list of Record type object for serialization
     */
    public static List<Record> record = new ArrayList<>();

    /**
     * Serializes List<Record> to a file
     * @param filename  - writes serialized data to this file
     */
    public static void serializeToFile(String filename){
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
        try {
            fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(record);
        } catch (IOException ex) {
            System.out.println("Failed to serialize.");
            return;
        }
        finally{
            try {
                if (objectOutputStream != null)
                    objectOutputStream.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(RecordSerialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Serialized.");
    }
    /**
     * Reads from serialized file into the List<Record> type variable
     * @param filename - reads from this file
     */
    public static void deserializeFromFile(String filename){
        List<Record> records = new ArrayList<>();
        FileInputStream fileInputStream = null;
        ObjectInputStream in = null;
        try {
            fileInputStream = new FileInputStream(filename);
            in = new ObjectInputStream(fileInputStream);
            records =  (List<Record>) in.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            System.out.println("Error while loading " + filename + ". No file, or the file empty.");
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (in != null)
                    in.close();
            } catch (IOException ex) {
                Logger.getLogger(RecordSerialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (records != null)
            RecordSerialization.record = records;
        else{
            System.out.println("File is empty.");
        }
    }
    
    /**
     * Clears serialized file. It is empty afterwards.
     * @param filename - file to be cleaned
     */
    public static void clearSerializationFile(String filename){
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
        try {
            fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(null);
        } catch (IOException ex) {
            System.out.println("ERROR: Failed to clean.");
            return;
        }
        finally{
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (objectOutputStream != null)
                    objectOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(RecordSerialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RecordSerialization.record.clear();
        System.out.println("Cleaned.");
    }
    
    /**
     * Writes from serialized file to readable format to stdout
     * @param serializedFile - file to read from
     * @return - true if successful, false otherwise
     */
    public static void writeFormattedRecords(String serializedFile){
        deserializeFromFile(serializedFile);     
        for (Record rec : record){
            String line = "User: " + rec.getUser() + "\nTime: " + rec.getTime() + 
                          "\nCommand: " + rec.getCommand() +  "\nResponse: " + rec.getResponse() +
                          "\n---------------------\n";
            System.out.println(line);
        }
   }
}
