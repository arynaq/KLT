package engine;

import sfx.ExamplePlayer;

public class Game {
	private Factory factory;
	private GraphicsEngine gfx;
	private SoundEngine sfx;
	private GameEngine engine;
	private static final Game instance = new Game();
	private int t0, t1, delta;

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
		engine.start();
		sleep(10);
		gfx.start();
		sleep(10);
		sfx.start();
		sleep(10);
	}

	public void gameLoop() {
		t0 = time();
		while (GameState.getInstance().getState() == GameCondition.RUNNING) {

			engine.update();
			sfx.play();

			t1 = time();
			delta = t1 - t0;

			gfx.render(delta);
			sleep(10);
		}
	}

	private int time() {
		return (int) System.currentTimeMillis();
	}

	public static void main(String[] args) {
		Game game = Game.getInstance();
		ExamplePlayer ex = new ExamplePlayer();
		ex.start();
		game.init();
		game.gameLoop();
	}

	private void sleep(int milliSeconds){
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
