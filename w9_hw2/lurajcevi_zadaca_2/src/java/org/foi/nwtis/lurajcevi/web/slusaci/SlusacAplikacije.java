
package org.foi.nwtis.lurajcevi.web.slusaci;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.lurajcevi.web.ObradaPoruka;

/**
 * 
 * Slusac aplikacije koji pokrece dretvu za provjeru email servera i sortiranje poruka
 * @author Luka Rajcevic
 */
@WebListener()
public class SlusacAplikacije implements ServletContextListener {
    /*******************************
     * VARIJABLE
     * ****************************
     */
    
    private ObradaPoruka op;
    public static Konfiguracija config = null;
    public static BP_Konfiguracija bpKonf = null;
    
    
    /**
     * Metoda koja se pokrece prilikom inicijalizacije konteksta web aplikacije
     * @param sce 
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String p = sce.getServletContext().getRealPath("/");
        String path = sce.getServletContext().getRealPath("WEB-INF");
        String datoteka = sce.getServletContext().getInitParameter("konfiguracija");
        bpKonf = new BP_Konfiguracija(path + File.separator + datoteka);
        
        String configFile = sce.getServletContext().getInitParameter("lurajcevi_zadaca2_config_txt");
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(path + File.separator + configFile);
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(SlusacAplikacije.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sce.getServletContext().setAttribute("BP_Konfiguracija", bpKonf);
        sce.getServletContext().setAttribute("lurajcevi_zadaca2_config", config);
        
        System.out.println("Konfiguracija baze učitana.");
        System.out.println("Konfiguracija učitana.");
       
        op = new ObradaPoruka(config, bpKonf, p);
        op.start();
    }
    
    /**
     * Metoda koja se pokrece prilikom unistavanja konteksta web aplikacije
     * @param sce 
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (op != null && !op.isInterrupted()){
            op.interrupt();
        }
    }
}
