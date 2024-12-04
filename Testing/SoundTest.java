
import org.junit.jupiter.api.Test;

import model.SoundEffect;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SoundTest {

    @Test
    void testPlaySoundWithValidFile() {
        String validSoundFile = "src/sounds/move.wav";
        File file = new File(validSoundFile);

        if (file.exists()) {
            assertDoesNotThrow(() -> SoundEffect.playSound(validSoundFile));
        } 
    }
    @Test
    void testPlaySoundWithNonExistentFile() {
        String nonExistentFile = "src/sounds/nonexistent.wav";
        assertDoesNotThrow(() -> SoundEffect.playSound(nonExistentFile));
    }
    @Test
    void testPlaySoundWithUnsupportedFile() throws IOException {
        String unsupportedFile = "src/sounds/unsupported.txt";
        File file = new File(unsupportedFile);
        if (!file.exists()) {
            file.createNewFile();
        }

        assertDoesNotThrow(() -> SoundEffect.playSound(unsupportedFile));
        file.delete();
    }
}
