import java.util.Scanner;

public class TextInputHumanPlayer extends Player {

	private Scanner scanner = new Scanner(System.in);

	public TextInputHumanPlayer(String name) {
		super(name);
	}

	public String makeOneMove() {
		System.out.println("Waiting for user input from " + getName() + ":");
		return scanner.nextLine();
	}

}
