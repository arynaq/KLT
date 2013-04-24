package engine;

import java.io.BufferedInputStream;
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
        System.out.println("Done loading sounds from file.");
    }

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
                InputStream in = getClass().getResourceAsStream(
                        "/sounds/" + args[1]);
                BufferedInputStream bin = new BufferedInputStream(in, 1024 * 8);
                AudioInputStream audio = AudioSystem
                        .getAudioInputStream(bin);
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