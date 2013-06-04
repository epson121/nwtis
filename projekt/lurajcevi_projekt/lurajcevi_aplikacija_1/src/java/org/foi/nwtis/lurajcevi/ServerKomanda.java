
package org.foi.nwtis.lurajcevi;

import java.io.Serializable;

/**
 * @document ServerKomanda
 * @author Luka Rajcevic
 */
public class ServerKomanda implements Serializable{
    
    private int id;
    private String komanda;

    public ServerKomanda(int id, String komanda) {
        this.id = id;
        this.komanda = komanda;
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
    
    
    
}
