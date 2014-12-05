import java.util.Random;

/**
 * A simple class that returns a random value from a set of defined values
 * @author Andree
 *
 */
public class Spinner {
	private Random rand = new Random();
	private int[] dial = {1, 2, 3, 4, 5, -1, -2, -3}; //possible values to be returned from the spinner
	
	//returns a random value from the spinner
	public int spin() {
		return dial[rand.nextInt(8)];
	}
}
