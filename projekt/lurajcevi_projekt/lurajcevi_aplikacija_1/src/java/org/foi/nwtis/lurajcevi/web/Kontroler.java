/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.lurajcevi.db.DBConnector;
import org.foi.nwtis.lurajcevi.ws.MeteoPodaci;

/**
 *
 * @author Luka Rajcevic
 */

public class Kontroler extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String zahtjev = request.getServletPath();
        String odrediste = null;
        if (zahtjev.equals("/Kontroler"))
            odrediste = "/index.jsp";
        
        else if (zahtjev.equals("/PrijavaKorisnika")) 
            odrediste = "/login.jsp";
        
        else if (zahtjev.equals("/OdjavaKorisnika")){
            HttpSession sesija = request.getSession();
            sesija.removeAttribute("korisnik");
            odrediste = "/Kontroler";
        }
        
        else if (zahtjev.equals("/ProvjeraKorisnika")) {
            String korIme = request.getParameter("kor_ime");
            String lozinka = request.getParameter("lozinka");
            HttpSession sesija = null;
            if (korIme == null || korIme.trim().length() == 0 || lozinka == null 
                || lozinka.trim().length() == 0){
                throw new NeuspjesnaPrijava("Nije uneseno korisnicko ime ili lozinka.");
            } 
            else {
                boolean b = false;
                try {
                    b = DBConnector.provjeriKorisnika("lurajcevi_admin_podaci", korIme, lozinka);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (b){
                    sesija = request.getSession();
                    sesija.setAttribute("korisnik", korIme);
                }
                else {
                    throw new NeuspjesnaPrijava("Korisnicko ime ili lozinka pogresni.");
                }
            }
            if (sesija.getAttribute("request") != null){
                odrediste = (String) sesija.getAttribute("request");
                sesija.removeAttribute("request");
            }
            else{
                odrediste = "/IspisKorisnika";
            }
        } 
        
        else if (zahtjev.equals("/PregledMeteoPodataka")){
            HttpSession sesija = request.getSession();
            int brojStranica = 5;
            if (sesija.getAttribute("broj_stranica") == null)
                sesija.setAttribute("broj_stranica", brojStranica);
            try {
                if (sesija.getAttribute("meteo_podaci") == null){
                    List<MeteoPodaci> podaci = DBConnector.dohvatiMeteoPodatke();
                    sesija.setAttribute("meteo_podaci", podaci);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
            odrediste = "/jsp/pregledMeteoPodataka.jsp";
        } 
        
        else if (zahtjev.equals("/PregledDnevnika")){
            HttpSession sesija = request.getSession();
            List<HttpZahtjev> pregled_zahtjeva = null;
            try {
                pregled_zahtjeva = DBConnector.dohvatiPopisZahtjevaPremaServeru("lurajcevi_dnevnik_zahtjeva");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
            sesija.setAttribute("pregled_zahtjeva", pregled_zahtjeva);
            odrediste = "/jsp/pregledDnevnika.jsp";
        } 
        
        else if (zahtjev.equals("/PregledZahtjevaServera")){
            
            odrediste = "/jsp/pregledZahtjevaServera.jsp";
        } 
        
        else if (zahtjev.equals("/MeteoPodaciStranicenje")){
            int brojStranica = Integer.parseInt(request.getParameter("broj_stranica"));
            HttpSession sesija = request.getSession();
            sesija.setAttribute("broj_stranica", brojStranica);
            odrediste = "/jsp/pregledMeteoPodataka.jsp";
        } 
        
        else if (zahtjev.equals("/MeteoPodaciInterval")){
            HttpSession sesija = request.getSession();
            String zip = request.getParameter("zip");
            String datum1 = request.getParameter("datum1");
            String datum2 = request.getParameter("datum2");
            if (zip != null && !zip.trim().equals("") && 
                datum1 != null && !datum1.trim().equals("") &&
                datum2 != null && !datum2.trim().equals("")){
                System.out.println("ZIP: " + zip);
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy hh.mm.ss");
                try{
                    Date d1 = df.parse(datum1);
                    Date d2 = df.parse(datum2);
                    datum1 = df2.format(d1);
                    datum2 = df2.format(d2);
                    System.out.println("D1: " + datum1);
                    System.out.println("D2: " + datum2);
                    List<MeteoPodaci> podaci = DBConnector.dohvatiPodatkeInterval("lurajcevi_podaci_zip", "90000", datum1, datum2);
                    sesija.setAttribute("meteo_podaci", podaci);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException e){  
                }
            }
            odrediste = "/jsp/pregledMeteoPodataka.jsp";
        }
        
        else{
            ServletException up = new ServletException("Zahtjev nije poznat");
            throw up;
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(odrediste);
        rd.forward(request, response);
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
