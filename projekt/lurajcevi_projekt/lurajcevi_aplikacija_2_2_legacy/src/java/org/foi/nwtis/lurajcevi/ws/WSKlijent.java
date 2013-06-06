
package org.foi.nwtis.lurajcevi.ws;

/**
 * @document WSKlijent
 * @author Luka Rajcevic
 */
public class WSKlijent {
    
    
    public static java.util.List<java.lang.String> dohvatiAktivneZipove() {
        org.foi.nwtis.lurajcevi.ws.WbServis_Service service = new org.foi.nwtis.lurajcevi.ws.WbServis_Service();
        org.foi.nwtis.lurajcevi.ws.WbServis port = service.getWbServisPort();
        return port.dohvatiAktivneZipove();
    }

    public static MeteoPodaci dohvatiPodatkeOZipKodu(java.lang.String zip) {
        org.foi.nwtis.lurajcevi.ws.WbServis_Service service = new org.foi.nwtis.lurajcevi.ws.WbServis_Service();
        org.foi.nwtis.lurajcevi.ws.WbServis port = service.getWbServisPort();
        return port.dohvatiPodatkeOZipKodu(zip);
    }
    
    

}
