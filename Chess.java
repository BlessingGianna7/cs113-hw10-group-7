import java.util.Scanner;
//Testing pushes to github, lets see if this works! -Anastasia
public class Chess extends TurnBasedGame {

	String[8][8] gameboard;

	//make board generator

	public Chess(Player player1, Player player2) {
		super(player1, player2);
	}
	//check if position is within bounds
	private boolean isValidPosition(int i, int j) {
		if (i < 0 || j < 0 ) return false;
		if (i >= gameboard.length || j >= gameboard[i].length){
			 return false;
		} else {
			return true;
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

	protected void evaluateMove(int m, int n, int i, int j) {
	    Scanner scanner = new Scanner(System.in);
 		if (!isValidPosition(i, j) || !isValidPosition(m, n)) {
       	 throw new IllegalArgumentException("Position out of bounds");
        }

		//player 1's turn X
		if (isFirstPlayerTurn()) {
			//check if piece belongs to player
        	if (!gameboard[i][j].equals("X")) {
           		throw new IllegalArgumentException("That's not your piece!");
        	}
			//check if there is pawn to capture
			if(isValidPosition(i + 1, j - 1) && gameboard[i + 1][j - 1].equals("O") || isValidPosition(i + 1, j + 1) && gameboard[i + 1][j + 1].equals("O")) {
				 System.out.println("There's a pawn you can capture! Do you want to capture? (yes/no)");
           		 String res = scanner.nextLine();
				 if(res.equals("yes")){
					if (isValidPosition(i + 1, j - 1) && gameboard[i + 1][j - 1].equals("O")) {
                		gameboard[i + 1][j - 1] = "X";
                	} else {
                    	gameboard[i + 1][j + 1] = "X";
                	}
                	gameboard[i][j] = ".";
                	return;
				}
			}
			if(n == j){
				//move forward 1 space
				if(m == i + 1 && gameboard[m][n].equals(".")) {
					gameboard[m][n] = "X";
					gameboard[i][j] = ".";
					return;
				//move forward 2 spaces from starting position	
				} else if(m == i + 2 && i == 1 && gameboard[i + 1][j].equals(".") && gameboard[m][n].equals(".")) {
					gameboard[m][n] = "X";
					gameboard[i][j] = ".";
					return;
				} else {
					throw new IllegalArgumentException("Invalid move");
				}

			} else {
				throw new IllegalArgumentException("Invalid move");
			}

 	    } else {
        // Player 2's turn O
       		if (!gameboard[i][j].equals("O")) {
            	throw new IllegalArgumentException("That's not your piece!");
			} 
			//check if there is pawn to capture
			if(isValidPosition(i - 1, j - 1) && gameboard[i - 1][j - 1].equals("X") || isValidPosition(i - 1, j + 1) && gameboard[i - 1][j + 1].equals("X")) {
				 System.out.println("There's a pawn you can capture! Do you want to capture? (yes/no)");
           		 String res = scanner.nextLine();
				 if(res.equals("yes")){
					if (isValidPosition(i - 1, j - 1) && gameboard[i + 1][j - 1].equals("X")) {
                		gameboard[i - 1][j - 1] = "O";
                	} else {
                    	gameboard[i - 1][j + 1] = "O";
                	}
                	gameboard[i][j] = ".";
                	return;
				}
			}
				if(n == j){
				//move forward 1 space	
				if(m == i - 1 && gameboard[m][n].equals(".")) {
					gameboard[m][n] = "O";
					gameboard[i][j] = ".";
					return;
				//move forward 2 spaces from starting position	
				} else if(m == i - 2 && i == 6 && gameboard[i - 1][j].equals(".") && gameboard[m][n].equals(".")) {
					gameboard[m][n] = "O";
					gameboard[i][j] = ".";
					return;
				} else {
					throw new IllegalArgumentException("Invalid move");
				}

			} else {
				throw new IllegalArgumentException("Invalid move");
			}

        
        }
		scanner.close();
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
