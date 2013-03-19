package gfx;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import engine.GameEngine;

public class AnimatedSprite extends Sprite {

	private int rows;
	private int columns;
	private int frameCount;
	private int frameWidth;
	private int frameHeight;
	private int FPS;
	private double deltaTime;
	private Map<Integer, Image> frames;

	private int gameFPS = GameEngine.FPS;
	private int repeatRate = 2;

	public AnimatedSprite(SpriteType type, int FPS) {
		// TO DO, read rows and columns from imagefile, it is slow, override
		// super loader?
		super(type);
		this.frames = new HashMap<Integer, Image>();
		this.FPS = FPS;
		this.deltaTime = 1.0 / (repeatRate * FPS);
		this.frameCount = rows * columns;

	}
	
	public AnimatedSprite(SpriteType type, int FPS, int cols, int rows) {
		this(type, FPS);
		this.columns = cols;
		this.rows = rows;
	}

	// Load each invidivual frame in the stripe into the frames hashmap
	public void loadFrames() {
		if (rows != 0 & columns != 0) {
			this.frameWidth = getWidth() / columns;
			this.frameHeight = getHeight() / rows;
			for(int i=0; i<rows; i++) {
				for (int j = 0; j < columns; j++) {
					frames.put(i * j,
							getImg().getSubimage(i, j, frameWidth, frameHeight));
				}
			}
		}
	}

	public void setFPS(int fPS) {
		FPS = fPS;
		deltaTime = 1.0 / (repeatRate * FPS);
	}

	public int getFPS() {
		return FPS;
	}

	public double getTimeDelay() {
		return deltaTime;
	}

	public int getFrameCount() {
		return frameCount;
	}

	public void update() {
		if (getSpriteState() == SpriteState.ALIVE) {
			// Keep rendering, do timing checks, do progress to next frame
		}
		
		if (getSpriteState() == SpriteState.DEAD) {
			// Da fuck happens when the sprite dies?
		}

		if (getSpriteState() == SpriteState.PAUSED) {
			// We definitely do not render when paused
		}

	}

}
