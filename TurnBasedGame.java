abstract public class TurnBasedGame extends TwoPlayerGame {

	private boolean firstPlayerTurn;

	public TurnBasedGame(Player player1, Player player2) {
		super(player1, player2);
		firstPlayerTurn = true;
	}

	public boolean isFirstPlayerTurn() {
		return firstPlayerTurn;
	}

	public String getCurrentPlayerName() {
		if(firstPlayerTurn) {
			return player1.getName();
		}
		else {
			return player2.getName();
		}
	}

	private void finishCurrentTurn() {
		firstPlayerTurn = !firstPlayerTurn;
	}

	protected void playOneTurn() {
		String move;

		if(firstPlayerTurn) {
			move = player1.makeOneMove();
		}
		else {
			move = player2.makeOneMove();
		}

		this.evaluateMove(move);
		this.finishCurrentTurn();
	}

	abstract protected void evaluateMove(String move);

}
