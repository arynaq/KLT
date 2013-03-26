package engine;

import gfx.GameFrame;

import java.awt.Color;



public class GraphicsEngine {
	private GameFrame gameFrame;

	public GraphicsEngine() {
		this.gameFrame = new GameFrame(Color.black);
	}

	private void splashScreen() {
		this.gameFrame.setBackgroundColor(Color.red);
	}

	public void setWindowTitle(String title) {
		this.gameFrame.setTitle(title);
	}

	public static void main(String[] args) {
		GraphicsEngine gfx = new GraphicsEngine();
		gfx.setWindowTitle("Gukern");
		gfx.setWindowTitle("FitteTryne");
	}
}

