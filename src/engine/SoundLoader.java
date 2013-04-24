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

    // public void loadSounds(String soundlistFile) {
    // Scanner readFile = null;
    // InputStream is = getClass().getResourceAsStream(soundlistFile);
    // readFile = new Scanner(is);
    //
    // while (readFile.hasNextLine()) {
    // String line = readFile.nextLine();
    // try {
    // AudioInputStream audio = AudioSystem
    // .getAudioInputStream(SoundLoader.class
    // .getResourceAsStream("/sounds/" + line));
    // Clip clip = AudioSystem.getClip();
    // clip.open(audio);
    // sounds.put(line, clip);
    // } catch (UnsupportedAudioFileException uae) {
    // System.out.println(uae);
    // } catch (IOException ioe) {
    // System.out.println(ioe);
    // } catch (LineUnavailableException lua) {
    // System.out.println(lua);
    // }
    // }
    // readFile.close();
    //
    // }
	public void loadSounds(String soundlistFile) {
        Scanner readFile = null;
        InputStream is = getClass().getResourceAsStream(soundlistFile);
        readFile = new Scanner(is);

        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            if (line.startsWith("#"))
                continue;
            String[] args = line.split("\\s+");
            try {
                System.out.println(args[1]);
                AudioInputStream audio = AudioSystem
                        .getAudioInputStream(SoundLoader.class
                                .getResourceAsStream("/sounds/" + args[1]));
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                sounds.put(args[0], clip);
            } catch (UnsupportedAudioFileException uae) {
                GameState.getInstance().setAudioSupported(false);
                System.out.println(uae);
            } catch (IOException ioe) {
                GameState.getInstance().setAudioSupported(false);
                System.out.println(ioe);
            } catch (LineUnavailableException lua) {
                GameState.getInstance().setAudioSupported(false);
                System.out.println(lua);
            }
        }
        readFile.close();

    }
}