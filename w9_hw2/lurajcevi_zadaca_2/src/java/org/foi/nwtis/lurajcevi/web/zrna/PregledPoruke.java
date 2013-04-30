
package org.foi.nwtis.lurajcevi.web.zrna;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.foi.nwtis.lurajcevi.web.kontrole.Poruka;

/**
 *
 * @author Luka Rajcevic
 */
@ManagedBean(name = "pregledPoruke")
@RequestScoped
public class PregledPoruke {
    
    private Poruka poruka;

    public PregledPoruke() {
    }

    public Poruka getPoruka() {
        PregledSvihPoruka psp = (PregledSvihPoruka) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pregledSvihPoruka");
        poruka = psp.getOdabranaPoruka();
        return poruka;
    }

    public void setPoruka(Poruka poruka) {
        this.poruka = poruka;
    }
    
    public String povratakPregledSvihPoruka(){
        return "OK";
    }
}
