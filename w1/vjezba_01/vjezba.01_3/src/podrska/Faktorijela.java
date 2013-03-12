package podrska;

public class Faktorijela{

	private long fact = 1;

	public Faktorijela(int unos){
		for ( int i = 1; i <= unos; i++){
			fact *= i;
		}
	}
	
	public long dajFaktorijelu(){
		return fact;
	}

}