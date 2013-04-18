package engine;

import gfx.GameFrame;
import gfx.GameImage;
import gfx.Renderable;
import gfx.Sprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import characters.Player;



public class GraphicsEngine {

	private Map<String, Entity> entities;
	private Map<String, ArrayList<BufferedImage>> images;
	private Map<String, Renderable> renderables;
	private int width;
	private int height;
	private Player player;

	private Graphics2D g;
	private BufferStrategy bufferStrategy;
	private GameFrame frame;
	private FontMetrics fontMetrics;


	public GraphicsEngine(Map<String, Entity> entities,
			Map<String, Renderable> renderables,
			Map<String, ArrayList<BufferedImage>> images, GameFrame frame) {

		this.entities = entities;
		this.renderables = renderables;
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
		renderables.get("currentMap").render(g);
		for (String key : renderables.keySet()) {
			if (key.equals("currentMap"))
				continue;
			renderables.get(key).render(g);
			renderables.get(key).render(g, delta);

		}
		// renderables.get("xp").render(g);
		for (String key : entities.keySet()) {
			entities.get(key).getRenderable().render(g, delta);
			entities.get(key).getRenderable().render(g);
		}

		renderRectangle(0, 0, 512, 30, Color.white, false);

		// Tegner Teksten til HUD
		player = (Player) entities.get("player");
		renderString("HP: " + player.getHealth() + "/" + player.getMaxHealth(),
				350, 20, 16, Color.black);
		renderString("Potions: " + player.getNumPotions() + "", 160, 20, 16,
				Color.black);
		showBuffer();




	}

	public Sprite getSprite(String key) {
		if (images.containsKey(key + "SPRITE")) {
			return new Sprite(new GameImage(images.get(key + "SPRITE")));
		}
		return null;
	}

	public void renderGameOver() {
		clearScreen();
		renderString("Game Over", 130, 256, 34, Color.white);
		showBuffer();
	}



	public void renderPause() {
		renderString("Game Paused", 120, 256, 34, Color.black);
		showBuffer();

	}

	public void renderString(String string, int x, int y, int size, Color color) {
		// Font stringFont = new Font("Visitor TT2 BRK", Font.BOLD, size);
		Font stringFont = new Font("Commodore 64 Pixelized", Font.PLAIN, size);
		g.setFont(stringFont);
		g.setColor(color);
		g.drawString(string, x, y);
	}

	public void renderRectangle(int x, int y, int w, int h, Color color,
			boolean rounded) {
		g.setColor(color);
		if (rounded == true) {
			g.fillRoundRect(x, y, w, h, 10, 10);
		} else {
			g.fillRect(x, y, w, h);

		}
	}

}

