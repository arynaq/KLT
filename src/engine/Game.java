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
        System.out.println("Starting game.");
		engine.start();
		sleep(10);
		gfx.start();
		sleep(10);
	}

	public void gameLoop() {

		while (true){
			t0 = time();
			while (GameState.getInstance().getState() == GameCondition.RUNNING) {

                engine.update(time() - t0);
                sfx.start();
				delta = time() - t0;
				gfx.render((int) delta);
				t0 = time();

				if ((fpsDelta - delta) <= 0) {
                    sleep(5);
				}

				else {
					sleep(fpsDelta - delta);
				}
			}

			while (GameState.getInstance().getState() == GameCondition.GAMEOVER) {
				sfx.playerGameOver();
				gfx.renderGameOver();
                sleep(100);
                System.gc();
			}
            // engine.getRenderables().remove("gameOverScreen");

			while (GameState.getInstance().getState() == GameCondition.PAUSED) {
                delta = time() - t0;
				sfx.pauseGame();
				gfx.renderPause();
                engine.updatePaused(delta);
                t0 = time();
                sleep(100);
			}

            while (GameState.getInstance().getState() == GameCondition.SPLASH) {
                if (GameState.getInstance().isEnableVisualTesting()) {
                    engine.initTest();
                }
                sfx.splash();
                gfx.renderSplash();
                System.gc();
                sleep(1000);
            }
            engine.getRenderables().remove("splash");
            engine.getImages().remove("splashBACKGROUND");

		}
	}

	private long time() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
        if (args.length != 0) {
            for (String option : args) {
                if (option.split("=")[0].equals("doTest")
                        && option.split("=")[1].equals("true"))
                    GameState.getInstance().setEnableVisualTesting(true);

                else if (option.split("=")[0].equals("settings")
                        && option.split("=")[1].equals("low"))
                    GameState.setFPS(30);
            }
        }
		Game game = Game.getInstance();
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
