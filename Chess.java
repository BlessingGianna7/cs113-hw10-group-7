import java.util.Scanner;

public class Chess extends TurnBasedGame {
	public String[][] gameboard = new String[8][8];
	
	//make board generator
	public String[][] initGen(){ //generates the initial board, for use later	
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(i == 1){
					gameboard[i][j] = "B"; // black
				}else if(i == 6){
					gameboard[i][j] = "W"; //white
				}else{
					gameboard[i][j] = "."; //empty
				}
			}
		}
		return gameboard;
	}

	public void display(String[][] board){
		for(int i = 0; i < 8; i++){
			if(i == 0){//print out the guiding nums
				System.out.printf("  0 1 2 3 4 5 6 7 \n");
				System.out.printf("------------------\n");
			}
			for(int j = 0; j < 8; j++){
				if(j == 0){
					System.out.printf(""+i);
				}
				if(board[i][j].equals("B")){
					System.out.printf("|B");
				}
				if(board[i][j].equals("W")){
					System.out.printf("|W");
				}
				if(board[i][j].equals(".")){
					System.out.printf("| ");
				}
				if(j == 7){
					System.out.printf("| \n"); //cap off the end of the board
				}
			}
			System.out.printf("------------------\n");
		}
	}
	
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


//make a HasBlackWon? and a HasWhiteWon?
	private boolean hasBlackWon(){ //instead of using marker, just make individual ones for each, simpler communication
		for(int j = 0; j < 8; j++){ //check every column of 8th row
			if(gameboard[7][j].equals("B")){
				return true;
			}
		}
		return false;
	}
	private boolean hasWhiteWon(){
		for(int j = 0; j < 8; j++){
			if(gameboard[0][j].equals("W")){
				return true;
			}
		}
		return false;
	}
	
	private void printWinner() {
//customize to fit our game
		if(hasWhiteWon()) {
			System.out.println("*** " + player1.getName() + " wins, playing with the white pieces! ***");
		}
		else if(hasBlackWon()) {
			System.out.println("*** " + player2.getName() + " wins, playing with the black pieces! ***");
		}
		else {
			System.out.println("*** " + player1.getName() + " and " + player2.getName() + " tie! ***");
		}
	}
	
	public void evaluateMove(String move){ //this needs to be here to stop java from complaining about turnbasedgame.java already having an abstract evaluateMove(String)
		int m = Integer.parseInt(move.substring(0,1));
		int n = Integer.parseInt(move.substring(1,2));
		int i = Integer.parseInt(move.substring(2,3));
		int j = Integer.parseInt(move.substring(3,4));
		evaluateMove(m,n,i,j);
	}

	protected void evaluateMove(int m, int n, int i, int j) {
	    Scanner scanner = new Scanner(System.in);
 		if (!isValidPosition(i, j) || !isValidPosition(m, n)) {
       	 throw new IllegalArgumentException("Position out of bounds");
        }

		//player 1's turn White
		if (isFirstPlayerTurn()) {
			//check if piece belongs to player
        	if (!gameboard[i][j].equals("W")) {
           		throw new IllegalArgumentException("That's not your piece!");
        	}
			//check if there is pawn to capture
			if(isValidPosition(i + 1, j - 1) && gameboard[i + 1][j - 1].equals("O") || isValidPosition(i + 1, j + 1) && gameboard[i + 1][j + 1].equals("O")) {
				 System.out.println("There's a pawn you can capture! Do you want to capture? (yes/no)");
           		 String res = scanner.nextLine();
				 if(res.equals("yes")){
					if (isValidPosition(i + 1, j - 1) && gameboard[i + 1][j - 1].equals("B")) {
                		gameboard[i + 1][j - 1] = "W";
                	} else {
                    	gameboard[i + 1][j + 1] = "W";
                	}
                	gameboard[i][j] = ".";
                	return;
				}
			}
			if(n == j){
				//move forward 1 space
				if(m == i + 1 && gameboard[m][n].equals(".")) {
					gameboard[m][n] = "W";
					gameboard[i][j] = ".";
					return;
				//move forward 2 spaces from starting position	
				} else if(m == i + 2 && i == 1 && gameboard[i + 1][j].equals(".") && gameboard[m][n].equals(".")) {
					gameboard[m][n] = "W";
					gameboard[i][j] = ".";
					return;
				} else {
					throw new IllegalArgumentException("Invalid move");
				}

			} else {
				throw new IllegalArgumentException("Invalid move");
			}

 	    } else {
        // Player 2's turn Black (o=B, X=W)
       		if (!gameboard[i][j].equals("B")) {
            	throw new IllegalArgumentException("That's not your piece!");
			} 
			//check if there is pawn to capture
			if(isValidPosition(i - 1, j - 1) && gameboard[i - 1][j - 1].equals("W") || isValidPosition(i - 1, j + 1) && gameboard[i - 1][j + 1].equals("W")) {
				 System.out.println("There's a pawn you can capture! Do you want to capture? (yes/no)");
           		 String res = scanner.nextLine();
				 if(res.equals("yes")){
					if (isValidPosition(i - 1, j - 1) && gameboard[i + 1][j - 1].equals("W")) {
                		gameboard[i - 1][j - 1] = "B";
                	} else {
                    	gameboard[i - 1][j + 1] = "B";
                	}
                	gameboard[i][j] = ".";
                	return;
				}
			}
				if(n == j){
				//move forward 1 space	
				if(m == i - 1 && gameboard[m][n].equals(".")) {
					gameboard[m][n] = "B";
					gameboard[i][j] = ".";
					return;
				//move forward 2 spaces from starting position	
				} else if(m == i - 2 && i == 6 && gameboard[i - 1][j].equals(".") && gameboard[m][n].equals(".")) {
					gameboard[m][n] = "B";
					gameboard[i][j] = ".";
					return;
				} else {
					throw new IllegalArgumentException("Invalid move");
				}

			} else {
				throw new IllegalArgumentException("Invalid move");
			}

        
        }
		//scanner.close();
	}
	
	public void play() {
		System.out.println("=== Game Started ===\n");
		System.out.printf("Input 4 numbers: the first two are the row and column of the piece you want to move, and the second two are the row and column of where you want it to move. Ex: '1323'\n");
		gameboard = initGen();
		display(gameboard);		
		System.out.println();
//replace with check O & X wins
		while(!hasWhiteWon() && !hasBlackWon()) {
			System.out.println("* " + getCurrentPlayerName() + "'s Turn *");
			playOneTurn();
			System.out.println();
			display(gameboard);
			System.out.println();
		}

		printWinner();
		System.out.println();
		System.out.println("=== Game Finished ===");
	}
}
