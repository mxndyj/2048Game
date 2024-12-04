import model.GameBoard;
import model.ScoreManager;
import controller.GameBoardController;
import view.GameBoardGUI;
import view.WelcomeScreen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import javax.swing.JButton;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest extends BaseGameBoardTest {

    private GameBoard gameBoard;
    private GameBoardController controller;
    private GameBoardGUI view;
    private WelcomeScreen welcomeScreen;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
        view = new GameBoardGUI();
        welcomeScreen = new WelcomeScreen();
        controller = new GameBoardController(gameBoard, view, welcomeScreen, "TEXT");

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testHandleKeyPress() throws Exception {
        Method handleKeyPressMethod = GameBoardController.class.getDeclaredMethod("handleKeyPress", KeyEvent.class);
        handleKeyPressMethod.setAccessible(true);

        KeyEvent keyEventUp = new KeyEvent(new JButton(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'U');
        assertDoesNotThrow(() -> handleKeyPressMethod.invoke(controller, keyEventUp));

        KeyEvent keyEventDown = new KeyEvent(new JButton(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'D');
        assertDoesNotThrow(() -> handleKeyPressMethod.invoke(controller, keyEventDown));

        KeyEvent keyEventLeft = new KeyEvent(new JButton(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L');
        assertDoesNotThrow(() -> handleKeyPressMethod.invoke(controller, keyEventLeft));

        KeyEvent keyEventRight = new KeyEvent(new JButton(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        assertDoesNotThrow(() -> handleKeyPressMethod.invoke(controller, keyEventRight));
    }

    @Test
    void testHandleKeyPressInvalidKey() throws Exception {
        KeyEvent keyEventInvalid = new KeyEvent(new JButton(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_X, 'X');
        Method handleKeyPressMethod = GameBoardController.class.getDeclaredMethod("handleKeyPress", KeyEvent.class);
        handleKeyPressMethod.setAccessible(true);

        assertDoesNotThrow(() -> handleKeyPressMethod.invoke(controller, keyEventInvalid));
    }

    @Test
    void testStartTextBasedGameEndInput() {
        String input = "end\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        controller.loadGame();
        String output = outputStream.toString();

        assertTrue(output.contains("Welcome to 2048!"));
        assertTrue(output.contains("Game Over!"));
    }

    @Test
    void testStartTextBasedGameLeaderboard() {
        ScoreManager.getScoreManager().addScore(100);
        ScoreManager.getScoreManager().finalizeScore();

        String input = "leaderboard\nend\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        controller.loadGame();
        String output = outputStream.toString();

        assertTrue(output.contains("Top 10 Scores"));
    }

    @Test
    void testStartTextBasedGameValidMove() {
        controller.spawnRandomTile();

        String input = "w\nend\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        controller.loadGame();
        String output = outputStream.toString();

        assertTrue(output.contains("Current Score:"));
        assertTrue(output.contains("Game Over!"));
    }

    @Test
    void testStartTextBasedGameInvalidMove() {
        String input = "x\nend\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        controller.loadGame();
        String output = outputStream.toString();

        assertTrue(output.contains("Invalid move"));
        assertTrue(output.contains("Game Over!"));
    }

    @Test
    void testDisplayGameBoard() {
        setSpecificValues(gameBoard, new int[][]{
                {2, 0, 0, 0},
                {0, 4, 0, 0},
                {0, 0, 8, 0},
              {0, 0, 0, 16}
        });

        controller.displayGameBoard();
        String output = outputStream.toString();

        assertTrue(output.contains("2\t.\t.\t."));
        assertTrue(output.contains(".\t4\t.\t."));
        assertTrue(output.contains(".\t.\t8\t."));
        assertTrue(output.contains(".\t.\t.\t16"));
    }

    @Test
    void testDisplayFinalScore() {
        ScoreManager scoreManager = ScoreManager.getScoreManager();
        scoreManager.addScore(100);
        scoreManager.finalizeScore();

        controller.displayFinalScore();
        String output = outputStream.toString();

        assertTrue(output.contains("Game Over!"));
    }

    
    @Test
    void testLoadGameGUIMode() {
        controller = new GameBoardController(gameBoard, view, welcomeScreen, "GUI");
        assertDoesNotThrow(() -> controller.loadGame());
    }

    @Test
    void testEndGameGUIMode() {
        assertDoesNotThrow(() -> controller.endGame(500, 1000, "Game Over"));
    }
    
    
    
    @Test
    void testEndGameGUI() throws Exception {
        Method endGameMethod = GameBoardController.class.getDeclaredMethod("endGame", int.class, int.class, String.class);
        endGameMethod.setAccessible(true);

        assertDoesNotThrow(() -> endGameMethod.invoke(controller, 500, 1000, "Game Over"));
    }

    @Test
    void testDisplayLeaderboardGUI() throws Exception {
        ScoreManager scoreManager = ScoreManager.getScoreManager();
        scoreManager.addScore(100);
        scoreManager.finalizeScore();

        Method displayLeaderboardGUIMethod = GameBoardController.class.getDeclaredMethod("displayLeaderboardGUI");
        displayLeaderboardGUIMethod.setAccessible(true);

        assertDoesNotThrow(() -> displayLeaderboardGUIMethod.invoke(controller));
    }
    
    
    @Test
    void testEndGameGUIFlow() {
        controller = new GameBoardController(gameBoard, view, welcomeScreen, "GUI");
        
        assertDoesNotThrow(() -> controller.endGame(500, 1000, "Test "));

    }
    
    @Test
    void testHandleInputAllDirections() {
        setSpecificValues(gameBoard, new int[][]{
            {2, 0, 2, 0},
           {0, 2, 0, 2},
            {2, 0, 2, 0},
            {0, 2, 0, 2}
        });

        assertTrue(controller.handleInput("w"));
        assertTrue(controller.handleInput("s"));

        assertTrue(controller.handleInput("a"));

        assertTrue(controller.handleInput("d"));
    }
    
    
  




}

