package org.foi.nwtis.lurajcevi.kvadrati;
public class NeparniKvadrati_2 extends Kvadrati{

	public NeparniKvadrati_2(int odBroja, int doBroja){
			super(odBroja, doBroja);
	}
	
	public void ispis(){
	
		int poc = (odBroja % 2 == 0) ? odBroja + 1 : odBroja;
	
		for (int i = poc; i < doBroja; i += 4){

				System.out.println( i + " * " + i + " = " + i * i);
		}

	}
	
}