import static org.junit.Assert.*;
import org.junit.Test;

import model.GameBoard;

public class GameBoardTest {
    @Test
    public void testInitialization() {
        GameBoard board = new GameBoard();
        assertNotNull(board);
    }
}
