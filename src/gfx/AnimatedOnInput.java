package gfx;

public class AnimatedOnInput extends AttackMoveAnimated {
	private int frameDelay;
	public AnimatedOnInput(SpriteSheet sheet, int frameDelay) {
		super(sheet, frameDelay);
		this.frameDelay = frameDelay;
	}

	public void move() {
		super.frameUpdate(frameDelay + 1);
	}

	public void stop() {
		super.frameUpdate(0);
	}

}
