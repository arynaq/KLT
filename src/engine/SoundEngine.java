package engine;

import java.util.Map;

import javax.sound.sampled.Clip;

public class SoundEngine {
	private Map<String, Entity> entities;
	private Map<String, Clip> sounds;
	private boolean playing;
	private String currentMusic = "";
	private int currentFrame = 0;

	public SoundEngine(Map<String, Entity> entities,
 Map<String, Clip> sounds) {

		this.sounds = sounds;
	}

	public void start() {
		// TODO Auto-generated method stub

	}

	public void playSFX(String soundName) {
		sounds.get(soundName).setFramePosition(0);
		sounds.get(soundName).start();
	}

	public void playMusic(String soundName) {
		if (playing && soundName.equals(currentMusic)) {
			return;
		}
		currentMusic = soundName;
		sounds.get(soundName).setFramePosition(currentFrame);
		sounds.get(soundName).start();
		playing = true;
	}

	public void playerGameOver() {
		sounds.get(currentMusic).stop();

	}

	public void pauseGame() {
		currentFrame = sounds.get(currentMusic).getFramePosition() - 100;
		sounds.get(currentMusic).stop();
		playing = false;

	}

}
