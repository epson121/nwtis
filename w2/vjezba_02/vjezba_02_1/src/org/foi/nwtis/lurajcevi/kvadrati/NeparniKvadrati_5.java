package org.foi.nwtis.lurajcevi.kvadrati;
import java.util.*;

public class NeparniKvadrati_5 extends Kvadrati{

	public NeparniKvadrati_5(int odBroja, int doBroja){
			super(odBroja, doBroja);
	}
	
	
	public static Ispisivac_1 kreirajIspisivac_1(int odBroja, int doBroja) {
	 	Random rand = new Random();
	 	switch(rand.nextInt(3)){
	 		case 0:
	 			NeparniKvadrati_3 np3 = new NeparniKvadrati_3(odBroja, doBroja);
	 			return np3;
	 		case 1:
	 			NeparniKvadrati_4 np4 = new NeparniKvadrati_4(odBroja, doBroja);
	 			return np4;
	 		case 2:
	 			KolikoJeSati kjs = new KolikoJeSati(odBroja, doBroja);
	 			return kjs;
		 	}
		 	return null;
		 }
		 
	
}