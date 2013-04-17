package engine;

import gfx.GameFrame;
import gfx.GameImage;
import gfx.Renderable;
import gfx.Sprite;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class GraphicsEngine {

	private Map<String, Entity> entities;
	private Map<String, ArrayList<BufferedImage>> images;
	private Map<String, Renderable> renderables;
	private int width;
	private int height;

	private Graphics2D g;
	private BufferStrategy bufferStrategy;
	private GameFrame frame;
	private FontMetrics fontMetrics;


	public GraphicsEngine(Map<String, Entity> entities,
			Map<String, ArrayList<BufferedImage>> images, GameFrame frame) {

		this.entities = entities;
		this.renderables = new HashMap<String, Renderable>();
		this.images = images;
		this.frame = frame;

		this.g = frame.getGraphics();
		this.fontMetrics = g.getFontMetrics();
		this.bufferStrategy = frame.getBufferStrategy();
		this.width = (int) GameState.getInstance().getFrameWidth();
		this.height = (int) GameState.getInstance().getFrameHeight();

		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		clearScreen();
	}

	public void start() {
		frame.setVisible(true);
		Renderable currentMap = GameState.getInstance().getCurrentMap();
		renderables.put("currentMap", currentMap);
		// Renderable bahamut;
		// int imageWidth = images.get("bahamutANIMATED").get(0).getWidth();
		// int imageHeight = images.get("bahamutANIMATED").get(0).getHeight();
		// for (int i = 0; i < width / imageWidth; i++) {
		// for (int j = 0; j < height / imageHeight; j++) {
		//
		// int delay = (int) ((1000 - 10) * Math.random() + 10);
		// bahamut = new Animated(images.get("bahamutANIMATED"), 4, 4,
		// 10 + delay);
		// bahamut.setX(i * imageWidth);
		// bahamut.setY(j * imageHeight);
		// renderables.put("" + i + "," + j, bahamut);
		// }
		// }
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
			renderables.get(key).render(g);
			renderables.get(key).render(g, delta);

		}
		for (String key : entities.keySet()) {
			entities.get(key).getRenderable().render(g);
			entities.get(key).getRenderable().render(g, delta);
		}
		showBuffer();

	}

	public Sprite getSprite(String key) {
		if (images.containsKey(key + "SPRITE")) {
			return new Sprite(new GameImage(images.get(key + "SPRITE")));
		}
		return null;
	}

	public void renderGameOver() {
		Composite cG = g.getComposite();
		Composite c = AlphaComposite.getInstance(AlphaComposite.DST_IN, 0.9f);
		Font fontG = g.getFont();
		Font messageFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		String message = "GAME OVER";

		g.setFont(messageFont);
		fontMetrics = g.getFontMetrics();
		int offsetX = fontMetrics.stringWidth(message) / 2;
		int offsetY = fontMetrics.getHeight() / 2;


		g.setComposite(c);
		clearScreen();
		g.setComposite(cG);
		g.setColor(Color.green);
		g.drawString(message, (width / 2) - offsetX, (height / 2) - offsetY);
		g.setFont(fontG);
		showBuffer();
	}

}

