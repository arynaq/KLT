package gfx;


public class AttackMoveAnimated extends Animated {

	public AttackMoveAnimated(SpriteSheet sheet, int frameDelay) {
		super(sheet, frameDelay);
	}

	public AttackMoveAnimated(SpriteSheet spriteSheet) {
		super(spriteSheet);
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
        return getRowAnimated(4, 100);
	}

	public Animated getWestAttackSheet() {
        return getRowAnimated(5, 100);
	}

	public Animated getEastAttackSheet(){
        return getRowAnimated(6, 100);
	}

	public Animated getNorthAttackSheet() {
        return getRowAnimated(7, 100);
	}

}
