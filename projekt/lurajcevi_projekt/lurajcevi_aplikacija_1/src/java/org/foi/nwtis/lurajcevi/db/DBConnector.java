
package org.foi.nwtis.lurajcevi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.lurajcevi.slusaci.SlusacAplikacije;

/**
 * @document DBConnector
 * @author Luka Rajcevic
 */
public class DBConnector {
    
    private static BP_Konfiguracija bpKonf = SlusacAplikacije.bpKonf;
    
    public static List<String> dohvatiPodatke() throws ClassNotFoundException{
        String upit = "SELECT * FROM lurajcevi_activezipcodes";
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        List<String> zipovi = new ArrayList<String>();
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery(upit);
            while (rs.next()){
                System.out.println("zip: " + rs.getString("zip_code"));
               zipovi.add(rs.getString("zip_code"));
            }
        } catch(SQLException e){
            if (veza != null){
                veza = null;
            }
            if (instr != null){
                instr = null;
            }
            if (rs != null){
                rs = null;
            }
            e.printStackTrace();
        }
        return zipovi;
    }
    
    public static void unesiPodatke(Map<String, LiveWeatherData>  weatherData) throws ClassNotFoundException{
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            for (Map.Entry<String, LiveWeatherData> entry : weatherData.entrySet()) {
                rs = instr.executeQuery("SELECT COUNT(*) FROM lurajcevi_zip_podaci WHERE zip_trazeni = '" + entry.getKey() + "'");
                rs.next();
                if (rs.getInt(1) == 0){
                    instr.execute("INSERT INTO lurajcevi_zip_podaci VALUES ('"
                       + entry.getKey() + "','"+ entry.getValue().getZipCode() + "','" + entry.getValue().getTemperature() + "','"
                       + entry.getValue().getHumidity() + "','" + entry.getValue().getLatitude() + "','" + entry.getValue().getLongitude()
                       + "','" + entry.getValue().getCity() + "','" +entry.getValue().getWindSpeed() + "','" +entry.getValue().getPressure() + "')");
                } else{
                    instr.execute("UPDATE lurajcevi_zip_podaci SET zip_vraceni = '" + entry.getValue().getZipCode() + "',"
                       + "temperatura = '" + entry.getValue().getTemperature() + "'," + "vlaga = '" + entry.getValue().getHumidity() + "',"
                       + "geo_duzina = '" + entry.getValue().getLatitude() + "'," + "geo_sirina = '" + entry.getValue().getLongitude() + "',"
                       + "grad = '" + entry.getValue().getCity() + "'," + "vjetar = '" + entry.getValue().getWindSpeed() + "',"
                       + "tlak = '" + entry.getValue().getPressure() + "' WHERE zip_trazeni = '" + entry.getKey() + "'");
                }
            }
        } catch(SQLException e){
            if (veza != null){
                veza = null;
            }
            if (instr != null){
                instr = null;
            }
            if (rs != null){
                rs = null;
            }
            e.printStackTrace();
        }
    }
   
    public static boolean provjeriZip(String baza, String zip) throws ClassNotFoundException{
         String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        boolean postoji = false;
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery("SELECT COUNT(*) FROM " + baza + " WHERE zip_code = '" + zip + "'");
                rs.next();
                if (rs.getInt(1) == 1){
                    postoji = true;
                }
        } catch(SQLException e){
            if (veza != null){
                veza = null;
            }
            if (instr != null){
                instr = null;
            }
            if (rs != null){
                rs = null;
            }
            e.printStackTrace();
        }
        return postoji;
    }
    
    public static boolean provjeriKorisnika(String baza, String ime, String loz) throws ClassNotFoundException{
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        boolean postoji = false;
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery("SELECT COUNT(*) FROM " + baza + " WHERE ime = '" + ime + "' AND lozinka ='" + loz + "'");
                rs.next();
                if (rs.getInt(1) == 1){
                    postoji = true;
                }
        } catch(SQLException e){
            if (veza != null){
                veza = null;
            }
            if (instr != null){
                instr = null;
            }
            if (rs != null){
                rs = null;
            }
            e.printStackTrace();
        }
        return postoji;
    }
    
    public static boolean unesiUBazu(String baza, String zip, String zatrazio) throws ClassNotFoundException{
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        boolean postoji = false;
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            postoji = provjeriZip("lurajcevi_activezipcodes", zip);
            if (!postoji)
                instr.execute("INSERT INTO " + baza + " VALUES ('" + zip + "', '" + zatrazio + "') ");
        } catch(SQLException e){
            if (veza != null){
                veza = null;
            }
            if (instr != null){
                instr = null;
            }
            if (rs != null){
                rs = null;
            }
            e.printStackTrace();
        }
        return postoji;
    }
    
    public static String dohvatiPodatkeOZipKodu(String baza, String zip) throws ClassNotFoundException, SQLException{
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        String odgovor = "";
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery("SELECT * FROM " + baza + " WHERE zip_trazeni = '" + zip + "'");
            rs.next();
            //TEMP nn.nn VLAGA nn.nn TLAK nnnn.nn GEOSIR {-
            //}nnn.nnnnnn GEODUZ {-}nnn.nnnnnn
            //TODO oblikovati prema formatu
            odgovor = "TEMP " + rs.getString("temperatura") + " VLAGA " + rs.getString("vlaga") + 
                             " TLAK " + rs.getString("tlak") + " GEOSIR " + rs.getString("geo_sirina") + 
                             " GEODUZ " + rs.getString("geo_duzina");
        } catch(SQLException e){
            if (veza != null){
                veza = null;
            }
            if (instr != null){
                instr = null;
            }
            if (rs != null){
                rs = null;
            }
            e.printStackTrace();
        }
        return odgovor;
    }
    
}
