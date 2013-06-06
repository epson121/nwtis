/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviPortfolio;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviPortfolioFacade;

/**
 *
 * @author Luka Rajcevic
 */
@Named(value = "portfolio")
@SessionScoped
public class Portfolio implements Serializable {
    @EJB
    private LurajceviPortfolioFacade lurajceviPortfolioFacade;

    private List<LurajceviPortfolio> portfolioPopis;
    private boolean postoji = false;
    private LurajceviPortfolio odabraniPortfolio;
    private int portfolioID;
    
    
    private HttpSession session;
    private FacesContext context;
    /**
     * Creates a new instance of Portfolio
     */
    public Portfolio() {
        context = FacesContext.getCurrentInstance();
        session = (HttpSession)(context.getExternalContext().getSession(true)); 
    }

    public List<LurajceviPortfolio> getPortfolioPopis() {
        String korisnik = (String) session.getAttribute("korisnik");
        portfolioPopis = lurajceviPortfolioFacade.dohvatiPortfolie(korisnik);
        if (portfolioPopis.size() > 0)
            postoji = true;
        return portfolioPopis;
    }
    
    public String pregledPortfolia(){
        return "OK";
    }

    public void setPortfolioPopis(List<LurajceviPortfolio> portfolioPopis) {
        this.portfolioPopis = portfolioPopis;
    }

    public boolean isPostoji() {
        return postoji;
    }

    public void setPostoji(boolean postoji) {
        this.postoji = postoji;
    }

    public int getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(int portfolioID) {
        this.portfolioID = portfolioID;
    }

    public LurajceviPortfolio getOdabraniPortfolio() {
        return odabraniPortfolio;
    }

    public void setOdabraniPortfolio(LurajceviPortfolio odabraniPortfolio) {
        this.odabraniPortfolio = odabraniPortfolio;
    }
    
    
    
}
