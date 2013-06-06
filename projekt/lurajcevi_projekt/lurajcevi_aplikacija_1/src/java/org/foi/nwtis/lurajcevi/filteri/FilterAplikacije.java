/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.filteri;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.lurajcevi.db.DBConnector;

/**
 * Web application filter.
 * Filtrira zahtjeve u ovisnosti o tome je li korisnik prijavljen ili ne.
 * @author Luka Rajcevic
 */
public class FilterAplikacije implements Filter {
    
    private static final boolean debug = true;
    private long start;
    private long duration;

    private FilterConfig filterConfig = null;
    
    public FilterAplikacije() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterAplikacije:DoBeforeProcessing");
        }
        start = System.currentTimeMillis();
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterAplikacije:DoAfterProcessing");
        }
        String zahtjev = ((HttpServletRequest) request).getServletPath();
        String korisnik = "anonymous";
        if (request.getParameter("korisnik") != null)
            korisnik = (String) request.getAttribute("korisnik");
        duration = System.currentTimeMillis() - start;
        try {
            DBConnector.unesiPodatke("lurajcevi_dnevnik_zahtjeva", zahtjev, duration + "", korisnik);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FilterAplikacije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("FilterAplikacije:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        Throwable problem = null;
        try {
            if (request instanceof HttpServletRequest){
                HttpSession sesija = ((HttpServletRequest)request).getSession();
                String zahtjev = ((HttpServletRequest) request).getServletPath();
                Object korisnik = sesija.getAttribute("korisnik");
                sesija.setAttribute("zahtjev", zahtjev);
                if (korisnik == null){
                    if (zahtjev.equals("/index.jsp")){
                        RequestDispatcher rd = request.getRequestDispatcher("/Kontroler");
                        rd.forward(request, response);
                        return;
                    }
                    sesija.setAttribute("request", zahtjev);
                    RequestDispatcher rd = request.getRequestDispatcher("/PrijavaKorisnika");
                    rd.forward(request, response);
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("FilterAplikacije:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterAplikacije()");
        }
        StringBuffer sb = new StringBuffer("FilterAplikacije(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
}
