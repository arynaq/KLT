package worldmap;

import gfx.Renderable;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameMap implements Renderable {
	BufferedImage image;
	private int[] xBound;
	private int[] yBound;

	public GameMap(BufferedImage bufferedImage) {
		this.image = bufferedImage;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, 0, 0, null);

	}

	@Override
	public void render(Graphics2D g, int deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

	public void Gukern() {

	}

	protected void setxBound(int[] xBound) {
		this.xBound = xBound;
	}

	protected void setyBound(int[] yBound) {
		this.yBound = yBound;
	}

	public int[] getxBound() {
		return xBound;
	}

	public int[] getyBound() {
		return yBound;
	}

}
