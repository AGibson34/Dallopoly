/**
 * This class will handle everything that relates to the Player like Name,
 * position (Square), and the ability to take a turn.
 * @author Andree
 *
 */
public class Player {
	private String name;
	private Square currentSquare;
	private int money;
	
	/**
	 * Creates a player with name at startSquare
	 * @param name Name of the player piece
	 * @param startSquare The first Square on the Board
	 */
	public Player(String name, Square startSquare) {
		setName(name);
		setCurrentSquare(startSquare);
		setMoney(1000);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Square getCurrentSquare() {
		return currentSquare;
	}
	
	public void setCurrentSquare(Square currentSquare) {
		this.currentSquare = currentSquare;
	}
	
	/**
	 * Spins the Spinner and moves the Player around the Board if 
	 * possible to do so.
	 * @param theSpinner Game Spinner object associated with the Board
	 * @param theBoard The Board associate with the Game
	 */
	public String takeTurn(Spinner theSpinner, Board theBoard) {
		int spin = theSpinner.spin();
		setCurrentSquare(theBoard.move(getCurrentSquare(), spin));
		currentSquare.landOn(this);
		return getName() + " has spun " + spin + ". " + toString() + " " + getName() + " has $" + getMoney() + "\n";
	}
	
	public String toString() {
		return name + " is on " + currentSquare.toString() + ".";
	}
	
	public void setMoney(int amount) {
		money = amount;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void changeMoney(int amount) {
		money += amount;
	}
}
