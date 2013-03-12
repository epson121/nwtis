package org.foi.nwtis.lurajcevi.kvadrati;
public class NeparniKvadrati_4 extends NeparniKvadrati_2 implements Ispisivac_2{

	public NeparniKvadrati_4(int odBroja, int doBroja){
			super(odBroja, doBroja);
	}
	
	public void ispisiPodatkeLinijski(){
	
		int poc = (odBroja % 2 == 0) ? odBroja + 1 : odBroja;
	
		for (int i = poc; i < doBroja; i += 2){

				System.out.print( i + " * " + i + " = " + i * i);
		}
		System.out.println();

	}

	public void ispisiPodatke(){
	 	ispis();
	}
	
}