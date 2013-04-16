
package org.foi.nwtis.lurajcevi.web.slusaci;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Luka Rajcevic
 */
@WebListener()
public class Vjezba_07_4 implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        System.out.println("Aplikacija: " + sc.getContextPath());
        System.out.println("Inicijalni parametar: " + sc.getInitParameter("konfiguracija"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        System.out.println("Završava aplikacija " + sc.getContextPath());
    }
}
