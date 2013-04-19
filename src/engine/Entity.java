package engine;

import engine.GameInput.Movement;
import gfx.Renderable;

public interface Entity {

	public Renderable getRenderable();

	public int getX();

	public int getY();

	public int getWidth();

	public int getHeight();

	public State getState();

	public enum State {
		ALIVE, DEAD
	}

	public Movement getFacing();

}
