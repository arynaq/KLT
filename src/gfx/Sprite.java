package gfx;

import java.awt.Graphics2D;

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

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}


}
