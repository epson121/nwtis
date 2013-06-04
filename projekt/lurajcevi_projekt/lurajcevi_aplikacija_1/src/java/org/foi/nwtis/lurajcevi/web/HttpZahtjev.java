
package org.foi.nwtis.lurajcevi.web;

import java.io.Serializable;

/**
 * @document HttpZahtjev
 * @author Luka Rajcevic
 */
public class HttpZahtjev implements Serializable{

    private int id;
    private String zahtjev;
    private String trajanje;

    public HttpZahtjev(int id, String zahtjev, String trajanje) {
        this.id = id;
        this.zahtjev = zahtjev;
        this.trajanje = trajanje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZahtjev() {
        return zahtjev;
    }

    public void setZahtjev(String zahtjev) {
        this.zahtjev = zahtjev;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }
    
    
    
}
