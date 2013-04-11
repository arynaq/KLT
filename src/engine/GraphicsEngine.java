package engine;

import gfx.Animated;
import gfx.GameFrame;
import gfx.GameImage;
import gfx.Renderable;
import gfx.Sprite;

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
	private static Map<String, ArrayList<BufferedImage>> images;
	private Map<String, Renderable> renderables;
	private Graphics2D g;
	private BufferStrategy bufferStrategy;
	private int width;
	private int height;

	public GraphicsEngine(Map<String, Entity> entities,
			Map<String, ArrayList<BufferedImage>> images, GameFrame frame) {

		this.entities = entities;
		this.renderables = new HashMap<String, Renderable>();
		GraphicsEngine.images = images;
		this.frame = frame;

		this.g = frame.getGraphics();
		this.bufferStrategy = frame.getBufferStrategy();
		this.width = (int) GameState.getInstance().getFrameWidth();
		this.height = (int) GameState.getInstance().getFrameHeight();
		clearScreen();
	}

	public void start() {
		frame.setVisible(true);
		Renderable bahamut;
		int imageWidth = images.get("bahamutANIMATED").get(0).getWidth();
		int imageHeight = images.get("bahamutANIMATED").get(0).getHeight();
		for (int i = 0; i < width / imageWidth; i++) {
			for (int j = 0; j < height / imageHeight; j++) {

				int delay = (int) ((1000 - 10) * Math.random() + 10);
				bahamut = new Animated(images.get("bahamutANIMATED"), 4, 4,
						10 + delay);
				bahamut.setX(i * imageWidth);
				bahamut.setY(j * imageHeight);
				renderables.put("" + i + "," + j, bahamut);
			}
		}
	}

	public void clearScreen() {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
	}

	public void showBuffer() {
		bufferStrategy.show();
	}

	public void drawAll() {

	}

	public void render(int delta) {
		clearScreen();
		for (String key : renderables.keySet()) {
			renderables.get(key).render(g, delta);
		}
		for (String key : entities.keySet()) {
			entities.get(key).getRenderable().render(g, delta);
		}
		showBuffer();

	}

	public static Sprite getSprite(String key) {
		if (images.containsKey(key + "SPRITE")) {
			return new Sprite(new GameImage(images.get(key + "SPRITE")));
		}
		return null;
	}

}

