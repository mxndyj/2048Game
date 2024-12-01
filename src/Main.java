import controller.GameBoardController;
import model.GameBoard;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select mode: GUI or TEXT?");
        String mode = scanner.nextLine().toUpperCase().strip();

        GameBoard model = new GameBoard();
        GameBoardController controller = new GameBoardController(model);

        controller.loadGame(mode);
        
        scanner.close();
    }
}
