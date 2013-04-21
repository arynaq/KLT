package characters;

import engine.GameInput.Movement;
import engine.SpriteBoundBox;

/**
 * An abstract baseclass for our game. All of our combatables implement this. We
 * are free to add combatables that are not GameCharachters but the combatables
 * in our game have so much in common.
 * 
 * @author aryann
 * 
 */
public abstract class GameCharacter implements Combatable {
	private int x;
	private int y;
	private int width;
	private int height;
	private Movement facing;
	private State state;
	private SpriteBoundBox spriteBox;

	/**
	 * Returns the cardinal direction this character faces.
	 * 
	 * @return
	 */
	public Movement getFacing() {
		return facing;
	}

    @Override
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
	 * Returns the width of the character. The characters sprites image-width
	 * should be used. This method is helpful for determining collisions.
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the character. The characters sprites image-height
	 * should be used. This method is helpful for determining collisions.
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

	public SpriteBoundBox getSpriteBox() {
		return spriteBox;
	}

	protected void setSpriteBox(SpriteBoundBox spriteBox) {
		this.spriteBox = spriteBox;
	}
}
