package engine;

import java.awt.Dimension;

import worldmap.GameMap;
import worldmap.WorldMap;

public class GameState {

	/*
	 * Holds the current map seen on the screen
	 */
	private GameMap currentMap;
	/*
	 * Holds the worldmap
	 */
	private WorldMap worldMap;
	/*
	 * Determines the screen dimensions
	 */
	public static final Dimension DIMENSION = new Dimension(512, 512);
	/*
	 * Determines how big the world is.
	 */
	public static final Dimension WORLDBOUNDS = new Dimension(
			4 * DIMENSION.width - 1, 4 * DIMENSION.height - 1);
	/*
	 * Singleton for this class.
	 */
	private static final GameState instance = new GameState();
    /*
     * Determines the highest rate at which the game will run. This is closely
     * related to movement (we only read input fps times per second) so movement
     * will behave unexpectedly over 60 fps.
     */
    public static int GAMEFPS = 60;
    /*
     * Determine how fast the player animation walks. 1 is very fast, 50 is very
     * slow.
     */
    public static final int PLAYERSTEPS = 10;
	/*
	 * Determines the state of the game.
	 */
	private GameCondition state;

    /*
     * Is audio supported on this hardware? Decided by soundloader.
     */
    private boolean audioSupported = true;

    /**
     * Should we enable boolean testing? This is set by the arguments to the the
     * main in Game.java
     */
    private boolean enableVisualTesting;

	private GameState() {
        this.state = GameCondition.SPLASH;
        System.out.println("GameState set.");
	}

	public static GameState getInstance() {
		return instance;
	}

	public int getFPS() {
		return GAMEFPS;
	}

	public int getFrameWidth() {
		return (int) DIMENSION.getWidth();
	}

	public int getFrameHeight() {
		return (int) DIMENSION.getHeight();
	}

	public GameMap getCurrentMap() {
		return currentMap;
	}


	public WorldMap getWorldMap() {
		return worldMap;
	}

	public void setWorldMap(WorldMap worldMap) {
		this.worldMap = worldMap;
	}

	public void setCurrentMap(GameMap currentMap) {
		this.currentMap = currentMap;
	}

	public GameCondition getState() {
		return state;
	}

	public void init() {
	}

	public void setState(GameCondition condition) {
		this.state = condition;
	}

    public boolean isInCurrentMap(Entity e) {
        if (getWorldMap().getGameMap().getBounds().contains(e.getX(), e.getY())){
            return true;
        }
		return false;
	}

    public boolean isAudioSupported() {
        return audioSupported;
    }

    public void setAudioSupported(boolean audioSupported) {
        this.audioSupported = audioSupported;
    }

    public boolean isEnableVisualTesting() {
        return enableVisualTesting;
    }

    public void setEnableVisualTesting(boolean enableVisualTesting) {
        this.enableVisualTesting = enableVisualTesting;
    }

    public static void setFPS(int i) {
        System.out.println("FPS was set from,to: " + GAMEFPS + "," + i);
        GAMEFPS = i;
    }

}
