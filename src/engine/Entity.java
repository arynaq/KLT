package engine;

import engine.GameInput.Movement;
import gfx.Renderable;

/**
 * Interface to be used to implement entities that have behavior that does not
 * fit in the combatable subinterface, such as questgiver.
 * 
 * @author aryann
 * 
 */
public interface Entity {

	public Renderable getRenderable();

	public int getX();

	public int getY();

    public void setX(int x);

    public void setY(int y);

	public int getWidth();

	public int getHeight();

	public State getState();

	public enum State {
		ALIVE, DEAD
	}

	public Movement getFacing();

    public void setFacing(Movement facing);

}
