
package org.foi.nwtis.lurajcevi.web.zrna;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.foi.nwtis.lurajcevi.web.kontrole.Poruka;

/**
 * Klasa koja prikazuje detalje o poruci
 * @author Luka Rajcevic
 */
@ManagedBean(name = "pregledPoruke")
@RequestScoped
public class PregledPoruke implements Serializable {
    
    /*******************************
     * VARIJABLE
     * ****************************
     */
    
    private Poruka poruka;
    private boolean multipart;
    
    /*******************************
     * KONSTRUKTOR
     * ****************************
     */
    
    public PregledPoruke() {
    }
    
     /********************************
     * POMOĆNE METODE
     * ******************************
     */
    
    /**
     * dohvaća poruku koja se prikazuje u web aplikaciji
     * @return 
     */
    public Poruka getPoruka() {
        PregledSvihPoruka psp = (PregledSvihPoruka) FacesContext.getCurrentInstance()
                                                    .getExternalContext()
                                                    .getSessionMap()
                                                    .get("pregledSvihPoruka");
        poruka = psp.getOdabranaPoruka();
        if (poruka.getBrojPrivitaka() > 0){
            setMultipart(true);
        } else{
            setMultipart(false);
        }
        return poruka;
    }
    /**
     * sluzi za navigaciju natrag do pregleda svih poruka
     * @return 
     */
    public String povratakPregledSvihPoruka(){
        return "OK";
    }

    
    /********************************
     * GETTERI I SETTERI
     * ******************************
     */
    
    public void setPoruka(Poruka poruka) {
        this.poruka = poruka;
    }
    
    public boolean isMultipart() {
        return multipart;
    }

    public void setMultipart(boolean multipart) {
        this.multipart = multipart;
    }
    
}
