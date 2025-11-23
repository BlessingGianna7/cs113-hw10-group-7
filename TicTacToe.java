public class TicTacToe extends TurnBasedGame {

	String[8][8] gameboard;

	//make board generator

	public TicTacToe(Player player1, Player player2) {
		super(player1, player2);
	}
//add to check if [i][j] is their own
	private boolean isValidPosition(int position) {
		if(position >= 0 && position <= 8 && gameboard[position].equals(" ")) {
			return true;
		}
		else {
			return false;
		}
	}
//make a HasBlackOne and a HasWhiteOne
	private boolean hasThreeInARow(String marker) {
		boolean flag0 = gameboard[0].equals(marker);
		boolean flag1 = gameboard[1].equals(marker);
		boolean flag2 = gameboard[2].equals(marker);
		boolean flag3 = gameboard[3].equals(marker);
		boolean flag4 = gameboard[4].equals(marker);
		boolean flag5 = gameboard[5].equals(marker);
		boolean flag6 = gameboard[6].equals(marker);
		boolean flag7 = gameboard[7].equals(marker);
		boolean flag8 = gameboard[8].equals(marker);

		if((flag0 && flag1 && flag2) || (flag3 && flag4 && flag5) || (flag6 && flag7 && flag8) ||
		   (flag0 && flag3 && flag6) || (flag1 && flag4 && flag7) || (flag2 && flag5 && flag8) ||
		   (flag0 && flag4 && flag8) || (flag2 && flag4 && flag6)) {
			return true;
		}
		else {
			return false;
		}
	}
//delete	
	private boolean gameboardIsFull() {
		for (String marker : gameboard) {
			if(marker.equals(" ")) {
				return false;
			}
		}
		return true;
	}

	private void printGameboard() {
//make it like minesweeper
		System.out.println(gameboard[0] + "|" + gameboard[1] + "|" + gameboard[2]);
		System.out.println("-----");
		System.out.println(gameboard[3] + "|" + gameboard[4] + "|" + gameboard[5]);
		System.out.println("-----");
		System.out.println(gameboard[6] + "|" + gameboard[7] + "|" + gameboard[8]);
	}

	private void printWinner() {
//customize to fit our game
		if(hasThreeInARow("X")) {
			System.out.println("*** " + player1.getName() + " wins! ***");
		}
		else if(hasThreeInARow("O")) {
			System.out.println("*** " + player2.getName() + " wins! ***");
		}
		else {
			System.out.println("*** " + player1.getName() + " and " + player2.getName() + " tie! ***");
		}
	}

	protected void evaluateMove(String move) {
//PARSE 4 THINGS
		int position = Integer.parseInt(move);
		if(isFirstPlayerTurn()) {
			if(!isValidPosition(position)) {
				// throw new IllegalArgumentException("Player 1's move was invalid.");
				this.evaluateMove(player1.makeOneMove());
			}
			else {
//PUT OUR GAME LOGIC HERE FOR X
				
			}
		}
		else {
			if(!isValidPosition(position)) {
				throw new IllegalArgumentException("Player 2's move was invalid.");
			}
			else {
//GAME  LOGIC FOR O
				gameboard[position] = "O";
			}
		}
	}

	public void play() {
		System.out.println("=== Game Started ===\n");
		System.out.println("Numbers 0 through 8 are valid inputs\n");
		printGameboard();
		System.out.println();
//replace with check O & X wins
		while(!hasThreeInARow("X") && !hasThreeInARow("O") && !gameboardIsFull()) {
			System.out.println("* " + getCurrentPlayerName() + "'s Turn *");
			playOneTurn();
			System.out.println();
			printGameboard();
			System.out.println();
		}

		printWinner();
		System.out.println();
		System.out.println("=== Game Finished ===");
	}
}
