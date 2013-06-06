/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJB;
import org.foi.nwtis.lurajcevi.ejb.eb.Cities;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviPortfolio;
import org.foi.nwtis.lurajcevi.ejb.eb.States;
import org.foi.nwtis.lurajcevi.ejb.eb.ZipCodes;
import org.foi.nwtis.lurajcevi.ejb.sb.CitiesFacade;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviPortfolioFacade;
import org.foi.nwtis.lurajcevi.ejb.sb.StatesFacade;
import org.foi.nwtis.lurajcevi.ejb.sb.ZipCodesFacade;

/**
 *
 * @author Luka Rajcevic
 */
@Named(value = "portfolioKreiranje")
@SessionScoped
public class PortfolioKreiranje implements Serializable {
    @EJB
    private LurajceviPortfolioFacade lurajceviPortfolioFacade;
    @EJB
    private ZipCodesFacade zipCodesFacade;
    @EJB
    private StatesFacade statesFacade;
    @EJB
    private CitiesFacade citiesFacade;
    
    private Map<String, Object> popisDrzava;
    private List<String> popisDrzavaOdabrano;
    private Map<String, Object> odabraneDrzave;
    private List<String> odabraneDrzaveOdabrano;
    private String filterDrzava;
    
    private Map<String, Object> popisGradova;
    private List<String> popisGradovaOdabrano;
    private Map<String, Object> odabraniGradovi;
    private List<String> odabraniGradoviOdabrano;
    private String filterGradova;
    private List<Cities> gradovi;
    
    private Map<String, Object> popisZipKodova;
    private List<String> popisZipKodovaOdabrano;
    private Map<String, Object> odabraniZipKodovi;
    private List<String> odabraniZipKodoviOdabrano;
    private String filterZipKodova;

    /**
     * *****************************************
     * KONSTRUKTOR 
     ******************************************
     */
     public PortfolioKreiranje() {
    }
  
    /**
     * *****************************************
     * POMOĆNE METODE 
     ******************************************
     */
    /**
     * Dohvaća odabrane drzave iz popisa drzava i stavlja ih u drugu listu 
     * (listu odabranih drzava)
     * @return vraća se na istu stranicu
     */
    public String dodajDrzave() {
        if (popisDrzavaOdabrano == null) {
            return "";
        }
        if (odabraneDrzave == null) {
            odabraneDrzave = new TreeMap<String, Object>();
        }
        for (String d : popisDrzavaOdabrano) {
            odabraneDrzave.put(d, d);
        }
        return "";
    }
    
    /**
     * brise drzavu iz popisa odabranih drzava
     * @return vraća se na istu stranicu
     */
    public String obrisiDrzave() {
        if (odabraneDrzaveOdabrano != null) {
            for (String d : odabraneDrzaveOdabrano) {
                odabraneDrzave.remove(d);
            }
        }
        return "";
    }
    
    /**
     * dohvaća popis gradova (nema implementacije jer je sohvaćanje napravljeno
     * u getteru za popisGradova)
     * @return vraća se na istu stranicu
     */
    public String preuzmiGradove() {
        return "";
    }
    
    /**
     * Dodaje odabrane gradove u novu listu (odabraniGradovi)
     * @return vraća se na istu stranicu
     */
    public String dodajGradove() {
        if (popisGradovaOdabrano == null || popisGradovaOdabrano.isEmpty()) {
            return "";
        }
        if (odabraniGradovi == null) {
            odabraniGradovi = new TreeMap<String, Object>();
        }
        for (String g : popisGradovaOdabrano) {
            odabraniGradovi.put(g, g);
        }
        return "";
    }
    
    /**
     * brise selektirane gradove iz popisa odabranih gradova
     * @return vraća se na istu stranicu
     */
    public String obrisiGradove() {
        if (odabraniGradoviOdabrano != null) {
            for (String g : odabraniGradoviOdabrano) {
                odabraniGradovi.remove(g);
            }
        }
        return "";
    }
    
    /**
     * dohvaća popis zip kodova u ovisnosti o odabranim gradovima i drzavama
     * implementacija je napravljena u getteru
     * @return vraća se na istu stranicu
     */
    public String preuzmiZipKodove() {
        return "";
    }
    
    /**
     * Dodaje odabrane zip kodove u novu listu odabranih zip kodova
     * @return vraća se na istu stranicu
     */
    public String dodajZipKodove() {
        if (popisZipKodovaOdabrano == null || popisZipKodovaOdabrano.isEmpty()) {
            return "";
        }
        if (odabraniZipKodovi == null) {
            odabraniZipKodovi = new TreeMap<String, Object>();
        }
        for (String z : popisZipKodovaOdabrano) {
            odabraniZipKodovi.put(z, z);
        }
        return "";
    }
    
    /**
     * brise odabrane zip kodove iz popisa odabranih zip kodova
     * @return vraća se na istu stranicu
     */
    public String obrisiZipKodove() {
        if (odabraniZipKodoviOdabrano != null) {
            for (String g : odabraniZipKodoviOdabrano) {
                odabraniZipKodovi.remove(g);
            }
        }
        return "";
    }
    
    public String spremiPortfolio(){
        //TODO save portfolio, zips, etc..
        //for petlja i dodat u zp svaki zip
        //random uuid generator!
        return "";
    }

