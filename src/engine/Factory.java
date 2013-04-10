package engine;

import gfx.GameFrame;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import sfx.GameSound;

public class Factory {

	

	private Map<String, Entity> entities;
	private Map<String, GameSound> sounds;
	private Map<String, ArrayList<BufferedImage>> images;
	private GameEventListener listener;
	private GameFrame frame;
	private ImageLoader Iloader;
	private SoundLoader Sloader;

	public Factory() {

		this.images = new ConcurrentHashMap<String, ArrayList<BufferedImage>>();
		this.entities = new ConcurrentHashMap<String, Entity>();
		this.sounds = new ConcurrentHashMap<String, GameSound>();

		this.Iloader = new ImageLoader("imagelist.txt", images);
		this.Sloader = new SoundLoader("soundlist.txt", sounds);
		this.listener = new GameEventListener();
		this.frame = new GameFrame(Color.black);
	}

	public GameEngine createGameEngine() {
		return new GameEngine(entities, images, sounds);
	}

	public SoundEngine createSoundEngine() {
		return new SoundEngine(entities, sounds);
	}

	public GraphicsEngine createGraphicsEngine() {
		frame.addListener(listener);
		return new GraphicsEngine(entities, images, frame);
	}
}
