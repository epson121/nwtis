/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web.zrna;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Luka Rajcevic
 */
@ManagedBean(name = "pregledPoruke")
@RequestScoped
public class PregledPoruke {
    
    //TODO kreirati varijablu za poruku. Koristiti klasu PrivitakPoruke
    
    public PregledPoruke() {
    }
    
    public String povratakPregledSvihPoruka(){
        return "OK";
    }
}
