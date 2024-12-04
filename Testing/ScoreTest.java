import model.ScoreManager;
import model.BestScoreHandler;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    private static final String TEST_FILE = "score.txt";
    private static final PrintStream originalErr = System.err;
    private ByteArrayOutputStream errContent;

    @BeforeEach
    void start() throws IOException {
        redirectErrorStream();
        resetScoreManager();
        clearTestFile();
    }

    @AfterEach
    void del() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
        System.setErr(originalErr); 
    }
    

    private void resetScoreManager() {
        try {
            java.lang.reflect.Field instance = ScoreManager.class.getDeclaredField("scoreManager");
            instance.setAccessible(true);
            instance.set(null, null); 
        } catch (Exception e) {
            throw new RuntimeException("Failed to reset ScoreManager ", e);
        }
    }

    private void clearTestFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write(""); 
        }
    }

    private void redirectErrorStream() {
        errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void testAddScoreUpdatesPlayerScore() {
        ScoreManager scoreManager = ScoreManager.getScoreManager();
        scoreManager.addScore(100);
        assertEquals(100, scoreManager.getPlayerScore());
    }

    @Test
    void testAddScoreUpdatesBestScore() {
        ScoreManager scoreManager = ScoreManager.getScoreManager();
        scoreManager.addScore(150);
        assertEquals(150, scoreManager.getBestScore());
    }

    
    @Test
    void testFinalizeScoreUpdatesLeaderboard() {
        ScoreManager scoreManager = ScoreManager.getScoreManager();
        scoreManager.addScore(200);
        scoreManager.finalizeScore();

        List<Integer> leaderboard = scoreManager.getLeaderboard();
        assertEquals(1, leaderboard.size());
        assertEquals(200, leaderboard.get(0));
    }

    
    @Test
    void testClearResetsPlayerScore() {
        ScoreManager scoreManager = ScoreManager.getScoreManager();
        scoreManager.addScore(250);
        scoreManager.clear();
        assertEquals(0, scoreManager.getPlayerScore());
    }

    @Test
    void testLeaderboardCapacity() {
        ScoreManager scoreManager = ScoreManager.getScoreManager();
        for (int i = 1; i <= 15; i++) {
            scoreManager.addScore(i * 10);
            scoreManager.finalizeScore();
        }

        List<Integer> leaderboard = scoreManager.getLeaderboard();
        assertEquals(10, leaderboard.size(), "Leaderboard should only keep top 10 scores");
    }

    @Test
    void testBestScoreHandlerSetAndGet() {
        BestScoreHandler.setBestScore(500);
        int bestScore = BestScoreHandler.getBestScore();
        assertEquals(500, bestScore);
    }

    @Test
    void testBestScoreHandlerReadsEmptyFile() throws IOException {
        clearTestFile(); // Ensure file is empty
        int bestScore = BestScoreHandler.getBestScore();
        assertEquals(0, bestScore);
    }

    @Test
    void testErrorHandlingForInvalidFile() {
        File invalidFile = new File("invalid_path/score.txt");
        invalidFile.getParentFile().mkdirs();
        invalidFile.delete();

        assertDoesNotThrow(() -> BestScoreHandler.setBestScore(100));
        assertDoesNotThrow(() -> BestScoreHandler.getBestScore());
    }

    @Test
    void testSaveScoreToLeaderboard() {
        ScoreManager scoreManager = ScoreManager.getScoreManager();
        scoreManager.addScore(400);
        scoreManager.finalizeScore();

        List<Integer> leaderboard = scoreManager.getLeaderboard();
        assertEquals(1, leaderboard.size());
        assertEquals(400, leaderboard.get(0));

        scoreManager.addScore(350);
        scoreManager.finalizeScore();
        leaderboard = scoreManager.getLeaderboard();
        assertEquals(2, leaderboard.size());
    }

    @Test
    void testLoadLeaderboardFileNotFound() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete(); 
        }

        ScoreManager scoreManager = ScoreManager.getScoreManager();
        List<Integer> leaderboard = scoreManager.getLeaderboard();

        assertTrue(leaderboard.isEmpty());
        assertTrue(errContent.toString().contains("Error"));
    }

    
    @Test
    void testSaveScoreToLeaderboardFileWriteError() {
        ScoreManager scoreManager = ScoreManager.getScoreManager();
        File file = new File(TEST_FILE);

        if (file.exists()) {
            file.setReadOnly();
        }

        scoreManager.addScore(200);
        scoreManager.finalizeScore();
        file.setWritable(true); 

        assertTrue(errContent.toString().contains("Error "));
    }
}

