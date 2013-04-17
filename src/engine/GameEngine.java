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
	private int oldPlayerX, oldplayerY;

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
				images.get("indianaANIMATED"), 4, 4));
		player = new Player(sprite);
		entities.put("player", player);


		WorldMap worldMap = new WorldMap(images.get("worldWORLDMAP"));
		GameState.getInstance().setWorldMap(worldMap);
		GameState.getInstance().setCurrentMap(worldMap.getGameMap());

	}

	public void update() {
		updatePlayer();
	}

	private void updatePlayer() {
		int newPlayerX = player.getX();
		int newPlayerY = player.getY();

		// if (newPlayerX == oldPlayerX && newPlayerY == oldplayerY) {
		// ((Animated) player.getRenderable()).stop();
		// }
		//
		// else {
		// ((Animated) player.getRenderable()).resume();
		// }

		oldPlayerX = newPlayerX;
		oldplayerY = newPlayerY;
	}

	public Player getPlayer() {
		return (Player) entities.get("player");
	}



}
