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
import java.util.Map;

import characters.Player;



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
		Player player = (Player) entities.get("player");
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
		}
		
		renderRectangle(200, 0, 300, 30, "dufern");

		// Tegner Teksten til HUD
		Player player = (Player) entities.get("player");
		renderString("HP: " + player.getHealth() + "/" + player.getMaxHealth(),
				350, 20, 25, Color.black);
		renderString("Potions: " + player.getNumPotions() + "", 200, 20, 25,
				Color.black);
		// showBuffer();

		// renderString("+10xp", player.getX(), player.getY(), 10, Color.green);
		showBuffer();

		// renderables.get("xp").setX(player.getX());
		// renderables.get("xp").setY(player.getY());


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

	public void renderPause() {
		Composite cG = g.getComposite();
		Composite c = AlphaComposite.getInstance(AlphaComposite.DST_IN, 0.9f);
		Font fontG = g.getFont();
		Font messageFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		String message = "PAUSED";

		g.setFont(messageFont);
		fontMetrics = g.getFontMetrics();
		int offsetX = fontMetrics.stringWidth(message) / 2;
		int offsetY = fontMetrics.getHeight() / 2;

		g.setComposite(c);
		clearScreen();
		g.setComposite(cG);
		g.setColor(Color.black);
		g.drawString(message, (width / 2) - offsetX, (height / 2) - offsetY);
		g.setFont(fontG);
		showBuffer();
	}

	public void renderString(String string, int x, int y, int size, Color color) {
		Font fontG = g.getFont();
		Font stringFont = new Font(Font.SANS_SERIF, Font.BOLD, size);
		g.setFont(stringFont);
		g.setColor(color);
		g.drawString(string, x, y);
	}

	public void renderString(String string, int x, int y, int size) {
		Font fontG = g.getFont();
		Font stringFont = new Font(Font.SANS_SERIF, Font.BOLD, size);
		g.setFont(stringFont);
		g.setColor(Color.black);
		g.drawString(string, x, y);
	}

	public void renderRectangle(int x, int y, int w, int h,
			String color) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, w, h);
	}

}

