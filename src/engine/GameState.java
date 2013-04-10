package engine;

import java.awt.Dimension;

import worldmap.GameMap;
import worldmap.WorldMap;

public class GameState {

	private GameMap currentMap;
	private WorldMap worldMap;
	// private Map<String, Entity> playerMapEntities;
	// private Map<String, Entity> worldMapEntities;
	
	private static final Dimension DIMENSION = new Dimension(1500, 1000);
	private static final int GAMEFPS = 60;
	private static final GameState instance = new GameState();
	private GameCondition state;

	private GameState() {
		this.state = GameCondition.RUNNING;
		// this.playerMapEntities = new HashMap<String, Entity>();
		// this.worldMapEntities = new HashMap<String, Entity>();
	}

	public static GameState getInstance() {
		return instance;
	}

	public int getFPS() {
		return GAMEFPS;
	}

	public double getFrameWidth() {
		return DIMENSION.getWidth();
	}

	public double getFrameHeight() {
		return DIMENSION.getHeight();
	}

//	public Map<String, Entity> getPlayerMapEntities() {
//		return playerMapEntities;
//	}
//
//	public Map<String, Entity> getWorldMapEntities() {
//		return worldMapEntities;
	// }

	// public static final int[] GAMEDIMENSION = { 800, 592 };
	// public static final int xCenter = GAMEDIMENSION[0] / 2;
	// public static final int yCenter = GAMEDIMENSION[1] / 2;
	// public static final String GAMETITLE = "KLT - The RPG";
	// public static MapType CURRENTMAP = MapType.OUT1;
	// public static GameCondition gameState = GameCondition.RUNNING;
	//
	// private static Map<String, ArrayList<BufferedImage>> IMAGES;
	// private static Map<String, Clip> SOUNDS;
	//
	// static {
	// IMAGES = new HashMap<String, ArrayList<BufferedImage>>();
	// loadSplashStuff();
	// Thread imageLoaderThread = new Thread() {
	// public void run() {
	// IMAGES = new HashMap<String, ArrayList<BufferedImage>>();
	// new ImageLoader(IMAGES);
	// }
	// };
	// Thread soundLoaderThread = new Thread() {
	// public void run() {
	// SOUNDS = new HashMap<String, Clip>();
	// }
	// };
	//
	// imageLoaderThread.start();
	// soundLoaderThread.start();
	// }
	//
	// public BufferedImage getPortrait(String key) {
	// if (key.contains("PORTRAIT")) {
	//
	// }
	// return null;
	// }
	//
	// public BufferedImage getSpriteImage(String key) {
	// if (key.contains("SPRITE")) {
	//
	// }
	// return null;
	// }
	//
	// public BufferedImage getCollisionMap(String key) {
	// if (key.contains("COLLISION")) {
	//
	// }
	//
	// return null;
	// }
	//
	// public BufferedImage getMap(String key) {
	// if (key.contains("MAP")) {
	//
	// }
	// return null;
	// }
	//
	// public ArrayList<BufferedImage> getAnimatedSpriteImage(String key) {
	// if (key.contains("ANIMATED")) {
	//
	// }
	// return null;
	// }
	//
	// public void addAnimatedSprite(String key, ArrayList<BufferedImage>
	// frames) {
	//
	// }
	//
	// public void addSprite(String key, BufferedImage sprite) {
	// ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();
	// list.add(sprite);
	// }
	//
	// public void addPortrait(String key, BufferedImage portrait) {
	// }
	//
	// public void addCollisionMap(String key, BufferedImage colMap) {
	// }
	//
	// public void addMap(String key, BufferedImage map) {
	// }
	//
	// private static void loadSplashStuff() {
	// }
	//
	//
	// public static GameState getInstance() {
	// return instance;
	// }
	//



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

}
