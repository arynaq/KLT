package characters;

import engine.Entity;
import engine.GameInput;
import engine.GameState;
import gfx.Renderable;

public class Player extends GameCharacter implements Entity {

	private Renderable attackSheet;
	private Renderable movementSheet;
	private Renderable renderable;
	private State state;
	private GameInput.Movement facing;

	private int x;
	private int y;
	private int speedX = 5;
	private int speedY = 5;

	public Player(Renderable sprite) {
		this.x = 0;
		this.y = 0;
		this.renderable = sprite;
		this.facing = GameInput.Movement.RIGHT;
		setReturnRenderable();
	}

	@Override
	public Renderable getRenderable() {
		// TODO Auto-generated method stub
		return renderable;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return state;
	}

	private void setReturnRenderable() {
		if (state == State.DEAD) {

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
		this.renderable.setX(x % GameState.DIMENSION.width);
	}

	public void setY(int y) {
		this.y = y;
		this.renderable.setY(y % GameState.DIMENSION.height);
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
}
