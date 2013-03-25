
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author dkermek
 */
public class TestOpcija {

    /*
     *
     * @param args the command line arguments
     */
	 
    public static void main(String[] args) {
	
	// -server -port port -konf datoteka[.txt | .xml] [-load] -s datoteka
        String sintaksa = "^-server -port ([8-9]\\d{3}) -konf ([^\\s]+\\.(?i)txt|xml)( +-load)? -s ([^\\s]+\\.[^\\s]+) *$";
                                                                   
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) { 
            sb.append(args[i]).append(" ");
        }
        String p = sb.toString().trim();
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher m = pattern.matcher(p);
        boolean status = m.matches();
        if (status) {
            int poc = 0;
            int kraj = m.groupCount();
            for (int i = poc; i <= kraj; i++) {
                System.out.println(i + ". " + m.group(i));
            }
        } else {
			System.out.println("Ne odgovara!");
		}
    }
}
// -server -port port -konf datoteka.txt -s datoteka