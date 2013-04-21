package engine;

import gfx.GameFrame;
import gfx.GameImage;
import gfx.Renderable;
import gfx.Sprite;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
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

    // public void renderString(String string, int x, int y, int size, Color
    // color) {
    // Font fontG = g.getFont();
    // Font stringFont = new Font(Font.SANS_SERIF, Font.BOLD, size);
    // g.setFont(stringFont);
    // g.setColor(color);
    // g.drawString(string, x, y);
    // }
    //
    // public void renderString(String string, int x, int y, int size) {
    // Font fontG = g.getFont();
    // Font stringFont = new Font(Font.SANS_SERIF, Font.BOLD, size);
    // g.setFont(stringFont);
    // g.setColor(Color.black);
    // g.drawString(string, x, y);
    // }

    public void renderString(String string, int x, int y, int size,
            Color color, boolean bold) {
        Font stringFont;
        if (bold == true) {
            stringFont = new Font("Commodore 64 Pixelized", Font.BOLD, size);
        } else {
            stringFont = new Font("Commodore 64 Pixelized", Font.PLAIN, size);
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

        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3f));
        g.draw(sha);
        g.setColor(color);
        g.fill(sha);

    }

	public void renderRectangle(int x, int y, int w, int h,
			String color) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, w, h);
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

}

