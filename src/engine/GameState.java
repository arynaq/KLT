package engine;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.Clip;

import worldmap.MapType;

public class GameState {

	public static final int[] GAMEDIMENSION = { 800, 592 };
	public static final int xCenter = GAMEDIMENSION[0] / 2;
	public static final int yCenter = GAMEDIMENSION[1] / 2;
	public static final String GAMETITLE = "KLT - The RPG";
	public static final int GAMEFPS = 60;
	public static MapType CURRENTMAP = MapType.OUT1;
	public static GameCondition gameState = GameCondition.RUNNING;
	
	private static Map<String, ArrayList<BufferedImage>> IMAGES;
	private static Map<String, Clip> SOUNDS;
	
	static {
		IMAGES = new HashMap<String, ArrayList<BufferedImage>>();
		loadSplashStuff();
		Thread imageLoaderThread = new Thread() {
			public void run() {
				IMAGES = new HashMap<String, ArrayList<BufferedImage>>();
				new ImageLoader(IMAGES);
			}
		};
		Thread soundLoaderThread = new Thread() {
			public void run() {
				SOUNDS = new HashMap<String, Clip>();
			}
		};

		imageLoaderThread.start();
		soundLoaderThread.start();
	}

	public BufferedImage getPortrait(String key) {
		if (key.contains("PORTRAIT")) {
			
		}
		return null;
	}

	public BufferedImage getSpriteImage(String key) {
		if (key.contains("SPRITE")) {

		}
		return null;
	}

	public BufferedImage getCollisionMap(String key) {
		if (key.contains("COLLISION")) {

		}

		return null;
	}

	public BufferedImage getMap(String key) {
		if (key.contains("MAP")) {

		}
		return null;
	}

	public ArrayList<BufferedImage> getAnimatedSpriteImage(String key) {
		if (key.contains("ANIMATED")) {

		}
		return null;
	}
	
	public void addAnimatedSprite(String key, ArrayList<BufferedImage> frames) {
	
	}
	
	public void addSprite(String key, BufferedImage sprite) {
		ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();
		list.add(sprite);
		// Add to hashmap
	}
	
	public void addPortrait(String key, BufferedImage portrait) {
		// Add portrait to hashamap
	}
	
	public void addCollisionMap(String key, BufferedImage colMap) {

	}

	public void addMap(String key, BufferedImage map) {

	}

	private static void loadSplashStuff() {
		// The few resources we need asap before the game splash, load these
		// first, let the threads handle the rest.
	}

	private static void loadSounds() {

	}
}
