package characters;

import engine.GameInput.Movement;

public abstract class GameCharacter {
	private int x;
	private int y;
	private Movement facing;

	/**
	 * Returns the cardinal direction this character faces.
	 * 
	 * @return
	 */
	public Movement getFacing() {
		return facing;
	}

	/**
	 * Sets which cardinal direction this character faces.
	 * 
	 * @param facing
	 */
	public void setFacing(Movement facing) {
		this.facing = facing;
	}
	/**
	 * Returns the x-position of the GameCharacter on the global(worldmap)
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y-position of the GameCharacter on the global(world) map.
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the characters movement-speed in the x-direction.
	 * 
	 * @return
	 */
	public abstract int getSpeedX();

	/**
	 * Returns the characters movement-speed in the y-direction.
	 * 
	 * @return
	 */
	public abstract int getSpeedY();

	/**
	 * Returns the width of the character. The characters sprites image-width is
	 * used. This method is helpful for determining collisions.
	 * 
	 * @return
	 */
	public abstract int getWidth();

	/**
	 * Returns the height of the character. The characters sprites image-height
	 * is used. This method is helpful for determining collisions.
	 * 
	 * @return
	 */
	public abstract int getHeight();

}
