package org.foi.nwtis.lurajcevi.ws;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.foi.nwtis.lurajcevi.db.DBConnector;

/**
 *
 * @author Luka Rajcevic
 */
@WebService(serviceName = "wb_servis")
public class wb_servis {
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "Hello")
    public String Hello() {
        return "Hello";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dohvatiAktivneZipove")
    public java.util.List<java.lang.String> dohvatiAktivneZipove() {
        List<String> podaci = null;
        try {
             podaci = DBConnector.dohvatiPodatke();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(wb_servis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return podaci;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dohvatiPodatkeOZipKodu")
    public org.foi.nwtis.lurajcevi.ws.MeteoPodaci dohvatiPodatkeOZipKodu(@WebParam(name = "zip") String zip) {
        MeteoPodaci lmp = null;
        try {
            lmp = DBConnector.dohvatiPodatkeOZipKoduMeteo("lurajcevi_podaci_zip", zip);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(wb_servis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lmp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dohvatiPosljednjihNPodatakaZaZipKod")
    public java.util.List<org.foi.nwtis.lurajcevi.ws.MeteoPodaci> dohvatiPosljednjihNPodatakaZaZipKod(@WebParam(name = "n") int n, @WebParam(name = "zip") String zip) {
        List<MeteoPodaci> lmp = null;
        try {
            lmp = DBConnector.dohvatiNajnovijePodatke("lurajcevi_podaci_zip", zip, n, 0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(wb_servis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(wb_servis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lmp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dohvatiZipoveSNajvisePodataka")
    public java.util.List<java.lang.String> dohvatiZipoveSNajvisePodataka(@WebParam(name = "n") int n) {
        List<String> ls = null;
        try {
            ls = DBConnector.dohvatiTopZipove("lurajcevi_podaci_zip", n);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(wb_servis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(wb_servis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dohvatiPodatkeInterval")
    public java.util.List<org.foi.nwtis.lurajcevi.ws.MeteoPodaci> dohvatiPodatkeInterval(@WebParam(name = "zip") String zip, @WebParam(name = "date1") String date1, @WebParam(name = "date2") String date2) {
        
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        String dateRegex = "^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}\\s[0-9]{2}\\:[0-9]{2}";
        Pattern p = Pattern.compile(dateRegex);
        Matcher m1 = p.matcher(date1);
        Matcher m2 = p.matcher(date2);
        if ( !m1.matches() && !m2.matches()){
            return null;
        }
        List<MeteoPodaci> lmp = null;
        try {
            lmp = DBConnector.dohvatiPodatkeInterval("lurajcevi_podaci_zip", zip, date1, date2);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(wb_servis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(wb_servis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lmp;
    }
    
    
    
}
