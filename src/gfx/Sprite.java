package gfx;

import javax.swing.JFrame;


public class Sprite extends GameImage implements Renderable {

	private SpriteState spriteState;
	JFrame frame;
	

	public Sprite(SpriteType type) {
		super(type.fileName());
		this.spriteState = SpriteState.ALIVE;
	}

	public SpriteState getSpriteState() {
		return spriteState;
	}

	public void setState(SpriteState state) {
		this.spriteState = state;
	}


}
