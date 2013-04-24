package gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animated implements Renderable {
	private SpriteSheet sheet;
	private int x;
	private int y;
	private int frameDelay, deltaTimeSum;
	private int currentFrame;
	private int lastFrame;
	private BufferedImage currentImage;
	private boolean timeIndependant;
	private int moveCount;

	/**
	 * Constructs a new animated sprite that is moving in time, the next frame
	 * in the spritesheet will be rendered if more than frameDelay time has
	 * passed.
	 * 
	 * @param sheet
	 * @param frameDelay
	 */
	public Animated(SpriteSheet sheet, int frameDelay) {
		this.sheet = sheet;
		this.frameDelay = frameDelay;
		this.lastFrame = sheet.getSize() - 1;
		this.currentImage = sheet.getFrame(0);
		this.timeIndependant = true;
	}

	/**
	 * Constructs a new animated sprite that is stationary in time, it only
	 * moves to the next frame if actively asked for it.
	 * 
	 * @param sheet
	 */
	public Animated(SpriteSheet sheet) {
		this.sheet = sheet;
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
	
	/**
	 * This method simply updates to the next frame when called. If the current
	 * frame is the last frame it restarts the animation from the first frame.
	 */
	@SuppressWarnings("unused")
	private void frameUpdate() {
		if (currentFrame == lastFrame) {
			currentFrame = 0;
		} else {
			currentFrame++;
		}

		currentImage = sheet.getFrame(currentFrame);
	}

	@Override
	public void render(Graphics2D g) {
		if (timeIndependant)
			return;
		g.drawImage(currentImage, x, y, null);
	}


	@Override
	public void render(Graphics2D g, int deltaTime) {
		if (!timeIndependant)
			return;
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
		return this.sheet;
	}

	public Animated getRowAnimated(int rowNumber) {
        return new Animated(sheet.getRow(rowNumber));
	}

    public Animated getRowAnimated(int rowNumber, int frameDelay) {
        return new Animated(sheet.getRow(rowNumber), frameDelay);
    }

	/**
	 * Used to push this animation one frame ahead. Steps define how many times
	 * this function must be called before the animation goes to the next frame.
	 * 
	 * @param steps
	 */
	public void move(int steps) {
		if (moveCount > steps) {
			frameUpdate();
			moveCount = 0;
		}
		moveCount++;

	}

	/**
	 * Resets this animation by setting the currentframe to the first frame.
	 */
	public void reset() {
		currentFrame = 0;
	}

    public boolean isOver() {
        return currentFrame == lastFrame;
    }

}
