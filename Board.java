import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Board {
	private ArrayList<Square> squares;
	
	/**
	 * Creates the board by setting the squares to the
	 * appropriate value
	 * @throws FileNotFoundException 
	 */
	public Board(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		squares = new ArrayList<Square>();
		
		//Below for testing only
		//int index = 0;
		
		
		/*
		 * The below code will get the next line in a file
		 * It then divides the string around a comma
		 * The first substring will be the Square type
		 * The second substring will be the name of the Square
		 * It then creates a square in the squares ArrayList
		 * Based on the type
		 */
		int commaIndex = 0;
		String line= "";
		String type = "";
		String label = "";
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			commaIndex = line.indexOf(",");
			type = line.substring(0, commaIndex);
			label = line.substring(++commaIndex, line.length());
			
			if(type.equals("Square")) {
				squares.add(new Square(label));				
			} else if(type.equals("PrizeSquare")) {
				squares.add(new PrizeSquare(label));
			} else if(type.equals("PenaltySquare")) {
				squares.add(new PenaltySquare(label));
			} else if(type.equals("LastSquare")) {
				squares.add(new LastSquare(label));
			}
			
			//Below is for testing only
			//System.out.println(squares.get(index));
			//index++;
		}
		scanner.close();
	}
	
	/**
	 * 
	 * @return The Square at the first spot on the Board
	 */
	public Square getStartSquare() {
		return squares.get(0);
	}
	
	/**
	 * 
	 * @return The Square at the last spot on the Board
	 */
	public Square getLastSquare() {
		return squares.get(squares.size()-1);
	}
	
	/**
	 * This method will return a Square that is either the next desired
	 * Square or the one the Player is currently on if. The Player cannot
	 * move past the last Square of the Board!
	 * @param startSquare The Square the Player is currently on
	 * @param next The amount determined by the Spinner
	 * @return A Square that is either the current Square or the Square at next
	 */
	public Square move(Square startSquare, int next) {
		int position = squares.indexOf(startSquare);
		int newPos = position + next;
		
		if(newPos < squares.size() && newPos >= 0) {
			return squares.get(position + next);
		}
		else 
			return squares.get(position);
	}
}
