package org.foi.nwtis.lurajcevi.kvadrati;
public class NeparniKvadrati_5 {

	public static Ispisivac_1 kreirajIspisivac(int odBroja, int doBroja){
		long i = System.currentTimeMillis() % 3;
		switch(Long.toString(i)){
			case "0":
				return new NeparniKvadrati_3(odBroja, doBroja);

			case "1":
				return new KolikoJeSati();

			default:
				return new NeparniKvadrati_3(odBroja, doBroja * 2);

		}
	}
	
}