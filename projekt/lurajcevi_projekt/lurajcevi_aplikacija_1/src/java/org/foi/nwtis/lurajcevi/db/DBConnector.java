
package org.foi.nwtis.lurajcevi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.lurajcevi.ServerKomanda;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.lurajcevi.slusaci.SlusacAplikacije;
import org.foi.nwtis.lurajcevi.web.HttpZahtjev;
import org.foi.nwtis.lurajcevi.ws.MeteoPodaci;

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
    
    public static List<HttpZahtjev> dohvatiPopisZahtjevaPremaServeru(String baza) throws ClassNotFoundException{
        String upit = "SELECT * FROM lurajcevi_dnevnik_zahtjeva";
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        List<HttpZahtjev> zahtjevi = new ArrayList<HttpZahtjev>();
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery(upit);
            while (rs.next()){
               HttpZahtjev z = new HttpZahtjev(rs.getInt("id"), 
                                               rs.getString("zahtjev"), 
                                               rs.getString("trajanje_obrade"));
               zahtjevi.add(z);
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
        return zahtjevi;
    }
    
    public static List<ServerKomanda> dohvatiPopisSocketServerKomandi(String baza) throws ClassNotFoundException{
        String upit = "SELECT * FROM lurajcevi_dnevnik_servera";
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        List<ServerKomanda> komande = new ArrayList<ServerKomanda>();
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery(upit);
            while (rs.next()){
                ServerKomanda z = new ServerKomanda(rs.getInt("id"), 
                                               rs.getString("naredba"));
               komande.add(z);
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
        return komande;
    }
    
    public static List<MeteoPodaci> dohvatiMeteoPodatke() throws ClassNotFoundException{
        String upit = "SELECT * FROM lurajcevi_podaci_zip";
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        List<MeteoPodaci> podaci = new ArrayList<MeteoPodaci>();
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery(upit);
            while(rs.next()){
                MeteoPodaci p = new MeteoPodaci(rs.getString("zip_trazeni"),
                                                rs.getString("zip_vraceni"),
                                                rs.getString("temperatura"),
                                                rs.getString("vlaga"),
                                                rs.getString("geo_duzina"),
                                                rs.getString("geo_sirina"),
                                                rs.getString("grad"),
                                                rs.getString("vjetar"),
                                                rs.getString("tlak"),
                                                rs.getString("datum"));
                podaci.add(p);
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
        return podaci;
    }
    
    public static void unesiPodatke(Map<String, LiveWeatherData>  weatherData) throws ClassNotFoundException{
        String dbName = "lurajcevi_podaci_zip";
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
               /* rs = instr.executeQuery("SELECT COUNT(*) FROM " + dbName + " WHERE zip_trazeni = '" + entry.getKey() + "'");
                rs.next();
                if (rs.getInt(1) == 0){*/
                System.out.println("Inserting" + entry.getValue().getCity());
                    instr.execute("INSERT INTO " + dbName +" VALUES (DEFAULT, '"
                       + entry.getKey() + "','"+ entry.getValue().getZipCode() + "','" + entry.getValue().getTemperature() + "','"
                       + entry.getValue().getHumidity() + "','" + entry.getValue().getLatitude() + "','" + entry.getValue().getLongitude()
                       + "','" + entry.getValue().getCity() + "','" +entry.getValue().getWindSpeed() + "','" +entry.getValue().getPressure() + "', now())");
                /*} else{
                    instr.execute("UPDATE " + dbName + " SET zip_vraceni = '" + entry.getValue().getZipCode() + "',"
                       + "temperatura = '" + entry.getValue().getTemperature() + "'," + "vlaga = '" + entry.getValue().getHumidity() + "',"
                       + "geo_duzina = '" + entry.getValue().getLatitude() + "'," + "geo_sirina = '" + entry.getValue().getLongitude() + "',"
                       + "grad = '" + entry.getValue().getCity() + "'," + "vjetar = '" + entry.getValue().getWindSpeed() + "',"
                       + "tlak = '" + entry.getValue().getPressure() + "' WHERE zip_trazeni = '" + entry.getKey() + "'");
                }*/
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
    
    public static void unesiPodatke(String baza, String zahtjev, String trajanje) throws ClassNotFoundException{
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
            instr.execute("INSERT INTO " + baza + " VALUES (DEFAULT, '" + zahtjev + "','"+ trajanje + "')");
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
            rs = instr.executeQuery("SELECT COUNT(*) as c FROM " + baza + " WHERE zip_code = '" + zip + "'");
                rs.next();
                if (rs.getInt("c") == 1){
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
            String temp = new DecimalFormat("##.##").format(rs.getDouble("temperatura"));
            String vlaga = new DecimalFormat("##.##").format(rs.getDouble("vlaga"));
            String tlak = new DecimalFormat("####.##").format(rs.getDouble("tlak"));
            String geo_sir = new DecimalFormat("###.######").format(rs.getDouble("geo_sirina"));
            String geo_duz = new DecimalFormat("###.######").format(rs.getDouble("geo_duzina"));
            odgovor = "TEMP " + temp + " VLAGA " + vlaga + " TLAK " + tlak + 
                   " GEOSIR " + geo_sir + " GEODUZ " + geo_duz;
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
    
    public static MeteoPodaci dohvatiPodatkeOZipKoduMeteo(String baza, String zip) throws ClassNotFoundException{
         String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        MeteoPodaci p = null;
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery("SELECT * FROM " + baza + " WHERE zip_trazeni = '" + zip + "' ORDER BY id DESC LIMIT 1");
            rs.next();
            p = new MeteoPodaci(rs.getString("zip_trazeni"),
                                                rs.getString("zip_vraceni"),
                                                rs.getString("temperatura"),
                                                rs.getString("vlaga"),
                                                rs.getString("geo_duzina"),
                                                rs.getString("geo_sirina"),
                                                rs.getString("grad"),
                                                rs.getString("vjetar"),
                                                rs.getString("tlak"),
                                                rs.getString("datum"));
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
        return p;
    }
    
    public static List<String> dohvatiTopZipove(String baza, int n) throws ClassNotFoundException, SQLException{
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        List<String> mp = new ArrayList<String>();
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            
                rs = instr.executeQuery("SELECT zip_trazeni, COUNT(*) AS c FROM " + baza + " GROUP BY zip_trazeni LIMIT " + n );
                while (rs.next()){
                    mp.add(rs.getString("zip_trazeni") + ":" + rs.getString("c"));
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
        return mp;
    }

    public static List<MeteoPodaci> dohvatiNajnovijePodatke(String baza, String zip, int n) throws ClassNotFoundException, SQLException{
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        List<MeteoPodaci> podaci = new ArrayList<MeteoPodaci>();
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            /*
            rs = instr.executeQuery("SELECT * FROM " + baza + 
                                    " WHERE id IN " +
                                      "(SELECT max(id) FROM " + baza + 
                                          "WHERE zip_trazeni='" + zip + 
                                            "' LIMIT " + n + "");
                                            * */
            rs = instr.executeQuery("SELECT * FROM " + baza +
                                     "WHERE zip_trazeni = '" + zip + "' ORDER BY id DESC LIMIT " + n);
            while(rs.next()){
                MeteoPodaci p = new MeteoPodaci(rs.getString("zip_trazeni"),
                                                rs.getString("zip_vraceni"),
                                                rs.getString("temperatura"),
                                                rs.getString("vlaga"),
                                                rs.getString("geo_duzina"),
                                                rs.getString("geo_sirina"),
                                                rs.getString("grad"),
                                                rs.getString("vjetar"),
                                                rs.getString("tlak"),
                                                rs.getString("datum"));
                podaci.add(p);
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
        return podaci;
    }

    public static List<MeteoPodaci> dohvatiPodatkeInterval(String baza, String zip, String d1, String d2) throws ClassNotFoundException, SQLException{
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        Connection veza = null;
        Statement instr = null;
        ResultSet rs = null;
        List<MeteoPodaci> podaci = new ArrayList<MeteoPodaci>();
        try{
            Class.forName(bpKonf.getDriver_database(url));
            veza = DriverManager.getConnection(url, korisnik, lozinka);
            instr = veza.createStatement();
            rs = instr.executeQuery("SELECT * FROM " + baza + " WHERE datum BETWEEN '" + d1 + "' AND '" + d2 + "'");
            while(rs.next()){
                MeteoPodaci p = new MeteoPodaci(rs.getString("zip_trazeni"),
                                                rs.getString("zip_vraceni"),
                                                rs.getString("temperatura"),
                                                rs.getString("vlaga"),
                                                rs.getString("geo_duzina"),
                                                rs.getString("geo_sirina"),
                                                rs.getString("grad"),
                                                rs.getString("vjetar"),
                                                rs.getString("tlak"),
                                                rs.getString("datum"));
                podaci.add(p);
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
        return podaci;
    }
    
    public static boolean unesiUDnevnikSocketServera(String baza, String naredba) throws ClassNotFoundException{
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
            instr.execute("INSERT INTO " + baza + " VALUES (DEFAULT, '" + naredba + "')");
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
}
