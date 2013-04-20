package engine;

import gfx.GameFrame;
import gfx.Renderable;

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
	private Map<String, Renderable> renderables;
	private GameEventListener listener;
	private InputManager movementManager;
	private GameFrame frame;
	private CollisionMap collisionMap;
	private GameEngine gameEngine;
	private GraphicsEngine graphicsEngine;
	private SoundEngine soundEngine;

	public Factory() {

		this.images = new ConcurrentHashMap<String, ArrayList<BufferedImage>>();
		this.entities = new ConcurrentHashMap<String, Entity>();
		this.sounds = new ConcurrentHashMap<String, GameSound>();
		this.renderables = new ConcurrentHashMap<String, Renderable>();

		new ImageLoader("imagelist.txt", images);
		new SoundLoader("soundlist.txt", sounds);

		this.listener = new GameEventListener();
		this.frame = new GameFrame(Color.black);
		this.collisionMap = new CollisionMap(images.get("collisionWORLDMAP").get(0));

        this.gameEngine = new GameEngine(entities, renderables, images, sounds,
                listener);
		this.movementManager = new InputManager(gameEngine);
		this.movementManager.addCollisionMap(collisionMap);

		listener.setMovementManager(movementManager);
		frame.addListener(listener);

		this.soundEngine = new SoundEngine(entities, sounds);
		this.graphicsEngine = new GraphicsEngine(entities, renderables, images,
				frame);
	}

	public GameEngine createGameEngine() {
		return gameEngine;
	}

	public SoundEngine createSoundEngine() {
		return soundEngine;
	}

	public GraphicsEngine createGraphicsEngine() {
		return graphicsEngine;
	}
}
