public class PrizeSquare extends Square {
	public PrizeSquare(String label) {
		super(label);
	}
	
	public void landOn(Player p) {
		p.changeMoney(+100);
	}
}
