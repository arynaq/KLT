package engine;

import gfx.AttackMoveAnimated;
import gfx.Renderable;
import gfx.ScrollingCombatText;
import gfx.SpriteSheet;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import sfx.GameSound;
import worldmap.WorldMap;
import characters.Combatable;
import characters.CyanRectangleEnemy;
import characters.Player;


public class GameEngine {
	private Map<String, Entity> entities;
	private Map<String, ArrayList<BufferedImage>> images;
	private Map<String, GameSound> sounds;
	private Map<String, Renderable> renderables;
	private Player player;
	private LevelManager levelManager;
	private CombatManager combatManager;
	private WorldMap worldMap;
	private FontLoader fontLoader;
    private GameEventListener inputListener;

	public GameEngine(Map<String, Entity> entities,
			Map<String, Renderable> renderables,
			Map<String, ArrayList<BufferedImage>> images,
            Map<String, GameSound> sounds, GameEventListener listener) {

		this.entities = entities;
		this.renderables = renderables;
		this.images = images;
		this.sounds = sounds;
        this.inputListener = listener;
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
		initTest();
	}

	/**
	 * Testing different aspects of the game.
	 */
	private void initTest() {
		renderables.put("playerBOX", player.getSpriteBox());
		for (String key : entities.keySet()) {
			Entity e = entities.get(key);
			AttackBoundBox box = new AttackBoundBox((Combatable) e);
			renderables.put(box.toString(), box);
		}
	}

	private void initManagers() {
		levelManager = new LevelManager(player, renderables.get("xpSCT"));
		combatManager = new CombatManager(player, renderables.get("dmgSCT"),
				this);
	}

	public void initFonts() {
		// fontLoader = new FontLoader();
	}

	private void initMaps() {
		worldMap = new WorldMap(images.get("worldWORLDMAP"));
		GameState.getInstance().setWorldMap(worldMap);
		GameState.getInstance().setCurrentMap(worldMap.getGameMap());

	}

	private void initEntities() {
		entities.put("player", player);
		Entity blueEnemy = new CyanRectangleEnemy(1, 100);
		entities.put("blueEnemy", blueEnemy);
	}

	private void initPlayer() {
		AttackMoveAnimated sprite = new AttackMoveAnimated(new SpriteSheet(
                images.get("playerANIMATED"), 4, 4), 100);
		player = new Player(sprite);
	}

	private void initRenderables() {
		ScrollingCombatText xpSCT = new ScrollingCombatText("+10XP", 0, 0, 10,
				Color.green);
		renderables.put("xpSCT", xpSCT);

	}

	public void update() {
        inputListener.update();
		updatePlayer();
		updateMap();
		
        ((CyanRectangleEnemy) entities.get("blueEnemy")).setFacing(player
                .getFacing().opposite());

	}
	private void updateMap() {
        worldMap.updateGameMap(player.getX(), player.getY());
	}

	private void updatePlayer() {

	}

	public Player getPlayer() {
		return (Player) entities.get("player");
	}

	public LevelManager getLevelManager() {
		return this.levelManager;
	}

	public CombatManager getCombatManager() {
		return combatManager;
	}

	public Map<String, Entity> getEntities() {
		return entities;
	}

	public Map<String, Renderable> getRenderables() {
		return renderables;
	}

}
