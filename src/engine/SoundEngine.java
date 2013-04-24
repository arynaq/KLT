package engine;

import java.util.Map;

import javax.sound.sampled.Clip;

public class SoundEngine {
    private Map<String, Clip> sounds;
    private boolean playing;
    private String currentMusic = "";
    private int currentFrame = 0;
    private boolean audioSupported;

    public SoundEngine(Map<String, Clip> sounds) {

        this.sounds = sounds;
        this.audioSupported = GameState.getInstance().isAudioSupported();
    }

    public void start() {
        // TODO Auto-generated method stub

    }

    public void playSFX(String soundName) {
        if (!audioSupported)
            return;
        sounds.get(soundName).setFramePosition(0);
        sounds.get(soundName).start();
    }

    public void playMusic() {
        if (!audioSupported || playing)
            return;
        sounds.get("mainMusic").setFramePosition(currentFrame);
        sounds.get("mainMusic").start();
        playing = true;
    }

    public void playerGameOver() {
        if (!audioSupported)
            return;
        playing = false;
    }

    public void pauseGame() {
        if (!audioSupported)
            return;
        currentFrame = sounds.get("mainMusic").getFramePosition() - 100;
        sounds.get("mainMusic").stop();
        playing = false;

    }

    public void playLevelUp() {
        if (!audioSupported)
            return;
        sounds.get("levelUp").setFramePosition(0);
        sounds.get("levelUp").start();
    }

    public void playPotion() {
        if (!audioSupported)
            return;
        sounds.get("potion").setFramePosition(0);
        sounds.get("potion").start();
    }

    public void playEnemyAttack() {
        if (!audioSupported)
            return;
        sounds.get("attack").setFramePosition(0);
        sounds.get("attack").start();
    }

    public void playPlayerAttack() {
        if (!audioSupported)
            return;
        sounds.get("attack").setFramePosition(0);
        sounds.get("attack").start();

    }

    public void playEnemyDead() {

    }

}
