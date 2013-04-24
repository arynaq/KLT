package engine;

import gfx.GameFrame;
import gfx.Renderable;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sound.sampled.Clip;

import worldmap.CollisionMap;

public class Factory {

	

	private Map<String, Entity> entities;
	private Map<String, Clip> sounds;
	private Map<String, ArrayList<BufferedImage>> images;
	private Map<String, Renderable> renderables;
	private GameEventListener listener;
	private InputManager movementManager;
	private GameFrame frame;
	private CollisionMap collisionMap;
	private GameEngine gameEngine;
	private GraphicsEngine graphicsEngine;
	private SoundEngine soundEngine;

    /**
     * Use the concurrent hashmaps if you are multithreading the game. They have
     * a larger memory/cpu overhead.
     */

	public Factory() {

        // this.images = new ConcurrentHashMap<String,
        // ArrayList<BufferedImage>>();
        this.images = new HashMap<String, ArrayList<BufferedImage>>();
        this.entities = new ConcurrentHashMap<String, Entity>();
        // this.entities = new HashMap<String, Entity>();
        // this.sounds = new ConcurrentHashMap<String, Clip>();
        this.sounds = new HashMap<String, Clip>();
        this.renderables = new ConcurrentHashMap<String, Renderable>();
        // this.renderables = new HashMap<String, Renderable>();

		new ImageLoader("imagelist.txt", images);
        new SoundLoader("soundList2.txt", sounds);
        new FontLoader();

		this.listener = new GameEventListener();
		this.frame = new GameFrame(Color.black);
		this.collisionMap = new CollisionMap(images.get("collisionWORLDMAP").get(0));

        this.soundEngine = new SoundEngine(sounds);
        this.gameEngine = new GameEngine(entities, renderables, images,
                soundEngine,
                listener);
		this.movementManager = new InputManager(gameEngine);
		this.movementManager.addCollisionMap(collisionMap);

		listener.setMovementManager(movementManager);
		frame.addListener(listener);
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
