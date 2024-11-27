
import model.GameBoard;
import model.Tile;
import model.MoveTileUp;
import model.MoveTileDown;
import model.MoveTileLeft;
import model.MoveTileRight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTileTest extends BaseGameBoardTest {

    private GameBoard gameBoard;
    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
    }

    @Test
    void testMoveTileUp() {
        setSpecificValues(gameBoard, new int[][]{
        	
            {0, 2, 0, 2},
            {0, 2, 0, 0},
           {0, 0, 0, 0},
            {0, 0, 0, 0}
            
        });

        MoveTileUp moveTileUp = new MoveTileUp();
        gameBoard.move(moveTileUp);
        
        Tile[][] grid = getPrivateGridBoard(gameBoard);
        assertEquals(4, grid[0][1].getTileValue());
    }

    
    
    @Test
    void testMoveTileDown() {
    	
        setSpecificValues(gameBoard, new int[][]{
        	{0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 2, 0, 2},
            {0, 2, 0, 0}
        });

        MoveTileDown moveTileDown = new MoveTileDown();
        gameBoard.move(moveTileDown);
        Tile[][] grid = getPrivateGridBoard(gameBoard);
        assertEquals(4, grid[3][1].getTileValue());
    }

    @Test
    void testMoveTileLeft() {
        setSpecificValues(gameBoard, new int[][]{
            {0, 0, 0, 0},
            {2, 0, 2, 0},
            {0, 0, 0, 0},
           {0, 0, 0, 0}
        });

        MoveTileLeft moveTileLeft =new MoveTileLeft();
        gameBoard.move(moveTileLeft);

       
        Tile[][] grid = getPrivateGridBoard(gameBoard);
        assertEquals(4, grid[1][0].getTileValue());
    }

    @Test
    void testMoveTileRight() {
        setSpecificValues(gameBoard, new int[][]{
            {0, 0, 0, 0},
            {0, 2, 0, 2},
            
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        });

        MoveTileRight moveTileRight = new MoveTileRight();
        gameBoard.move(moveTileRight);

      
        Tile[][] grid = getPrivateGridBoard(gameBoard);
        assertEquals(4, grid[1][3].getTileValue());
    }

    @Test
    void testNoValidMoves() {
        setSpecificValues(gameBoard, new int[][]{
            {2, 4, 8, 16},
            {14, 3, 4, 5},
        {13, 12, 6, 9},
            {11, 7, 8,10}
        });

        MoveTileUp moveTileUp = new MoveTileUp();
        int[] result = gameBoard.move(moveTileUp);

        assertEquals(0,result[0]);
       
    }
    @Test
    void testEmptyGridMove() {
        setSpecificValues(gameBoard, new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        });

        MoveTileLeft moveTileLeft = new MoveTileLeft();
        int[] result2 = gameBoard.move(moveTileLeft);
        assertEquals(0, result2[0]);
    }
}
