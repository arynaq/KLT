package engine;

import gfx.AttackMoveAnimated;
import gfx.SpriteSheet;

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
	private Player player;
	private WorldMap worldMap;

	private int oldPlayerX, oldPlayerY;

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
		AttackMoveAnimated sprite = new AttackMoveAnimated(new SpriteSheet(
				images.get("bahamutANIMATED"), 4, 4));
		player = new Player(sprite);
		oldPlayerX = player.getX();
		oldPlayerY = player.getY();
		entities.put("player", player);


		worldMap = new WorldMap(images.get("worldWORLDMAP"));
		GameState.getInstance().setWorldMap(worldMap);
		GameState.getInstance().setCurrentMap(worldMap.getGameMap());

	}

	public void update() {
		updatePlayer();
		updateMap();
	}

	private void updateMap() {
		int x = player.getX();
		int y = player.getY();
		worldMap.updateGameMap(x, y);
	}

	private void updatePlayer() {
		
	}

	public Player getPlayer() {
		return (Player) entities.get("player");
	}



}
