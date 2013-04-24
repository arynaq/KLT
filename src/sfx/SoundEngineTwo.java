package sfx;

import java.util.Map;

public class SoundEngineTwo {
    private Map<String, GameSound> sounds;

    public void playMusic() {
    }

    public Map<String, GameSound> getSounds() {
        return sounds;
    }

    public void setSounds(Map<String, GameSound> sounds) {
        this.sounds = sounds;
    }
}
