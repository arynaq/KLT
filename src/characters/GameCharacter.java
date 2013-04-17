package characters;


public abstract class GameCharacter {
	private int x;
	private int y;

	/**
	 * Returns the x-position of the GameCharacter on the global(worldmap)
	 * 
	 * @return
	 */
	public abstract int getX();

	/**
	 * Returns the y-position of the GameCharacter on the global(world) map.
	 * 
	 * @return
	 */
	public abstract int getY();

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
