/**
 * A class that will define the individual squares on a "monopoly-like" board
 * @author Andree
 *
 */
public class Square {
	private String label;
	
	public Square(String label) {
		setLabel(label);
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String toString() {
		return label + " square";
	}
	
	public void landOn(Player p) {
		//Does nothing for simple square
	}
}
