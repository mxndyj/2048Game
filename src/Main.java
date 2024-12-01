
import controller.GameBoardController;
import model.GameBoard;
import view.GameBoardGUI;
import view.WelcomeScreen;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select mode: GUI or TEXT?");
        String mode = scanner.nextLine().toUpperCase();

        GameBoard model = new GameBoard();
        GameBoardGUI view = new GameBoardGUI();
        WelcomeScreen welcomeScreen = new WelcomeScreen();

        GameBoardController controller = new GameBoardController(model, view, welcomeScreen, mode);

        if (mode.equals("GUI")) {
            controller.loadGame();
        } else if (mode.equals("TEXT")) {
            controller.loadGame();
        } else {
            System.out.println("Invalid mode selected. Exiting.");
        }
        scanner.close();
    }
}
