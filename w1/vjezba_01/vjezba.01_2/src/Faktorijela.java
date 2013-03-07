

class Faktorijela{

	private long fact = 1;

	Faktorijela(int unos){
		for ( int i = 1; i <= unos; i++){
			fact *= i;
		}
	}
	
	public long dajFaktorijelu(){
		return fact;
	}

}