package org.foi.nwtis.lurajcevi.kvadrati;
public class Kvadrati{

	protected int odBroja;
	protected int doBroja;

	public Kvadrati(int _od, int do_){
		this.odBroja = _od;
		this.doBroja = do_;
	}
	
	public void ispis(){
	
		for (int i = odBroja; i < doBroja; i++){
			System.out.println( i + " * " + i + " = " + i * i);
		}

	}
	
}