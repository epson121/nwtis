/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.lurajcevi.web.kontrola.Korisnik;

/**
 *
 * @author nwtis_2
 */
@WebServlet(name = "Kontroler", urlPatterns = {"/Kontroler", 
                                               "/PrijavaKorisnika", 
                                               "/OdjavaKorisnika", 
                                               "/ProvjeraKorisnika", 
                                               "/IspisAktivnihKorisnika", 
                                               "/IspisKorisnika", 
                                               "/IspisPodataka"}
                                              )
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String zahtjev = request.getServletPath();
        String odrediste = null;
        switch(zahtjev){
            case "/Kontroler":
                odrediste = "/jsp/index.jsp";
                break;
            case "/PrijavaKorisnika":
                odrediste = "/jsp/login.jsp";
                break;
            case "/OdjavaKorisnika":
                odrediste = "/Kontroler";
                break;
            case "/ProvjeraKorisnika":
                String korIme = request.getParameter("kor_ime");
                String lozinka = request.getParameter("lozinka");
                if (korIme == null || korIme.trim().length() == 0 || lozinka == null 
                    || lozinka.trim().length() == 0){
                    throw new NeuspjesnaPrijava("Nije uneseno korisnicko ime ili lozinka.");
                } 
                else if(korIme.compareTo("lurajcevi") != 0 || lozinka.compareTo("123456") != 0){
                    throw new NeuspjesnaPrijava("Korisnicko ime ili lozinka pogresni.");
                }
                HttpSession sesija = request.getSession();
                Korisnik korisnik = new Korisnik(korIme, "Rajcevic", "Luka", request.getRemoteAddr(), 
                                                 sesija.getId(), 1);
                sesija.setAttribute("korisnik", korisnik);
                odrediste = "/IspisKorisnika";
                break;
            case "/IspisAktivnihKorisnika":
                odrediste = "/admin/ispisAktvnihKorisnika.jsp";
                break;
            case "/IspisKorisnika":
                odrediste = "/admin/ispisKorisnika.jsp";
                break;
            case "/IspisPodataka":
                odrediste = "/privatno/ispisPodataka.jsp";
                break;
            default:
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
