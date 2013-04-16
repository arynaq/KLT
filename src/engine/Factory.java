package engine;

import gfx.GameFrame;
import gfx.ScreenManager;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import sfx.GameSound;
import worldmap.CollisionMap;

public class Factory {

	

	private Map<String, Entity> entities;
	private Map<String, GameSound> sounds;
	private Map<String, ArrayList<BufferedImage>> images;
	private GameEventListener listener;
	private MovementManager movementManager;
	private ScreenManager screenManager;
	private GameFrame frame;
	private ImageLoader Iloader;
	private SoundLoader Sloader;
	private ArrayList<GameInput> moveStack;

	public Factory() {

		this.images = new ConcurrentHashMap<String, ArrayList<BufferedImage>>();
		this.entities = new ConcurrentHashMap<String, Entity>();
		this.sounds = new ConcurrentHashMap<String, GameSound>();

		this.Iloader = new ImageLoader("imagelist.txt", images);
		this.Sloader = new SoundLoader("soundlist.txt", sounds);
		this.listener = new GameEventListener();
		this.frame = new GameFrame(Color.black);
		this.movementManager = new MovementManager(entities);
		this.movementManager.addCollisionMap(new CollisionMap(images.get(
				"collisionWORLDMAP").get(0)));
	}

	public GameEngine createGameEngine() {
		return new GameEngine(entities, images, sounds);
	}

	public SoundEngine createSoundEngine() {
		return new SoundEngine(entities, sounds);
	}

	public GraphicsEngine createGraphicsEngine() {
		listener.setMovementManager(movementManager);
		listener.setScreenManager(screenManager);
		frame.addListener(listener);
		return new GraphicsEngine(entities, images, frame);
	}
}
