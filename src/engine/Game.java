package engine;


public class Game {
	private Factory factory;
	private GraphicsEngine gfx;
	private SoundEngine sfx;
	private GameEngine engine;
	private static final Game instance = new Game();
	private long fpsDelta, delta, t0;

	private Game() {
		factory = new Factory();
		this.engine = factory.createGameEngine();
		this.sfx = factory.createSoundEngine();
		this.gfx = factory.createGraphicsEngine();
		this.fpsDelta = (long) (1000.0 / GameState.getInstance().getFPS());
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

		while (true){
			t0 = time();
			while (GameState.getInstance().getState() == GameCondition.RUNNING) {

                engine.update();
				sfx.playMusic("beezDul.wav");
				delta = time() - t0;
				gfx.render((int) delta);
				t0 = time();

				if ((fpsDelta - delta) <= 0) {
					sleep(2);
				}

				else {
					sleep(fpsDelta - delta);
				}


			}

			while (GameState.getInstance().getState() == GameCondition.GAMEOVER) {
				sfx.playerGameOver();
				gfx.renderGameOver();
				sleep(100);
			}

			while (GameState.getInstance().getState() == GameCondition.PAUSED) {
				sfx.pauseGame();
				gfx.renderPause();
				sleep(100);
			}

            while (GameState.getInstance().getState() == GameCondition.SPLASH) {
                engine.update();
                gfx.renderSplash();
                sleep(100);
            }
		}
	}

	private long time() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		Game game = Game.getInstance();
        // ExamplePlayer ex = new ExamplePlayer();
        // ex.start();
		game.init();
		game.gameLoop();
	}

	private void sleep(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
