package engine;

import java.util.Map;

import sfx.GameSound;

public class SoundLoader {
	private Map<String, GameSound> sounds;

	public SoundLoader(String string, Map<String, GameSound> snds) {
		this.sounds = snds;
	}

}
