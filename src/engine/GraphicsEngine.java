package engine;

import gfx.GameFrame;
import gfx.GameImage;
import gfx.Renderable;
import gfx.Sprite;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

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
		showBuffer();
		// renderHUD();
	}

	// /**
	// * Renders HUD
	// */
	// public void renderHUD() {
	// player = (Player) entities.get("player");
	// /*
	// * Level indicator
	// */
	// renderString("Level", 40, 20, 12, Color.yellow, false);
	// renderString("" + player.getLevels().getLevel(), 40, 40, 18,
	// Color.white, true);
	//
	// /*
	// * XP indicator
	// */
	// renderString("XP", 70, 485, 10, Color.yellow, true);
	// renderString(player.getXP() + "/" + player.getLevels().getReqXP(), 70,
	// 505, 18, Color.white, true);
	//
	// /*
	// * HP indicator
	// */
	// renderString("Health", 455, 20, 10, Color.yellow, true);
	// renderString(player.getHealth() + "/" + player.getMaxHealth(), 455, 40,
	// 18, Color.white, true);
	//
	// /*
	// * Potion indicator
	// */
	// renderString("Potion", 455, 485, 10, Color.yellow, true);
	// renderString("" + player.getPotions().size(), 455, 505, 18,
	// Color.white,
	// true);
	//
	// showBuffer();
	//
	// }

	public Sprite getSprite(String key) {
		if (images.containsKey(key + "SPRITE")) {
			return new Sprite(new GameImage(images.get(key + "SPRITE")));
		}
		return null;
	}

	public void renderGameOver() {
		clearScreen();
		renderString("Game Over", 256, 256, 34, Color.white, Color.blue, false);
		showBuffer();
	}



	public void renderPause() {
		renderString("Game Paused", 120, 256, 34, Color.black, Color.white,
				false);
		showBuffer();
	}

	public void renderSplash() {
		Color color = new Color(255, 255, 255);
		g.setColor(color);
		g.fillRect(0, 0, 512, 512);
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(
					"/sprites/kloftasmall3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, null);
		int centerX = (GameState.DIMENSION.width / 2);
		renderString("KLT", centerX, 410, 98, Color.black, Color.white,
				true);
		// renderString("alpha", 440, 180, 12, Color.black, Color.white, true);
		renderString("- The Role Playing Game -", centerX, 430, 14,
				Color.white, Color.black, false);
		renderString("START GAME", centerX, 460, 18, Color.white,
				Color.blue, false);
		renderString("A game by: Fredrik 'Ferd' Larsen and Aryan Naqid (2013)",
				centerX, 506, 12, Color.white, Color.black, false);
		showBuffer();
	}

	public void renderString(String string, int x, int y, int size,
			Color color, Color stroke, boolean bold) {
		Font stringFont;
		if (bold == true) {
			stringFont = new Font("Commodore 64 Pixelized", Font.BOLD,
					size);
		} else {
			stringFont = new Font("Commodore 64 Pixelized", Font.PLAIN,
					size);
		}
		int h = y;
		int w = x;
		FontRenderContext frc = g.getFontRenderContext();
		AttributedString as = new AttributedString(string);
		as.addAttribute(TextAttribute.FONT, stringFont, 0, string.length());
		AttributedCharacterIterator aci = as.getIterator();
		TextLayout tl = new TextLayout(aci, frc);
		float sw = (float) tl.getBounds().getWidth();
		Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(w
				- (sw / 2), h));
		g.setColor(stroke);
		g.setStroke(new BasicStroke(3f));
		g.draw(sha);
		g.setColor(color);
		g.fill(sha);

	}

	public void renderRectangle(int x, int y, int w, int h, Color color,
			boolean rounded) {
		g.setColor(color);
		if (rounded == true) {
			g.fillRoundRect(x, y, w, h, 5, 6);
		} else {
			g.fillRect(x, y, w, h);

		}
	}

}

