import model.GameBoard;
import model.MoveTileDown;
import model.MoveTileUp;
import model.MoveTileLeft;
import model.MoveTileRight;

public class Main {
    public static void main(String[] args) {
        System.out.println("Debug prints");

        System.out.println("\nInitial Game Board (start with 2 tiles):");
        GameBoard gameBoard = new GameBoard();
        gameBoard.showTiles();

        System.out.println("\nPlacing a random tile:");
        gameBoard.placeRandomTile();
        gameBoard.showTiles();

        //  MoveTileDown
        System.out.println("\nTesting MoveTileDown...");
        MoveTileDown moveTileDown = new MoveTileDown();
        int[] resultDown = gameBoard.move(moveTileDown);
        gameBoard.showTiles();
        printMoveResult(resultDown);

        //  MoveTileUp
        System.out.println("\nPlacing another random tile...");
        gameBoard.placeRandomTile();
        gameBoard.showTiles();

        System.out.println("\nTesting MoveTileUp...");
        MoveTileUp moveTileUp = new MoveTileUp();
        int[] resultUp = gameBoard.move(moveTileUp);
        gameBoard.showTiles();
        printMoveResult(resultUp);

        //  MoveTileLeft
        System.out.println("\nPlacing another random tile...");
        gameBoard.placeRandomTile();
        gameBoard.showTiles();

        System.out.println("\nTesting MoveTileLeft...");
        MoveTileLeft moveTileLeft = new MoveTileLeft();
        int[] resultLeft = gameBoard.move(moveTileLeft);
        gameBoard.showTiles();
        printMoveResult(resultLeft);

        //  MoveTileRight
        System.out.println("\nPlacing another random tile...");
        gameBoard.placeRandomTile();
        gameBoard.showTiles();

        System.out.println("\nTesting MoveTileRight...");
        MoveTileRight moveTileRight = new MoveTileRight();
        int[] resultRight = gameBoard.move(moveTileRight);
        gameBoard.showTiles();
        printMoveResult(resultRight);
    }

    private static void printMoveResult(int[] result) {
        if (result[0] == 1) {
            System.out.println("Move successful. Score added: " + result[1]);
        } else {
            System.out.println("Move not valid. No tiles moved.");
        }
    }
}
