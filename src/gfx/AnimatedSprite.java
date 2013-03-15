package gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AnimatedSprite extends Sprite {
	
	private Map<Integer, BufferedImage> frames;

	public AnimatedSprite(SpriteType type) {
		super(type);
		this.frames = new HashMap<Integer, BufferedImage>();
		

	}


}
