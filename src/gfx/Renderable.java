package gfx;

import java.awt.Graphics2D;

public interface Renderable {
	/**
	 * For use with static renders, eg something that is always on a frame
	 * Regardless of framereate
	 * 
	 * @param g
	 */
	public void render(Graphics2D g);

	/**
	 * For use with timedependant rendering (like sprites)
	 * 
	 * @param g
	 * @param deltaTime
	 */
	public void render(Graphics2D g, int deltaTime);

	/**
	 * Change the (or set) the position of the renderable on the canvas
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y);
}
