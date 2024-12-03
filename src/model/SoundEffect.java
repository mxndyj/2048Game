package model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
	
	public static void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
	
	public static void loopMusic(String soundFile) {
		try {
			File file = new File(soundFile);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);		
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		}catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
	}
}
}
