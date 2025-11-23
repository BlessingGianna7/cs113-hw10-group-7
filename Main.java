public class Main {

	public static void main(String[] args) {
	
		TextInputHumanPlayer player1 = new TextInputHumanPlayer("Gianna");
		TextInputHumanPlayer player2 = new TextInputHumanPlayer("Anastasia");
		Chess game = new Chess(player1, player2);
		game.play();
	}

}
