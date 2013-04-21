package engine;

import gfx.AttackMoveAnimated;
import gfx.GameHUD;
import gfx.Renderable;
import gfx.ScrollingCombatText;
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
	private GameEventListener gameEventListener;

	public GameEngine(Map<String, Entity> entities,
			Map<String, Renderable> renderables,
			Map<String, ArrayList<BufferedImage>> images,
			Map<String, GameSound> sounds, GameEventListener listener) {

		this.entities = entities;
		this.renderables = renderables;
		this.images = images;
		this.sounds = sounds;
		this.gameEventListener = listener;
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
		levelManager.setDamageSCT((ScrollingCombatText) renderables.get("dmgSCT"));
		levelManager.setLevelUpSCT((ScrollingCombatText) renderables
				.get("levelUpSCT"));
		levelManager.setHpSCT((ScrollingCombatText) renderables.get("hpSCT"));

	}

	public void initFonts() {
		fontLoader = new FontLoader("visitor2.ttf");
		fontLoader = new FontLoader("m04.ttf");
		fontLoader = new FontLoader("m04b.ttf");
		fontLoader = new FontLoader("Commodore Pixelized v1.2.ttf");
		fontLoader = new FontLoader("manaspc.ttf");
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
				images.get("player2ANIMATED"), 4, 4), 100);
		player = new Player(sprite);
		GameHUD hud = new GameHUD(player);
		renderables.put("playerHUD", hud);
	}

	private void initRenderables() {
		ScrollingCombatText dmgSCT = new ScrollingCombatText("dmg", 0, 0, 10,
 Color.red);
		ScrollingCombatText xpSCT = new ScrollingCombatText("+10XP", 0, 0, 10,
				Color.orange);
		ScrollingCombatText levelUpSCT = new ScrollingCombatText("", 0, 0, 24,
				Color.yellow);
		ScrollingCombatText hpSCT = new ScrollingCombatText("hp", 0, 0, 10,
				Color.green);
		renderables.put("xpSCT", xpSCT);
		renderables.put("dmgSCT", dmgSCT);
		renderables.put("levelUpSCT", levelUpSCT);
		renderables.put("hpSCT", hpSCT);
		// Renderable qPan = new QuestPanel(0, 350, 512, 200, Color.green, 120,
		// true);
		// renderables.put("dufern", qPan);

	}

	public void update() {
		gameEventListener.update();
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
