package engine;

import gfx.AttackMoveAnimated;
import gfx.GameHUD;
import gfx.GameOverScreen;
import gfx.IntroText;
import gfx.PauseScreen;
import gfx.Renderable;
import gfx.ScrollingCombatText;
import gfx.SplashScreen;
import gfx.SpriteSheet;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import worldmap.WorldMap;
import characters.Combatable;
import characters.CyanRectangleEnemy;
import characters.HumanoidEnemy;
import characters.Player;


public class GameEngine {
    private Map<String, Entity> entities;
    private Map<String, ArrayList<BufferedImage>> images;
    private Map<String, Renderable> renderables;
    private Player player;
    private LevelManager levelManager;
    private CombatManager combatManager;
    private WorldMap worldMap;
    private FontLoader fontLoader;
    private GameEventListener inputListener;
    private SoundEngine soundEngine;
    private long pauseTimer;

    public GameEngine(Map<String, Entity> entities,
            Map<String, Renderable> renderables,
            Map<String, ArrayList<BufferedImage>> images,
            SoundEngine soundEngine, GameEventListener listener) {

        this.entities = entities;
        this.renderables = renderables;
        this.images = images;
        this.soundEngine = soundEngine;
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
    }



    private void initManagers() {
        levelManager = new LevelManager(player,
                (ScrollingCombatText) renderables.get("xpSCT"),
                soundEngine);
        levelManager.setLevelUpSCT((ScrollingCombatText) renderables
                .get("levelUpSCT"));
        combatManager = new CombatManager(player, this);
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
        Entity blueEnemy = new CyanRectangleEnemy(100 + 512, 100, 20, 15, 1,
                Color.blue);
        entities.put("blueEnemy", blueEnemy);


        AttackMoveAnimated trollSprite = new AttackMoveAnimated(
                new SpriteSheet(images.get("trollANIMATED"), 4, 4), 100);
        // AttackMoveAnimated oldManSprite = new AttackMoveAnimated(
        // new SpriteSheet(images.get("oldmanANIMATED"), 4, 4), 100);
        // AttackMoveAnimated ghostSprite = new AttackMoveAnimated(
        // new SpriteSheet(images.get("ghostANIMATED"), 4, 4), 100);
        // AttackMoveAnimated bruteSprite = new AttackMoveAnimated(
        // new SpriteSheet(images.get("bruteANIMATED"), 4, 4), 100);
        // AttackMoveAnimated oldWomanSprite = new AttackMoveAnimated(
        // new SpriteSheet(images.get("oldwomanANIMATED"), 4, 4), 100);
        // AttackMoveAnimated germanSprite = new AttackMoveAnimated(
        // new SpriteSheet(images.get("germanANIMATED"), 4, 4), 100);

        Entity troll = new HumanoidEnemy(trollSprite, 120, 340, 100, 2,
                10000);
        // Entity oldman = new HumanoidEnemy(oldManSprite, 130 + 1024, 330, 100,
        // 2, 10000);
        // Entity ghost = new HumanoidEnemy(ghostSprite, 130, 360 + 512, 100, 2,
        // 10000);
        // Entity brute = new HumanoidEnemy(bruteSprite, 120 + 512, 360, 100, 2,
        // 10000);
        // Entity oldwoman = new HumanoidEnemy(oldWomanSprite, 100 + 512, 320,
        // 100, 2,
        // 10000);
        // Entity german = new HumanoidEnemy(germanSprite, 100, 300, 100, 2,
        // 10000);

        entities.put("troll", troll);
        // entities.put("oldman", oldman);
        // entities.put("ghost", ghost);
        // entities.put("brute", brute);
        // entities.put("oldwoman", oldwoman);
        // entities.put("german", german);

        // a oldman EnemyFour.png 4 4
        // a ghost EnemyThree.png 4 4
        // a brute EnemyTwo.png 4 4
        // a baldman npc1.png 4 4
        // a oldwoman npc3.png 4 4
        // a german npc4.png 4 4
        // Entity redEnemy = new CyanRectangleEnemy(180, 230, 29, 17, 2,
        // Color.red);
        // Entity whiteEnemy = new CyanRectangleEnemy(180, 190, 28, 37, 2,
        // Color.white);
        // Entity yellowEnemy = new CyanRectangleEnemy(100, 300, 15, 19, 2,
        // Color.yellow);
        // Entity greyEnemy = new CyanRectangleEnemy(150, 250, 30, 30, 2,
        // Color.gray);

        // entities.put("redEnemy", redEnemy);
        // entities.put("white", whiteEnemy);
        // entities.put("yellow", yellowEnemy);
        // entities.put("grey", greyEnemy);
    }

    private void initPlayer() {
        AttackMoveAnimated sprite = new AttackMoveAnimated(new SpriteSheet(
                images.get("playerANIMATED"), 8, 4), 100);
        player = new Player(sprite);
        GameHUD hud = new GameHUD(player);
        renderables.put("gameHUD", hud);
    }

    private void initRenderables() {
        SplashScreen splash = new SplashScreen();
        renderables.put("splash", splash);
        PauseScreen pauseScreen = new PauseScreen();
        renderables.put("pauseScreen", pauseScreen);

        GameOverScreen gameOverScreen = new GameOverScreen();
        renderables.put("gameOverScreen", gameOverScreen);
        ScrollingCombatText xpSCT = new ScrollingCombatText("+10XP", 0, 0, 12,
                Color.green);
        ScrollingCombatText enemySCT = new ScrollingCombatText("+10XP", 0, 0,
                13,
                Color.yellow);
        ScrollingCombatText playerSCT = new ScrollingCombatText("", 0, 0, 14,
                Color.red);
        ScrollingCombatText levelUpSCT = new ScrollingCombatText("", 0, 0, 24,
                Color.yellow);
        renderables.put("xpSCT", xpSCT);
        renderables.put("playerSCT", playerSCT);
        renderables.put("enemySCT", enemySCT);
        renderables.put("levelUpSCT", levelUpSCT);
        renderables.put("bsTxt", new IntroText());

    }

    public void update() {
        inputListener.update();
        updatePlayer();
        updateMap();
        combatManager.updateCombatables();

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

    public Map<String, ArrayList<BufferedImage>> getImages() {
        return images;
    }

    /**
     * Decides what to do during pause. We are just gonna force the
     * garbagecollector every minute.
     * 
     * @param delta
     */
    public void updatePaused(long delta) {
        pauseTimer += delta;
        if (pauseTimer > 1000 * 60) {
            System.out.println("Game paused, kicking in garbagecollector");
            System.gc();
            pauseTimer = 0;
        }
    }

    /**
     * Testing different aspects of the game. Sets up the visual bounding boes
     * that define the sprite bounds and the attackbounds.
     */
    public void initTest() {
        renderables.put("playerBOX", player.getSpriteBox());
        for (String key : entities.keySet()) {
            Entity e = entities.get(key);
            AttackBoundBox box = new AttackBoundBox((Combatable) e);
            System.out.println(box);
            renderables.put(box.toString(), box);
        }
    }

    public SoundEngine getSoundEngine() {
        return soundEngine;
    }


}
