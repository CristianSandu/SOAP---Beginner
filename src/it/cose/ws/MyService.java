package it.cose.ws;
import java.util.Arrays;

public class MyService {
	public String echo(String message) {
	      return "Echo " + message;
	   }
	    
	   public Output sort(Input input) {
	      Arrays.sort(input.vector);
	      return new Output(input.vector);
	   }
	   
	   public int somma(int x, int y) {
		   return x+y;
	   }
}
