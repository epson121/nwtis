
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lurajcevi
 */
public class RegexTester {

    /*
     *
     * @param args the command line arguments
     */
	 
    public static void main(String[] args) {
	
	    //-server -port port -konf datoteka[.txt | .xml] [-load] -s datoteka
        String reg1 = "^-server\\s+-port\\s+\\d{4}\\s+-konf\\s+[a-zA-Z]+\\.(txt|xml)\\s*(-load\\s+)?-s\\s+[a-zA-Z]+$";

        //-admin -ts ipadresa -port port -u korisnik -p lozinka -konf datoteka[.txt | .xml] 
        //[-t dd.mm.yyyy hh:mm:ss | [PAUSE | START | STOP | CLEAN]]
        String reg2 = "^-admin\\s+-ts\\s+(\\d{1,3}\\.){3}(\\d{1,3})\\s+-port\\s+\\d{4}\\s+-u\\s+[a-zA-Z]+\\s+-p\\s+[a-zA-Z]+\\s+-konf\\s+[a-zA-Z]+\\.(txt|xml)\\s*(-t\\s+\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2}:\\d{2}|START|STOP|PAUSE|CLEAN)$";
              
        //-user -ts ipadresa -port port -u korisnik -konf datoteka[.txt | .xml]      
        String reg3 = "^-user\\s+-ts\\s+(\\d{1,3}\\.){3}(\\d{1,3})\\s+-port\\s+\\d{4}\\s+-u\\s+[a-zA-Z]+\\s+-konf\\s+[a-zA-Z]+\\.(txt|xml)$";

        //-show -s datoteka
        String reg4 = "^-show\\s+-s\\s+[a-zA-Z]+$";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) { 
            sb.append(args[i]).append(" ");
        }
        String p = sb.toString().trim();
        //Pattern pattern = Pattern.compile(reg1);
        //Pattern pattern = Pattern.compile(reg2);
        //Pattern pattern = Pattern.compile(reg3);
        Pattern pattern = Pattern.compile(reg4);
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