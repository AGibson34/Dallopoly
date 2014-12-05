public class LastSquare extends Square {
	public LastSquare(String label) {
		super(label);
	}
	
	public void landOn(Player p) {
		p.changeMoney(+300);
	}
}
