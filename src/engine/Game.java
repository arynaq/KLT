package engine;

public class Game {
	private Factory factory;
	private GraphicsEngine gfx;
	private SoundEngine sfx;
	private GameEngine engine;
	private static final Game instance = new Game();

	private Game() {
		factory = new Factory();
		this.engine = factory.createGameEngine();
		this.sfx = factory.createSoundEngine();
		this.gfx = factory.createGraphicsEngine();
	}

	public static Game getInstance() {
		return instance;
	}

	public void init() {
		gfx.start();
		sfx.start();
		engine.start();
	}

	public void gameLoop() {
		engine.update();
		gfx.render();
		sfx.play();
	}

	public static void main(String[] args) {
		Game game = Game.getInstance();
		game.gameLoop();
	}
}
