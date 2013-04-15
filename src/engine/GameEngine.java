package engine;

import gfx.Animated;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import sfx.GameSound;
import worldmap.WorldMap;
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
		Animated sprite = new Animated(images.get("indianaANIMATED"), 4, 4, 200);
		player = new Player(sprite);
		entities.put("player", player);

		WorldMap worldMap = new WorldMap(images.get("worldWORLDMAP"));
		GameState.getInstance().setWorldMap(worldMap);
		GameState.getInstance().setCurrentMap(worldMap.getGameMap());

	}

	public void update() {
		// Manager player, where has he gone? Should he change map? For now the
		// player is stored somewhere else, we can change that.
	}



}
