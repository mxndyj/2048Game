
import model.GameBoard;


public class Main {
    public static void main(String[] args) {
        System.out.println("Debug prints");

        System.out.println("Initial Game Board (start with 2 tiles):");
        GameBoard gameBoard = new GameBoard();
        gameBoard.showTiles();

        System.out.println("Placing a random tile");
        gameBoard.placeRandomTile();
        gameBoard.showTiles();

        System.out.println("Placing another random tile");
        gameBoard.placeRandomTile();
        gameBoard.showTiles();

    }
}
