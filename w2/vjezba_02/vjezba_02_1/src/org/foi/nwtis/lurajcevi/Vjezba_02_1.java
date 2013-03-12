
package org.foi.nwtis.lurajcevi;
import org.foi.nwtis.lurajcevi.kvadrati.*;

public class Vjezba_02_1{

	public static void main(String[] args){

		if (args.length != 3){
			System.out.println("Trebam 3 argumenta.");
			System.exit(0);
		}
		int odBroja = 0;
		int doBroja = 0;
		try{
			odBroja = Integer.parseInt(args[0]);
			doBroja = Integer.parseInt(args[1]);
		} 
		catch(NumberFormatException e){
			System.out.println("Molim unesite brojeve.");
			System.exit(0);
		}
		switch(Integer.parseInt(args[2])){
			case 0:
				Kvadrati kvad = new Kvadrati(odBroja, doBroja);
				kvad.ispis();
				break;
			case 1:
				NeparniKvadrati_1 nkvad1 = new NeparniKvadrati_1(odBroja, doBroja);
				nkvad1.ispis();
				break;
			case 2:
				Kvadrati nkvad2 = new NeparniKvadrati_1(odBroja, doBroja);
				nkvad2.ispis();
				break;
			case 3:
				Kvadrati nkvad3 = new NeparniKvadrati_2(odBroja, doBroja);
				nkvad3.ispis();
				break;
			case 4:
				NeparniKvadrati_3 nkvad4 = new NeparniKvadrati_3(odBroja, doBroja);
				nkvad4.ispisiPodatke();
				break;
			case 5:
				Ispisivac_1 nkvad5 = new NeparniKvadrati_3(odBroja, doBroja);
				nkvad5.ispisiPodatke();
				//System.out.println(nkvad5.getClass().getName());
				break;
			case 6:
				NeparniKvadrati_4 nkvad6 = new NeparniKvadrati_4(odBroja, doBroja);
				nkvad6.ispisiPodatkeLinijski();
				//System.out.println(nkvad5.getClass().getName());
				break;
			case 7:
				Kvadrati nkvad7 = new NeparniKvadrati_4(odBroja, doBroja);
				nkvad7.ispis();
				//System.out.println(nkvad5.getClass().getName());
				break;
			case 8:
				Ispisivac_1 nkvad8 = new NeparniKvadrati_4(odBroja, doBroja);
				nkvad8.ispisiPodatke();
				//System.out.println(nkvad5.getClass().getName());
				break;
			case 9:
				Ispisivac_2 nkvad9 = new NeparniKvadrati_4(odBroja, doBroja);
				nkvad9.ispisiPodatkeLinijski();
				//System.out.println(nkvad5.getClass().getName());
				break;
			case 10:

				for (int i = 0; i < 10; i++){
					Ispisivac_1  i1 =  NeparniKvadrati_5.kreirajIspisivac_1(odBroja, doBroja);
					i1.ispisiPodatke();
					System.out.println(i1.getClass().getName());
				}
				
				break;
			default:
				System.out.println("Nepoznati unos");
				break;
		}
	}
}