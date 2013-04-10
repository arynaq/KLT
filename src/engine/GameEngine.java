package engine;

import gfx.GameImage;
import gfx.Sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import sfx.GameSound;
import characters.Player;


public class GameEngine {
	private Map<String, Entity> entities;
	private Map<String, ArrayList<BufferedImage>> images;
	private Map<String, GameSound> sounds;

	public GameEngine(Map<String, Entity> entities,
			Map<String, ArrayList<BufferedImage>> images,
			Map<String, GameSound> sounds) {

		this.entities = entities;
		this.images = images;
		this.sounds = sounds;

	}

	public void start() {
		initEntities();

	}

	private void initEntities() {
		Player player;
		Sprite sprite;
		sprite = new Sprite(new GameImage(images.get("playerSPRITE")));
		player = new Player(sprite);
		entities.put("player", player);
	}

	public void update() {
		// TODO Auto-generated method stub

	}



}
