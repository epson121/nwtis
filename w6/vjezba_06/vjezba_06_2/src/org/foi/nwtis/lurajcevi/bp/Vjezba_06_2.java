/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.bp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;

/**
 *
 * @author nwtis_2
 */
public class Vjezba_06_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (args.length != 1){
            System.out.println("Neispravan broj argumenata.");
            return;
        }
        BP_Konfiguracija bpKonf = new BP_Konfiguracija(args[0]);
        String upit = "SELECT ime, prezime FROM polaznici";
        String url = bpKonf.getServer_database() + bpKonf.getUser_database();
        String korisnik = bpKonf.getUser_username();
        String lozinka = bpKonf.getUser_password();
        
        try (
                Connection veza = DriverManager.getConnection(url, korisnik, lozinka);
                Statement instr = veza.createStatement();
                ResultSet rs = instr.executeQuery(upit);
            ) 
            {
            while (rs.next()) {
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                System.out.printf("Ime: %s, Prezime: %s%n", ime, prezime);
                //System.out.println("Ime: " + ime + "\nPrezime: " + prezime + "\n");
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
                
    }
}
