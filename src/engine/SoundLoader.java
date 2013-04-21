package engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundLoader {
	private Map<String, Clip> sounds;

	public SoundLoader(String soundlistName, Map<String, Clip> sounds) {
		this.sounds = sounds;
		loadSounds(soundlistName);
	}

	public void loadSounds(String soundlistFile) {
		Scanner readFile = null;
		InputStream is = getClass().getResourceAsStream(soundlistFile);
        System.out.println(soundlistFile);
		readFile = new Scanner(is);

		while (readFile.hasNextLine()) {
			String line = readFile.nextLine();
			try {
				AudioInputStream audio = AudioSystem
						.getAudioInputStream(SoundLoader.class
								.getResourceAsStream("/sounds/" + line));
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				sounds.put(line, clip);
			} catch (UnsupportedAudioFileException uae) {
				System.out.println(uae);
			} catch (IOException ioe) {
				System.out.println(ioe);
			} catch (LineUnavailableException lua) {
				System.out.println(lua);
			}
		}
		readFile.close();

	}
}