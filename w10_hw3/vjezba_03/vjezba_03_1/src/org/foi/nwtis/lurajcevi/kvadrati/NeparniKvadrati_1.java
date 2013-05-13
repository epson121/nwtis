package org.foi.nwtis.lurajcevi.kvadrati;
public class NeparniKvadrati_1 extends Kvadrati{

	public NeparniKvadrati_1(int odBroja, int doBroja){
			super(odBroja, doBroja);
	}
	
	public void ispis(){
	
		int poc = (odBroja % 2 == 0) ? odBroja + 1 : odBroja;
	
		for (int i = poc; i < doBroja; i += 2){

				System.out.println( i + " * " + i + " = " + i * i);
		}

	}
	
}