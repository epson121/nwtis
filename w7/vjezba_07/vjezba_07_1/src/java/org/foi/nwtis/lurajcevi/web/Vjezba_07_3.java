/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;

/**
 *
 * @author nwtis_2
 */
public class Vjezba_07_3 extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Vjezba_07_3</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Vjezba_07_3 at " + request.getContextPath() + "</h1>");
            String path = getServletContext().getRealPath("WEB-INF");
            String datoteka = getInitParameter("konfiguracija");
            BP_Konfiguracija bpKonf = new BP_Konfiguracija(path + File.separator + datoteka);
            String upit = "SELECT ime, prezime FROM polaznici";
            String url = bpKonf.getServer_database() + bpKonf.getUser_database();
            String korisnik = bpKonf.getUser_username();
            String lozinka = bpKonf.getUser_password();
            String driver = bpKonf.getDriver_database();
            try { 
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Vjezba_07_3.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (
                    Connection veza = DriverManager.getConnection(url, korisnik, lozinka);
                    Statement instr = veza.createStatement();
                    ResultSet rs = instr.executeQuery(upit);
                ) 
                {
                    out.println("<table>");
                    out.println("<tr><th>Ime</th><th>Prezime</th>");
                while (rs.next()) {
                    String ime = rs.getString("ime");
                    String prezime = rs.getString("prezime");
                    out.println("<tr>");
                    out.println("<td>" + ime + "</td>");
                    out.println("<td>" + prezime + "</td>");
                    out.println("</tr>");
                     //System.out.println("Ime: " + ime + "\nPrezime: " + prezime + "\n");
                }
                out.println("<table>");
            } catch(SQLException e){
                e.printStackTrace();
            }
                out.println("</body>");
                out.println("</html>");
            } finally {            
                out.close();
            }
        }

        
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
