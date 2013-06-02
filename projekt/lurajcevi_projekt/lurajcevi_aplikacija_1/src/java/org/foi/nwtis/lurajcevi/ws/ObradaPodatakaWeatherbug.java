
package org.foi.nwtis.lurajcevi.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.db.DBConnector;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.slusaci.SlusacAplikacije;

/**
 * @document ObradaPodatakaWeatherbug
 * @author Luka Rajcevic
 */
public class ObradaPodatakaWeatherbug extends Thread{
    
    private int interval;
    private WeatherBugKlijent wbKlijent;
    private List<String> zipKodovi;
    private Map<String, LiveWeatherData> meteoPodaci;
    
    public ObradaPodatakaWeatherbug(Konfiguracija config){
        this.interval = Integer.parseInt(config.dajPostavku("interval"));
        meteoPodaci = new HashMap<String, LiveWeatherData>();
        wbKlijent = new WeatherBugKlijent();
    }
    
    @Override
    public synchronized void start() {
        super.start(); 
    }

    @Override
    public void run() {
        long start, duration = 0;
        while (!SlusacAplikacije.isStopped()) {
            start = System.currentTimeMillis();
            System.out.println("STOP: " + SlusacAplikacije.isStopped());
            if (!SlusacAplikacije.isPaused()){
                meteoPodaci.clear();
                try {
                    zipKodovi = DBConnector.dohvatiPodatke();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ObradaPodatakaWeatherbug.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (String z : zipKodovi){
                    System.out.println("z" + z);
                    meteoPodaci.put(z, wbKlijent.dajMeteoPodatke(z));
                }

                try {
                    DBConnector.unesiPodatke(meteoPodaci);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ObradaPodatakaWeatherbug.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
            duration = System.currentTimeMillis() - start;
            try {
                long sleepTime;
                sleepTime = ((int) (interval * 1000) - duration);
                System.out.println("Spavanje: " + sleepTime);
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(0);
            } catch (InterruptedException ex) {
                Logger.getLogger(ObradaPodatakaWeatherbug.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("Weatherbug Thread: Stopping.");
    }

    @Override
    public void interrupt() {
        super.interrupt(); 
    }
    
}
