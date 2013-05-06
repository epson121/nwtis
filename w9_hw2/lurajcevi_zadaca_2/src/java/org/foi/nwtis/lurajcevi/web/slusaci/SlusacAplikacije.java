
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
 * Web application lifecycle listener.
 *
 * @author Luka Rajcevic
 */
@WebListener()
public class SlusacAplikacije implements ServletContextListener {
    
    private ObradaPoruka op;
    private Konfiguracija config = null;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String path = sce.getServletContext().getRealPath("WEB-INF");
        
        
        String datoteka = sce.getServletContext().getInitParameter("konfiguracija");
        BP_Konfiguracija bpKonf = new BP_Konfiguracija(path + File.separator + datoteka);
        
        String configFile = sce.getServletContext().getInitParameter("lurajcevi_zadaca2_config");
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(path + File.separator + configFile);
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(SlusacAplikacije.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sce.getServletContext().setAttribute("BP_Konfiguracija", bpKonf);
        sce.getServletContext().setAttribute("lurajcevi_zadaca2_config", config);
        
        System.out.println("Konfiguracija baze učitana.");
        System.out.println("Konfiguracija učitana.");
       
        op = new ObradaPoruka(config,
                              bpKonf,
                              path
                             );
        op.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (op != null && !op.isInterrupted()){
            //op.interrupt();
        }
    }
}
