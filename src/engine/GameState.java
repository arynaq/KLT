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
	 * Determines the highest rate at which the game will run.
	 */
    public static final int GAMEFPS = 60;
    /*
     * Determine how fast the player animation walks. 1 is very fast, 50 is very
     * slow.
     */
    public static final int PLAYERSTEPS = 20;
	/*
	 * Determines the state of the game.
	 */
	private GameCondition state;

	private GameState() {
        this.state = GameCondition.SPLASH;
		// this.playerMapEntities = new HashMap<String, Entity>();
		// this.worldMapEntities = new HashMap<String, Entity>();
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

}
