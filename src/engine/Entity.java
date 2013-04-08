package engine;

import gfx.Renderable;

public interface Entity {

	public Renderable getRenderable();

	public int getX();

	public int getY();

	public State getState();

	public enum State {
		ALIVE, DEAD
	}

}
