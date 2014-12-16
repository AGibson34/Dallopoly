import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Dallopoly {
	private ArrayList<Player> players;
	private Spinner theSpinner;
	private Board theBoard;
	
	//set a flag that will change when the game ends 															(v0.1.1 change)
	boolean gameOver = false;
	
	/**
	 * Creates the Board, the Spinner, then the Players and puts the
	 * players at the start Square.
	 * @throws FileNotFoundException 
	 */
	public Dallopoly() throws FileNotFoundException {
		File file = new File("src/squareList.txt"); //File of squares list in src folder
		theBoard = new Board(file);
		theSpinner = new Spinner();
		players = new ArrayList<Player>();
	}
	
	/**
	 * Alternates turns for players and checks victory conditions
	 */
	public String playGame() {
		//String variable to hold all text from the game being played
		String gameInfo = "";
		
		//FOR TESTING PURPOSES ONLY!
		//addPlayer("Horse");
		//addPlayer("Iron");
		
		//If the game isn't over this method will play through an entire turn (until someone wins) 				(v0.1.1 change)
		if(!gameOver) {
			/* Loop through the ArrayList of players and send each player
			 * the "takeTurn" message. After the player has moved compare
			 * the player's new square to the boards lastSquare. If the
			 * player is on the Board's last Square determine who
			 * the winner is based on the money of each player
			 */
			for(int i = 0; i < players.size(); i++) {
				gameInfo += players.get(i).takeTurn(theSpinner, theBoard);
				
				//Beautification text
				if(i == players.size()-1)
					gameInfo += "\n";
				//-------------------
				
				if(players.get(i).getCurrentSquare().getLabel() == theBoard.getLastSquare().getLabel()) {
					gameInfo += "\n"; //Beautification text
					
					
					/**************
					 
					  The following code is a for testing a tie scenario
					 
					  for(Player player: players) {
					   	player.setMoney(1000);
					  }
					   
					 **************/
					
					//Setup to check for win conditions
					int amount = 0;
					ArrayList<Player> winner = new ArrayList<Player>();
					
					//Check all players currently held amount of money to determine a winner
					for(Player player: players) {
						if(amount == player.getMoney()) {
							winner.add(player); 
						}
						else if(amount < player.getMoney()) {
							amount = player.getMoney();
							winner.clear(); //Clear the ArrayList in case there is a tie
							winner.add(player); //Set the current winner
							player = players.get(i);
						}
					}
					
					//Add each winner to the gameInfo String
					if(winner.size() > 1) {
						gameInfo += "There is a tie! The winners are:";
						for(int j = 0; j < winner.size(); j++) {
							gameInfo += " " + winner.get(j).getName();
							
							//Proper English grammar. If there are no additional winners the "and" will not be concatenated
							if(j < winner.size()-1)
								gameInfo += " and";
						}
						gameInfo += " with $ " + winner.get(0).getMoney();
					}
					else {
						gameInfo += "The winner is " + winner.get(0).getName() + " with $" + winner.get(0).getMoney() + "!";
					}
					
					gameOver = true;
					break;
				}	
			}
		
		}
		
		//System.out.println(gameInfo); // For Testing Purposes only
		
		return gameInfo;
	}
	
	public String addPlayer(String name) {
		players.add(new Player(name, theBoard.getStartSquare()));
		return name;
	}
}
