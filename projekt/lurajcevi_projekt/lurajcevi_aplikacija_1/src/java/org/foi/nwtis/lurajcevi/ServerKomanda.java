
package org.foi.nwtis.lurajcevi;

import java.io.Serializable;

/**
 * @document ServerKomanda
 * @author Luka Rajcevic
 */
public class ServerKomanda implements Serializable{
    
    private int id;
    private String komanda;
    private String status;
    private String korisnik;
    private String datum;

    public ServerKomanda(int id, String komanda, String status, String korisnik, String datum) {
        this.id = id;
        this.komanda = komanda;
        this.status = status;
        this.korisnik = korisnik;
        this.datum = datum;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKomanda() {
        return komanda;
    }

    public void setKomanda(String komanda) {
        this.komanda = komanda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }
    
    
    
}
