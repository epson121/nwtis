
package org.foi.nwtis.lurajcevi.web.kontrole;

import java.util.Date;
import java.util.List;
import javax.mail.Flags;

/**
 * Klasa koja predstavlja entitet poruke i sadrzi sve njene dijelove
 * @author Luka Rajcevic
 */
public class Poruka {
    /*******************************
     * VARIJABLE
     * ****************************
     */
    private String id;
    private Date vrijeme;
    private String salje;
    private String predmet;
    private String vrsta;
    private String tekstPoruke;
    private int velicina;    
    private int brojPrivitaka;
    private Flags zastavice;
    private List<PrivitakPoruke> privitciPoruke;
    private boolean brisati;
    private boolean procitano;

    
    /*******************************
     * KONSTRUKTOR
     * ****************************
     */
    
    
    /**
     * Konstrutor klase Poruka
     * @param id - id poruke
     * @param poslano - datum slanja
     * @param salje - naziv posiljatelja
     * @param predmet - predmet poruke
     * @param vrsta - vrsta poruke
     * @param velicina - velicina poruke ( u bajtovima)
     * @param brojPrivitaka - broj privitaka u poruci
     * @param zastavice - zastavice u poruci
     * @param privitciPoruke - popis privitaka 
     * @param brisati - boolean brisati
     * @param procitano - boolean procitano
     * @param tekstPoruke - tekst poruke
     */
    public Poruka(String id, Date poslano, String salje, String predmet, String vrsta, int velicina, int brojPrivitaka, Flags zastavice, List<PrivitakPoruke> privitciPoruke, boolean brisati, boolean procitano, String tekstPoruke) {
        this.id = id;
        this.vrijeme = poslano;
        this.salje = salje;
        this.predmet = predmet;
        this.vrsta = vrsta;
        this.velicina = velicina;
        this.brojPrivitaka = brojPrivitaka;
        this.zastavice = zastavice;
        this.privitciPoruke = privitciPoruke;
        this.brisati = brisati;
        this.procitano = procitano;
        this.tekstPoruke = tekstPoruke;
    }

    /********************************
     * GETTERI I SETTERI
     * ******************************
     */
    public String getId() {
        return id;
    }

    public boolean isBrisati() {
        return brisati;
    }

    public void setBrisati(boolean brisati) {
        this.brisati = brisati;
    }

    public int getBrojPrivitaka() {
        return brojPrivitaka;
    }

    public Flags getZastavice() {
        return zastavice;
    }

    public Date getVrijeme() {
        return vrijeme;
    }

    public String getPredmet() {
        return predmet;
    }

    public List<PrivitakPoruke> getPrivitciPoruke() {
        return privitciPoruke;
    }

    public boolean isProcitano() {
        return procitano;
    }

    public void setProcitano(boolean procitano) {
        this.procitano = procitano;
    }
    
    public String getSalje() {
        return salje;
    }

    public String getVrsta() {
        return vrsta;
    }

    public int getVelicina() {
        return velicina;
    }

    public void setVrijeme(Date vrijeme) {
        this.vrijeme = vrijeme;
    }

    public String getTekstPoruke() {
        return tekstPoruke;
    }

    public void setTekstPoruke(String tekstPoruke) {
        this.tekstPoruke = tekstPoruke;
    }

    
}