    /**
     * *****************************************
     * GETTERI I SETTERI 
     ******************************************
     */
    public Map<String, Object> getPopisDrzava() {
        popisDrzava = new TreeMap<String, Object>();
        List<States> drzave;
        if (filterDrzava == null || filterDrzava.trim().isEmpty()) {
            drzave = statesFacade.findAll();
        } else {
            drzave = statesFacade.filtrirajDrzave(filterDrzava.toUpperCase());
        }
        for (States d : drzave) {
            popisDrzava.put(d.getName(), d.getName());
        }
        return popisDrzava;
    }

    public void setPopisDrzava(Map<String, Object> popisDrzava) {
        this.popisDrzava = popisDrzava;
    }

    public List<String> getPopisDrzavaOdabrano() {
        return popisDrzavaOdabrano;
    }

    public void setPopisDrzavaOdabrano(List<String> popisDrzavaOdabrano) {
        this.popisDrzavaOdabrano = popisDrzavaOdabrano;
    }

    public Map<String, Object> getOdabraneDrzave() {
        return odabraneDrzave;
    }

    public void setOdabraneDrzave(Map<String, Object> odabraneDrzave) {
        this.odabraneDrzave = odabraneDrzave;
    }

    public List<String> getOdabraneDrzaveOdabrano() {
        return odabraneDrzaveOdabrano;
    }

    public void setOdabraneDrzaveOdabrano(List<String> odabraneDrzaveOdabrano) {
        this.odabraneDrzaveOdabrano = odabraneDrzaveOdabrano;
    }

    public String getFilterDrzava() {
        return filterDrzava;
    }

    public void setFilterDrzava(String filterDrzava) {
        this.filterDrzava = filterDrzava;
    }

    public Map<String, Object> getPopisGradova() {
        popisGradova = new TreeMap<String, Object>();
        if (odabraneDrzave == null || odabraneDrzave.isEmpty()) {
            return popisGradova;
        }

        if (filterGradova == null || filterGradova.isEmpty()) {
            gradovi = citiesFacade.filtrirajGradove(odabraneDrzave.keySet());
        } else {
            gradovi = citiesFacade.filtrirajGradove(odabraneDrzave.keySet(), filterGradova.toUpperCase());
        }

        for (Cities c : gradovi) {
            String grad = c.getCitiesPK().getState() + " - " + c.getCitiesPK().getCounty() + " -  " + c.getCitiesPK().getCity();
            popisGradova.put(grad, grad);
        }
        return popisGradova;
    }

    public void setPopisGradova(Map<String, Object> popisGradova) {
        this.popisGradova = popisGradova;
    }

    public List<String> getPopisGradovaOdabrano() {
        return popisGradovaOdabrano;
    }

    public void setPopisGradovaOdabrano(List<String> popisGradovaOdabrano) {
        this.popisGradovaOdabrano = popisGradovaOdabrano;
    }

    public Map<String, Object> getOdabraniGradovi() {
        return odabraniGradovi;
    }

    public void setOdabraniGradovi(Map<String, Object> odabraniGradovi) {
        this.odabraniGradovi = odabraniGradovi;
    }

    public List<String> getOdabraniGradoviOdabrano() {
        return odabraniGradoviOdabrano;
    }

    public void setOdabraniGradoviOdabrano(List<String> odabraniGradoviOdabrano) {
        this.odabraniGradoviOdabrano = odabraniGradoviOdabrano;
    }

    public String getFilterGradova() {
        return filterGradova;
    }

    public void setFilterGradova(String filterGradova) {
        this.filterGradova = filterGradova;
    }

    public Map<String, Object> getPopisZipKodova() {
        popisZipKodova = new TreeMap<String, Object>();
        List<ZipCodes> zipovi;
        if (odabraniGradovi == null || odabraniGradovi.isEmpty()) {
            return popisZipKodova;
        }
        if (filterZipKodova == null || filterZipKodova.isEmpty()) {
            zipovi = zipCodesFacade.filtrirajZipove(odabraniGradovi.keySet());
        } else {
            zipovi = zipCodesFacade.filtrirajZipove(odabraniGradovi.keySet(), filterZipKodova);
        }
        for (ZipCodes zc : zipovi) {
            String res = zc.getCities().getCitiesPK().getState() + " - " + zc.getCities().getCitiesPK().getCounty() + " -  " + 
                         zc.getCities().getCitiesPK().getCity() + " - " + zc.getZip();
            popisZipKodova.put(res.toString(), res.toString());
        }
        return popisZipKodova;
    }

    public void setPopisZipKodova(Map<String, Object> popisZipKodova) {
        this.popisZipKodova = popisZipKodova;
    }

    public List<String> getPopisZipKodovaOdabrano() {
        return popisZipKodovaOdabrano;
    }

    public void setPopisZipKodovaOdabrano(List<String> popisZipKodovaOdabrano) {
        this.popisZipKodovaOdabrano = popisZipKodovaOdabrano;
    }

    public Map<String, Object> getOdabraniZipKodovi() {
        return odabraniZipKodovi;
    }

    public void setOdabraniZipKodovi(Map<String, Object> odabraniZipKodovi) {
        this.odabraniZipKodovi = odabraniZipKodovi;
    }

    public List<String> getOdabraniZipKodoviOdabrano() {
        return odabraniZipKodoviOdabrano;
    }

    public void setOdabraniZipKodoviOdabrano(List<String> odabraniZipKodoviOdabrano) {
        this.odabraniZipKodoviOdabrano = odabraniZipKodoviOdabrano;
    }

    public String getFilterZipKodova() {
        return filterZipKodova;
    }

    public void setFilterZipKodova(String filterZipKodova) {
        this.filterZipKodova = filterZipKodova;
    }

}
