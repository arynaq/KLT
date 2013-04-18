package engine;

import gfx.AttackMoveAnimated;
import gfx.Renderable;
import gfx.ScrollingTextXP;
import gfx.SpriteSheet;

import java.awt.Color;
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
	private Map<String, Renderable> renderables;
	private Player player;
	private LevelManager levelManager;
	private WorldMap worldMap;
	private FontLoader fontLoader;

	public GameEngine(Map<String, Entity> entities,
			Map<String, Renderable> renderables,
			Map<String, ArrayList<BufferedImage>> images,
			Map<String, GameSound> sounds) {

		this.entities = entities;
		this.renderables = renderables;
		this.images = images;
		this.sounds = sounds;
		initGameElements();

	}

	public void start() {
	}

	/**
	 * This method is responsible for initializing every renderable and entity
	 * that is needed in the game.
	 */
	private void initGameElements() {
		initRenderables();
		initPlayer();
		initEntities();
		initManagers();
		initMaps();
		initFonts();
	}

	private void initManagers() {
		levelManager = new LevelManager(player, renderables.get("xpSCT"));

	}

	public void initFonts() {
		fontLoader = new FontLoader();
	}

	private void initMaps() {
		worldMap = new WorldMap(images.get("worldWORLDMAP"));
		GameState.getInstance().setWorldMap(worldMap);
		GameState.getInstance().setCurrentMap(worldMap.getGameMap());

	}

	private void initEntities() {
		entities.put("player", player);
		// KHLOL no entities as of yet

	}

	private void initPlayer() {
		AttackMoveAnimated sprite = new AttackMoveAnimated(new SpriteSheet(
				images.get("indianaANIMATED"), 4, 4), 100);
		player = new Player(sprite);
	}

	private void initRenderables() {
		ScrollingTextXP xpSCT = new ScrollingTextXP("+10XP", 0, 0, 10,
				Color.green);
		renderables.put("xpSCT", xpSCT);

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

	public LevelManager getLevelManager() {
		return this.levelManager;
	}



}
