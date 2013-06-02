
package org.foi.nwtis.lurajcevi.ws.klijent;

import java.util.List;
import net.wxbug.api.MeteoPodaci;

/**
 * @document WSKlijent
 * @author Luka Rajcevic
 */
public class WSKlijent {

    public List<String> dohvatiPodatke(){
        return dohvatiAktivneZipove();
    }
    
    public MeteoPodaci dohvatiMeteoZaZip(String zip){
        return dohvatiPodatkeOZipKodu(zip);
    }
    
    public List<MeteoPodaci> dohvatiPosljednjihN(int n, String zip){
        return dohvatiPosljednjihNPodatakaZaZipKod(n, zip);
    }
    
    public List<MeteoPodaci> dohvatiPodatkeIntervalno(String zip, String d1, String d2){
        return dohvatiPodatkeInterval(zip, d1, d2);
    }
    
    public List<String> dohvatiZipoveSTopPodataka(int n){
        return dohvatiZipoveSNajvisePodataka(n);
    }
    
    private static java.util.List<java.lang.String> dohvatiAktivneZipove() {
        org.foi.nwtis.lurajcevi.ws.WbServis_Service service = new org.foi.nwtis.lurajcevi.ws.WbServis_Service();
        org.foi.nwtis.lurajcevi.ws.WbServis port = service.getWbServisPort();
        return port.dohvatiAktivneZipove();
    }

    private static MeteoPodaci dohvatiPodatkeOZipKodu(java.lang.String zip) {
        org.foi.nwtis.lurajcevi.ws.WbServis_Service service = new org.foi.nwtis.lurajcevi.ws.WbServis_Service();
        org.foi.nwtis.lurajcevi.ws.WbServis port = service.getWbServisPort();
        return port.dohvatiPodatkeOZipKodu(zip);
    }

    private static java.util.List<net.wxbug.api.MeteoPodaci> dohvatiPodatkeInterval(java.lang.String zip, java.lang.String date1, java.lang.String date2) {
        org.foi.nwtis.lurajcevi.ws.WbServis_Service service = new org.foi.nwtis.lurajcevi.ws.WbServis_Service();
        org.foi.nwtis.lurajcevi.ws.WbServis port = service.getWbServisPort();
        return port.dohvatiPodatkeInterval(zip, date1, date2);
    }

    private static java.util.List<net.wxbug.api.MeteoPodaci> dohvatiPosljednjihNPodatakaZaZipKod(int n, java.lang.String zip) {
        org.foi.nwtis.lurajcevi.ws.WbServis_Service service = new org.foi.nwtis.lurajcevi.ws.WbServis_Service();
        org.foi.nwtis.lurajcevi.ws.WbServis port = service.getWbServisPort();
        return port.dohvatiPosljednjihNPodatakaZaZipKod(n, zip);
    }

    private static java.util.List<java.lang.String> dohvatiZipoveSNajvisePodataka(int n) {
        org.foi.nwtis.lurajcevi.ws.WbServis_Service service = new org.foi.nwtis.lurajcevi.ws.WbServis_Service();
        org.foi.nwtis.lurajcevi.ws.WbServis port = service.getWbServisPort();
        return port.dohvatiZipoveSNajvisePodataka(n);
    }
    
    
    
}
