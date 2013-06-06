
package org.foi.nwtis.lurajcevi.slusaci;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.foi.nwtis.lurajcevi.ObradaPodatakaServer;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.lurajcevi.ws.ObradaPodatakaWeatherbug;

/**
 * Web application lifecycle listener.
 *
 * @author Luka Rajcevic
 */
public class SlusacAplikacije implements ServletContextListener {
    
    public static Konfiguracija config = null;
    public static BP_Konfiguracija bpKonf = null;
    public static boolean stopped = false;
    public static boolean paused = false;
    private ObradaPodatakaWeatherbug opw = null;
    private ObradaPodatakaServer ops = null;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        String path = sce.getServletContext().getRealPath("WEB-INF");
        String configFile = sce.getServletContext().getInitParameter("konfiguracija");
        String bpConfigFile = sce.getServletContext().getInitParameter("BPkonfiguracija");
        bpKonf = new BP_Konfiguracija(path + File.separator + bpConfigFile);
        
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(path + File.separator + configFile);
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(SlusacAplikacije.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sce.getServletContext().setAttribute("BP_Konfiguracija", bpKonf);
        sce.getServletContext().setAttribute("Konfiguracija", config);
        
        System.out.println("Konfiguracija baze učitana.");
        System.out.println("Konfiguracija učitana.");
        
        opw = new ObradaPodatakaWeatherbug(config);
        //opw.start();
        
        ops = new ObradaPodatakaServer(config);
        //ops.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (opw.isAlive())
            opw.interrupt();
        if (ops.isAlive())
            ops.interrupt();
    }

    public static boolean isStopped() {
        return stopped;
    }

    public static void setStopped(boolean stopped) {
        SlusacAplikacije.stopped = stopped;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean paused) {
        SlusacAplikacije.paused = paused;
    }
    
}
