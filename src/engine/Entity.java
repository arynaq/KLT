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

    /**
     * Returns this entitys renderable. In our game this is used in the
     * rendering loop to get every entities animation (if they have one).
     * 
     * @return
     */
	public Renderable getRenderable();

    /**
     * Get this entitys x-position. The global worldmap is used. The upper-left
     * corner is 0.
     * 
     * @return
     */
	public int getX();

    /**
     * Get this entities y-position. The global worldmap is used. The upper-left
     * corner is 0.
     * 
     * @return
     */
	public int getY();

    /**
     * Set this entitys xposition
     * 
     * @param x
     */
    public void setX(int x);

    /**
     * Set this entitys y-position
     * 
     * @param y
     */
    public void setY(int y);

    /**
     * Get this entitys width. Used for collisiondetection among others.
     * 
     * @return
     */
	public int getWidth();

    /**
     * Get this entitys height.
     * 
     * @return
     */
	public int getHeight();

    /**
     * Returns whether this entity is dead or alive.
     * 
     * @return
     */
	public State getState();

    /**
     * Returns the cardinal direction this entity is facing.
     * 
     * @return
     */
	public Movement getFacing();

    /**
     * Sets the cardinal direction this entity faces.
     * 
     * @param facing
     */
    public void setFacing(Movement facing);

    /**
     * Two-state constant deciding whether something is dead or alive.
     * 
     * @author arynaq
     * 
     */
    public enum State {
        ALIVE, DEAD
    }

}
