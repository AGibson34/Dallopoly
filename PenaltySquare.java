public class PenaltySquare extends Square {
	public PenaltySquare(String label) {
		super(label);
	}
	
	public void landOn(Player p) {
		p.changeMoney(-200);
	}
}
