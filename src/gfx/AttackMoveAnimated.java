package gfx;


public class AttackMoveAnimated extends Animated {

	public AttackMoveAnimated(SpriteSheet sheet, int frameDelay) {
		super(sheet, frameDelay);
	}

	public Animated getSouthMovementSheet() {
		return getRowAnimated(0);
	}
	
	public Animated getWestMovementSheet() {
		return getRowAnimated(1);
	}

	public Animated getEastMovementSheet() {
		return getRowAnimated(2);
	}

	public Animated getNorthMovementSheet() {
		return getRowAnimated(3);
	}

	public Animated getSouthAttackSheet() {
		return null;
	}

	public Animated getWestAttackSheet() {
		return null;
	}

	public Animated getEastAttackSheet(){
		return null;
	}

	public Animated getNorthAttackSheet() {
		return null;
	}
}
