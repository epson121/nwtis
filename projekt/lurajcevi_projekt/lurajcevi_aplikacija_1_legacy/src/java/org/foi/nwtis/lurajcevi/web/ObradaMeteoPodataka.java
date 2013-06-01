
package org.foi.nwtis.lurajcevi.web;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.lurajcevi.ws.WeatherBugKlijent;

/**
 * @document ObradaMeteoPodataka
 * @author Luka Rajcevic
 */
public class ObradaMeteoPodataka extends Thread{
    
    private int interval;
    private BP_Konfiguracija bpKonf;
    private WeatherBugKlijent wbKlijent;
    private List<String> zipKodovi;
    private List<LiveWeatherData> meteoPodaci;
    
    public ObradaMeteoPodataka(Konfiguracija conf){
        this.interval = Integer.parseInt(conf.dajPostavku("interval"));
        wbKlijent = new WeatherBugKlijent();
        meteoPodaci = new ArrayList<LiveWeatherData>();
    }
    
    @Override
    public synchronized void start() {
        super.start(); 
    }

    @Override
    public void run() {
        long start, duration = 0;
        while (true) {

            start = System.currentTimeMillis();
            //obrada
            duration = System.currentTimeMillis() - start;
            meteoPodaci.clear();
            zipKodovi = dohvatiPodatke();
            for (String z : zipKodovi){
                meteoPodaci.add(wbKlijent.dajMeteoPodatke(z));
            }
            try {
                long sleepTime;
                sleepTime = ((int) (interval * 1000) - duration);
                System.out.println("Spavanje: " + sleepTime);
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(0);
            } catch (InterruptedException ex) {
                Logger.getLogger(ObradaMeteoPodataka.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }
    
       /**
     * Provjerava korisnicko ime i lozinku dobivene u parametrima unutar baze
     * @param username korisnicko ime
     * @param password lozinka
     * @return true ili false
     */
    private List<String> dohvatiPodatke(){
        String upit = "SELECT zip FROM lurajcevi_activezipcodes";
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        List<String> zipKodovi = new ArrayList<String>();
        try{
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery(upit);
            while (rs.next()){
               zipKodovi.add(rs.getString("zip"));
            }
            
        } catch(SQLException e){
            if (veza != null){
                veza = null;
            }
            if (instr != null){
                instr = null;
            }
            if (rs != null){
                rs = null;
            }
            e.printStackTrace();
        }
        return zipKodovi;
    }
 

    
}
