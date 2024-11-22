import model.GameBoard;
import model.MoveTileDown;

public class Main {
    public static void main(String[] args) {
        System.out.println("Debug prints");

        System.out.println("Initial Game Board (start with 2 tiles):");
        GameBoard gameBoard = new GameBoard();
        gameBoard.showTiles();

        System.out.println("Placing a random tile");
        gameBoard.placeRandomTile();
        gameBoard.showTiles();

        System.out.println("Testing MoveTileDown...");
        MoveTileDown moveTileDown = new MoveTileDown();
        int[] result = gameBoard.move(moveTileDown); 
        gameBoard.showTiles();

        if (result[0] == 1) {
            System.out.println("Move successful. Score added: " + result[1]);
        } else {
            System.out.println("Move not valid. No tiles moved.");
        }

        System.out.println("Placing another random tile");
        gameBoard.placeRandomTile();
        gameBoard.showTiles();
        
        
        System.out.println("Testing MoveTileDown...");
        MoveTileDown moveTileDown1 = new MoveTileDown();
        int[] result2 = gameBoard.move(moveTileDown1); 
        gameBoard.showTiles();

        if (result2[0] == 1) {
            System.out.println("Move successful. Score added: " + result2[1]);
        } else {
            System.out.println("Move not valid. No tiles moved.");
        }
    }
}
