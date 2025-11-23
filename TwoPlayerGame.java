abstract public class TwoPlayerGame {

	protected Player player1;
	protected Player player2;

	public TwoPlayerGame(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
	}

	abstract public void play();

}
