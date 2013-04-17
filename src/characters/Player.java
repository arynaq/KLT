package characters;

import engine.Entity;
import engine.GameInput;
import engine.GameInput.Movement;
import engine.GameState;
import gfx.AttackMoveAnimated;
import gfx.Renderable;

public class Player extends GameCharacter implements Entity {

	private Renderable fullSheet;
	private Renderable moveSouthSheet;
	private Renderable moveWestSheet;
	private Renderable moveEastSheet;
	private Renderable moveNorthSheet;
	private Renderable attackSouthSheet;
	private Renderable attackWestSheet;
	private Renderable attackEastSheet;
	private Renderable attackNorthSheet;
	private Renderable currentRenderable;

	private State state;
	private GameInput.Movement facing;
	private int x;
	private int y;
	private int speedX = 5;
	private int speedY = 5;
	private int playerWidth;
	private int playerHeight;
	private boolean isWalking;

	/**
	 * The player is initialized with its sprite represented by the renderable.
	 * @param renderable
	 */
	public Player(Renderable renderable) {
		this.x = 120;
		this.y = 340;
		this.fullSheet = renderable;
		this.facing = Movement.RIGHT;
	}

	/**
	 * The player is initialized with its sprite being fully animated.
	 * 
	 * @param animatedSpriteSheet
	 */
	public Player(AttackMoveAnimated animatedSpriteSheet) {
		this.x = 120;
		this.y = 340;
		this.fullSheet = animatedSpriteSheet;
		this.moveSouthSheet = animatedSpriteSheet.getSouthMovementSheet();
		this.moveWestSheet = animatedSpriteSheet.getWestMovementSheet();
		this.moveEastSheet = animatedSpriteSheet.getEastMovementSheet();
		this.moveNorthSheet = animatedSpriteSheet.getNorthMovementSheet();
		this.playerWidth = animatedSpriteSheet.getSheeet().getImages().get(0)
				.getWidth();
		this.playerHeight = animatedSpriteSheet.getSheeet().getImages().get(0)
				.getHeight();
		this.facing = Movement.RIGHT;
	}

	@Override
	public Renderable getRenderable() {
		if (facing == Movement.RIGHT) {
			currentRenderable = moveEastSheet;
		}

		else if (facing == Movement.LEFT) {

			currentRenderable = moveWestSheet;
		}

		else if (facing == Movement.UP) {
			currentRenderable = moveNorthSheet;
		}

		else if (facing == Movement.DOWN) {
			currentRenderable = moveSouthSheet;
		}
		updateCurrentRenderable();
		return currentRenderable;
	}

	private void updateCurrentRenderable() {
		this.currentRenderable.setX(x % GameState.DIMENSION.width);
		this.currentRenderable.setY(y % GameState.DIMENSION.height);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return state;
	}

	private void setReturnRenderable() {
		if (state == State.DEAD) {
			// He is dead, send the dying animation
			return;
		}

		if (facing == Movement.DOWN) {
		}
	}

	public boolean isAlive() {
		if (state == State.DEAD) {
			return false;
		}

		return true;
	}

	public void setX(int x) {
		this.x = x;
		this.currentRenderable.setX(x % GameState.DIMENSION.width);
	}

	public void setY(int y) {
		this.y = y;
		this.currentRenderable.setY(y % GameState.DIMENSION.height);
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setFacing(GameInput.Movement facing) {
		this.facing = facing;
	}

	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}

	public int getWidth() {
		return this.playerWidth;
	}

	public int getHeight() {
		return this.playerHeight;
	}
}
