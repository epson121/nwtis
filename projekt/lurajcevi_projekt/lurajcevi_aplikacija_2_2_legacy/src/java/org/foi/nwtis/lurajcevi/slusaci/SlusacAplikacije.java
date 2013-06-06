
package org.foi.nwtis.lurajcevi.slusaci;

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
import org.foi.nwtis.lurajcevi.mail.ObradaPoruke;

/**
 * Web application lifecycle listener.
 *
 * @author Luka Rajcevic
 */
@WebListener()
public class SlusacAplikacije implements ServletContextListener {

    private Konfiguracija config;
    private BP_Konfiguracija bpKonf;
    private ObradaPoruke op;
    
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
        
        op = new ObradaPoruke(config);
        //op.start();
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
   
    }
}
