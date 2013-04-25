/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web.slusaci;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.lurajcevi.web.ObradaPoruka;

/**
 * Web application lifecycle listener.
 *
 * @author Luka
 */
@WebListener()
public class SlusacAplikacije implements ServletContextListener {
    
    private ObradaPoruka op;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String path = sce.getServletContext().getRealPath("WEB-INF");
        String datoteka = sce.getServletContext().getInitParameter("konfiguracija");
        BP_Konfiguracija bpKonf = new BP_Konfiguracija(path + File.separator + datoteka);
        
        sce.getServletContext().setAttribute("BP_Konfiguracija", bpKonf);
        System.out.println("Konfiguracija učitana.");
        op = new ObradaPoruka();
        op.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (op != null && !op.isInterrupted()){
            op.interrupt();
        }
    }
}
