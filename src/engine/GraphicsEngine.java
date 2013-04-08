package engine;

import gfx.GameFrame;
import gfx.Renderable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class GraphicsEngine {
	private GameFrame frame;
	private Map<String, Entity> entities;
	private Map<String, ArrayList<BufferedImage>> images;
	private Map<String, Renderable> renderables;
	private Graphics2D g;
	private BufferStrategy bufferStrategy;
	private int width;
	private int height;

	public GraphicsEngine(Map<String, Entity> entities,
			Map<String, ArrayList<BufferedImage>> images, GameFrame frame) {

		this.entities = entities;
		this.renderables = new HashMap<String, Renderable>();
		this.images = images;
		this.frame = frame;

		this.g = frame.getGraphics();
		this.bufferStrategy = frame.getBufferStrategy();
		this.width = (int) GameState.getInstance().getFrameWidth();
		this.height = (int) GameState.getInstance().getFrameHeight();
		clearScreen();
	}

	public void start() {
		frame.setVisible(true);
	}

	public void clearScreen() {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
	}

	public void showBuffer() {
		bufferStrategy.show();
	}

	public void drawAll() {
		long t = (long) (1E+9 / GameState.getInstance().getFPS());
		long t0 = System.nanoTime();
		long t1 = System.nanoTime();
		long delta = (long) (t - (t1 - t0) / (1E+9));
	}

	public void render() {
		drawAll();
	}

}

