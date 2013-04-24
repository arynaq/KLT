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
        
        if(currentMusic == "splashTheme"){
            playing = false;
            stop();
        }
        playMusic("skyrim");
    }
    public void stop(){
        sounds.get(currentMusic).stop();        
    }

    public void playSFX(String soundName) {
        if (!audioSupported)
            return;
        sounds.get(soundName).setFramePosition(0);
        sounds.get(soundName).start();
    }

    public void playMusic(String songName) {
        if (!audioSupported || playing)
            return;
        sounds.get(songName).setFramePosition(currentFrame);
        sounds.get(songName).start();
        currentMusic = songName;
        playing = true;
        currentFrame = 0;
    }

    public void playerGameOver() {
       if(currentMusic == "skyrim"){
           playing = false;
           stop();
           playMusic("gameOverTheme");
       }
    }
    public void splash(){
        playMusic("splashTheme");
    }

    public void pauseGame() {
        if (!audioSupported)
            return;
        currentFrame = sounds.get(currentMusic).getFramePosition() - 100;
        sounds.get(currentMusic).stop();
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
