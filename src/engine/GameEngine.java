package engine;



public class GameEngine {

	private GraphicsEngine gfx;
	private SoundEngine sfx;
	private LogicEngine logic;

	public GameEngine() {
		new GameState();
		this.gfx = new GraphicsEngine();
		this.sfx = new SoundEngine();
		this.logic = new LogicEngine();
	}




}
