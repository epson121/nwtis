/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import org.foi.nwtis.lurajcevi.ejb.eb.Cities;
import org.foi.nwtis.lurajcevi.ejb.eb.States;
import org.foi.nwtis.lurajcevi.ejb.sb.CitiesFacade;
import org.foi.nwtis.lurajcevi.ejb.sb.StatesFacade;

/**
 *
 * @author nwtis_2
 */
@Named(value = "odabirZipKodovaZaGradove")
@SessionScoped
public class OdabirZipKodovaZaGradove implements Serializable {
    @EJB
    private CitiesFacade citiesFacade;
    @EJB
    private StatesFacade statesFacade;

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
    
    
    public OdabirZipKodovaZaGradove() {
    }
    
    public String dodajDrzave(){
        if (popisDrzavaOdabrano == null){
            return "";  
        }
        if (odabraneDrzave == null){
            odabraneDrzave = new HashMap<String, Object>();
        }
        for (String d : popisDrzavaOdabrano){
            odabraneDrzave.put(d, d);
        }
        return ""
;    }
    
    public String obrisiDrzave(){
        if (odabraneDrzaveOdabrano != null){
            for (String d : odabraneDrzaveOdabrano){
                odabraneDrzave.remove(d);
            }
        }
        return "";
    }
    
    public String preuzmiGradove(){
        if (odabraneDrzave == null || odabraneDrzave.isEmpty()){
            return "";
        }
        if (popisGradova == null){
            popisGradova = new HashMap<String, Object>();
        }
        List <Cities> gradovi = citiesFacade.filtrirajGradove(odabraneDrzave.keySet());
        for (Cities c : gradovi){
            popisGradova.put(c.getName(), c.getName());
        }
        return "";
    }

    public String dodajGradove(){
        if (popisGradovaOdabrano == null || popisGradovaOdabrano.isEmpty()){
            return "";
        }
        if (odabraniGradovi == null){
            odabraniGradovi = new HashMap<String, Object>();
        }
        for (String g : popisGradovaOdabrano){
            odabraniGradovi.put(g, g);
        }
        return "";
    }
    
    public String obrisiGradove(){
        return "";
    }
    
    public String preuzmiZipKodove(){
        return "";
    }
    
    public Map<String, Object> getPopisDrzava() {
        popisDrzava = new HashMap<String, Object>();
        List<States> drzave;
        if (filterDrzava == null || filterDrzava.trim().isEmpty()){
            drzave = statesFacade.findAll();
        } else {
            drzave = statesFacade.filtrirajDrzave(filterDrzava);
        }
        for (States d : drzave){
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
        popisGradova = new HashMap<String, Object>();
        if (odabraneDrzave == null || odabraneDrzave.isEmpty()){
            return popisGradova;
        }

        List <Cities> gradovi;
        if (filterGradova == null || filterGradova.isEmpty()){
            gradovi = citiesFacade.filtrirajGradove(odabraneDrzave.keySet());
        } else{
            gradovi = citiesFacade.filtrirajGradove(odabraneDrzave.keySet(), filterGradova);
        }
        for (Cities c : gradovi){
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
    
}
