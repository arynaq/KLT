package gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animated implements Renderable {
	private ArrayList<BufferedImage> sheet;
	private int cols;
	private int rows;
	private int x;
	private int y;
	private int frameDelay, deltaTimeSum;
	private int currentFrame;
	private int lastFrame;
	private BufferedImage currentImage;

	public Animated(ArrayList<BufferedImage> spritesheet, int cols,
			int rows, int frameDelay) {

		this.sheet = spritesheet;
		this.cols = cols;
		this.rows = rows;
		this.frameDelay = frameDelay;
		this.lastFrame = sheet.size() - 1;
		this.currentImage = sheet.get(0);
	}


	private void frameUpdate(int deltaTime) {
		deltaTimeSum += deltaTime;
		if (deltaTimeSum >= frameDelay) {
			if (currentFrame == lastFrame) {
				currentFrame = 0;
			}
			else {
				currentFrame++;
			}
			currentImage = sheet.get(currentFrame);
			deltaTimeSum = 0;
		}
	}

	@Override
	public void render(Graphics2D g) {

	}


	@Override
	public void render(Graphics2D g, int deltaTime) {
		frameUpdate(deltaTime);
		g.drawImage(currentImage, x, y, null);

	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

}
