package view;

import controller.GameBoardController;

import java.util.Scanner;

public class TextBasedUI {
    private final GameBoardController controller;

    public TextBasedUI(GameBoardController controller) {
        this.controller = controller;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your console 2048 Game!");
        controller.displayGameBoard();

        while (!controller.isGameOver()) {
            System.out.println("Enter a move (w=UP, s=DOWN, a=LEFT, d=RIGHT)"+  "\n"+ 
        "or type 'leaderboard' to view scores, or 'end' to quit (score will count for LEADERBOARS):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("end")) {
                controller.displayFinalScore();
                return;
            } else if (input.equalsIgnoreCase("leaderboard")) {
                controller.displayLeaderboard();
            } else if (controller.handleInput(input)) {
                controller.spawnRandomTile();
                controller.displayGameBoard();
            } else {
                System.out.println("Invalid move. Try again.");
            }
            
         scanner.close();

        }
        controller.displayFinalScore();
        
    }
}

