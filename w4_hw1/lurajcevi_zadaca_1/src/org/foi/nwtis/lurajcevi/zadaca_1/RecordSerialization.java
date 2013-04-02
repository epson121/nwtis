/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Luka Rajcevic
 */
public class RecordSerialization implements Serializable {
    
    public static List<Record> record = new ArrayList<>();

    public static void serializeToFile(String filename){
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
        try {
            fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(record);
        } catch (IOException ex) {
            System.out.println("Failed to serialize.");
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
    
    public static void deserializeFromFile(String filename){
        List<Record> records = new ArrayList<>();
        FileInputStream fileInputStream = null;
        ObjectInputStream in = null;
        try {
            fileInputStream = new FileInputStream(filename);
            in = new ObjectInputStream(fileInputStream);
            records = (ArrayList<Record>) in.readObject();
            
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            System.out.println("Error while loading " + filename + ". Most likely it's empty.");
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
    
    public static void clearSerializationFile(String filename){
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
        try {
            fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(null);
        } catch (IOException ex) {
            System.out.println("ERROR: Failed to clean.");
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
        
        System.out.println("Cleaned.");
    }

}
