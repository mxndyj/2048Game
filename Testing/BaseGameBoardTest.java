
import model.GameBoard;
import model.Tile;

import java.lang.reflect.Field;

public abstract class BaseGameBoardTest {
// utility methods
    protected Tile[][] getPrivateGridBoard(GameBoard gameBoard) {
        try {
            Field gridField = GameBoard.class.getDeclaredField("gridBoard");
            gridField.setAccessible(true);
            return (Tile[][]) gridField.get(gameBoard);
        }
        catch (NoSuchFieldException |IllegalAccessException f) {
            throw new RuntimeException("Failed to access gridBoard", f);
        }
    }

    protected void setSpecificValues(GameBoard gameBoard, int[][] values) {
        Tile[][] grid = getPrivateGridBoard(gameBoard);
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                grid[i][j].setTileValue(values[i][j]);
            }
        }
    }

    protected int countNonZeroTiles(Tile[][] grid) {
        int count = 0;
        for (Tile[] row :grid) {
            for (Tile tile : row) {
                if (!tile.isEmpty()) {
                    count++;
                }
            }
        }
        return count;
    }

    protected void fillBoardWithReflection(GameBoard gameBoard, int value) {
        Tile[][] grid = 
        		getPrivateGridBoard(gameBoard);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setTileValue(value);
            }
        }
    }
}
