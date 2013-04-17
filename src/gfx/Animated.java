package gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animated implements Renderable {
	private SpriteSheet sheet;
	private int cols;
	private int rows;
	private int x;
	private int y;
	private int frameDelay, deltaTimeSum;
	private int currentFrame;
	private int lastFrame;
	private BufferedImage currentImage;
	private boolean doUpdate = false;

	public Animated(SpriteSheet sheet, int frameDelay) {

		this.sheet = sheet;
		this.frameDelay = frameDelay;
		this.lastFrame = sheet.getSize() - 1;
		this.currentImage = sheet.getFrame(0);
	}


	private void frameUpdate(int deltaTime) {
		deltaTimeSum += deltaTime;
		if (deltaTimeSum >= getFrameDelay()) {
			if (currentFrame == lastFrame) {
				currentFrame = 0;
			}
			else {
				currentFrame++;
			}
			currentImage = sheet.getFrame(currentFrame);
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


	public int getFrameDelay() {
		return frameDelay;
	}



	public ArrayList<BufferedImage> getSheetList() {
		return sheet.getImages();
	}


	public SpriteSheet getSheeet() {
		// TODO Auto-generated method stub
		return this.sheet;
	}

	public Animated getRowAnimated(int rowNumber) {
		return new Animated(sheet.getRow(rowNumber), this.frameDelay);
	}

	@Override
	public boolean doRender() {
		// TODO Auto-generated method stub
		return false;
	}

}
