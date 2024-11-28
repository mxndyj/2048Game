import model.GameBoard;
import model.MoveTileDown;
import model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest extends BaseGameBoardTest{

    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
    }
    
    @Test
    void testInitialSetup() {
        Tile[][] grid = getPrivateGridBoard(gameBoard);
        int nonZeroTiles = countNonZeroTiles(grid);
        assertEquals(2, nonZeroTiles);
    }

    @Test
    void testPlaceRandomTile() {
        gameBoard.placeRandomTile();
        Tile[][] grid = getPrivateGridBoard(gameBoard);
        int nonZeroTiles = countNonZeroTiles(grid);
        assertEquals(3,nonZeroTiles);
    }

    @Test
    void testFullGridNoRandomTilePlaced() {
        fillBoardWithReflection(gameBoard, 2);
        gameBoard.showTiles();
        assertFalse(gameBoard.placeRandomTile());
    }

    @Test
    void testMoveWithValidStrategy() {
        Tile[][] grid = getPrivateGridBoard(gameBoard);
        grid[3][0 ].setTileValue(2);
        grid[2][0].setTileValue(2);

        MoveTileDown moveTileDown = new MoveTileDown();
        int[] result = gameBoard.move(moveTileDown);

        assertEquals(1, result[0]);
        assertEquals(4,result[1]);
    }

    @Test
    void testMoveWithNoValidMoves() {
        setSpecificValues(gameBoard, new int[][]{
            {2, 4, 8, 16},
            {32, 64, 128, 256}, {512, 1024, 2048, 4096},
            {8192, 16384, 32768, 65536}
        });
  
        MoveTileDown moveTileDown = new MoveTileDown();
        int[] result = gameBoard.move(moveTileDown);
        assertEquals(0, result[0]);
        assertEquals(0, result[1]);
    }

    
    @Test
    void testGameOverCondition() {
        setSpecificValues(gameBoard, new int[][]{
            {2, 4, 8, 16}, {32, 64, 128, 256}, {512, 1024, 2048, 4096},
            {8192, 16384, 32768, 65536}
        });
        assertTrue(gameBoard.isGameOver());
    }

    @Test
    void testGameNotOverCondition() {
        setSpecificValues(gameBoard, new int[][]{
            {2, 2, 4, 4},  {8, 16, 32, 64}, {128, 256, 512, 1024},
            {2048, 4096, 8192, 16384}
        });

        assertFalse(gameBoard.isGameOver());
    }

    @Test
    void testShowTiles() {
        assertDoesNotThrow(() -> gameBoard.showTiles(), "showTiles() should not throw any exceptions.");
    }
    
    

   
    @Test
    void testGetModelGridBoard() {
    	
        Tile[][] originalBoard = getPrivateGridBoard(gameBoard);

        Tile[][] copiedBoard = gameBoard.getModelGridBoard();

        int SIZE = gameBoard.getSize();
        
        for (int i =0; i < SIZE; i++)
        {
            for (int j = 0; j <SIZE; j++) {
                assertEquals(
                    originalBoard[i][j].getTileValue(),
                    copiedBoard[i][j].getTileValue()
                );
            }
        }
    }

   
}

