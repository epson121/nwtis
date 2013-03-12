package aplikacija;
import podrska.Faktorijela;

public class Vjezba_01_4{

	public static void main(String[] args){
	
		int broj = 0;
		if (args.length == 1 ){
			broj = Integer.parseInt(args[0]);
			Faktorijela f = new Faktorijela(broj);
			System.out.println(broj + " faktorijela = " + f.dajFaktorijelu());
		}
		else{
			System.out.println("Trebam tocno jedan argument.");
			System.exit(0);
		}
		
	}

}