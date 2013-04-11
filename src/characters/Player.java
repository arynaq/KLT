package characters;

import engine.Entity;
import gfx.Renderable;

public class Player extends GameCharacter implements Entity {

	private Renderable attackSheet;
	private Renderable movementSheet;
	private Renderable renderable;
	private State state;
	private Facing facing;

	private int x;
	private int y;
	private int speedX = 10;
	private int speedY = 10;

	public Player(Renderable movementSheet) {
		this.x = 0;
		this.y = 0;
		this.attackSheet = null;
		this.movementSheet = movementSheet;
		this.renderable = this.movementSheet;
		this.facing = Facing.EAST;
		renderable.setX(x);
		renderable.setY(y);
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
		if (facing == Facing.EAST) {

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
		this.renderable.setX(x);
	}

	public void setY(int y) {
		this.y = y;
		this.renderable.setY(y);
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}
}
