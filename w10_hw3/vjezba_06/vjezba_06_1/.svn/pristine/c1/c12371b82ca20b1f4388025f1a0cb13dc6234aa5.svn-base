/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.konfiguracije.bp;

import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;

/**
 *
 * @author nwtis_2
 */
public class BP_Konfiguracija implements BP_sucelje{

    private Konfiguracija config;
    
    private String admin_database;
    private String admin_password;
    private String admin_username;
    private String driver_database;
    private String server_database;
    private String user_database;
    private String user_password;
    private String user_username;
    
    public BP_Konfiguracija(String datoteka) {
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(datoteka);
            admin_database  = config.dajPostavku("admin.database");
            admin_password  = config.dajPostavku("admin.password");
            admin_username  = config.dajPostavku("admin.username");
            server_database = config.dajPostavku("server.database");
            user_database   = config.dajPostavku("user.database");
            user_username   = config.dajPostavku("user.username");
            user_password   = config.dajPostavku("user.password");
            
            driver_database = getDriver_database(server_database);
            
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(BP_Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String getAdmin_database() {
        return admin_database;
    }

    @Override
    public String getAdmin_password() {
        return admin_password;
    }

    @Override
    public String getAdmin_username() {
        return admin_username;
    }

    @Override
    public String getDriver_database() {
        return driver_database;
    }

    @Override
    public String getDriver_database(String bp_url) {
        String[] podaci = bp_url.split(":");
        //TODO provjera koliko ima elemenata
        String tipDrivera = podaci[1];
        String odabraniDriver = null;
        Properties p = getDrivers_database();
        for (Enumeration e = p.keys(); e.hasMoreElements(); ){
            String kljuc = (String) e.nextElement();
            String[] driveri = kljuc.split(("\\."));
            if (driveri[2].compareTo(tipDrivera) == 0){
                odabraniDriver = p.getProperty(kljuc);
            }
        }
        
        return odabraniDriver;
    }

    @Override
    public Properties getDrivers_database() {
        Properties drivers = new Properties();
        for (Enumeration<String> e = config.dajPostavke(); e.hasMoreElements();){
            String kljuc = e.nextElement();
            if (kljuc.startsWith("driver.database.")){
                drivers.setProperty(kljuc, config.dajPostavku(kljuc));
            }
        }
        return drivers;
    }

    @Override
    public String getServer_database() {
        return server_database;
    }

    @Override
    public String getUser_database() {
        return user_database;
    }

    @Override
    public String getUser_password() {
        return user_password;
    }

    @Override
    public String getUser_username() {
        return user_username;
    }
 
    
}
