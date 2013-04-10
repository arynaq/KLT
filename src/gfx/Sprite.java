package gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;



public class Sprite implements Renderable {

	private BufferedImage image;
	private int x;
	private int y;

	public Sprite(GameImage image) {
		this.image = image.getImage();
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, x, y, null);

	}

	@Override
	public void render(Graphics2D g, int deltaTime) {
		g.drawImage(image, x, y, null);

	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
