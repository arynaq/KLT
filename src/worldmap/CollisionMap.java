package worldmap;

import java.awt.image.BufferedImage;

import characters.Combatable;
import characters.GameCharacter;
import engine.Entity;
import engine.GameInput.Movement;

public class CollisionMap {
    private static BufferedImage collisionMap;

	public CollisionMap(BufferedImage collisionMap) {
        CollisionMap.collisionMap = collisionMap;
	}

	/**
	 * Checks whether this character can move in the given direction.
	 * 
	 * @param character
	 * @param direction
	 * @return
	 */
    public static boolean isWalkable(GameCharacter character, Movement direction) {
		int midX = character.getX() + direction.getDX() * character.getSpeedX();
		int midY = character.getY() + direction.getDY() * character.getSpeedY();
		if (collisionMap.getRGB(midX + character.getWidth() / 2 - 2, midY
				+ character.getHeight() - 2) == -1) {
			return true;
		}
		return false;
	}

    public static boolean isWalkable(int x, int y) {
        if (collisionMap.getRGB(x, y) == -1)
            return true;
        return false;
    }

    /**
     * Check if the combatable can walk to the new position given by the new x,y
     * 
     * @param combatable
     * @param dx
     * @param dy
     * @return
     */
    public static boolean isWalkable(Combatable combatable, int x, int y) {
        if (collisionMap.getRGB(x, y) == -1) {
            return true;
        }
        return false;
    }

    public static boolean isWalkableTwo(Entity entity, Movement direction) {
		return false;
	}

	// TO DO : Do the collision pixel for pixel if we find extra time and extra
	// cpu, cba atm, the above looks very nice as it is.
	// /**
	// * Returns the x length this entity is allowed to walk before meeting
	// * blockable. How far this entity is able to go in x before having to
	// stop.
	// * This is preoptimized to return 0 if the player is not moving in the
	// * x-direction (eg will not work for diagonal movement), you can override
	// or
	// * change this if you want diagonal.
	// *
	// * @param entity
	// * @param direction
	// * @return
	// */
	// public int findXEdgeOfBlockable(Entity entity, Movement direction) {
	// if (direction == Movement.UP || direction == Movement.DOWN) {
	// return 0;
	// }
	//
	// int speedX = entity.getSpeedX();
	// int speedY = entity.getSpeedY();
	// int dx = speedX;
	// int retvalue = 0;
	// for (int i = 0; i < dx; i++) {
	// if (isWalkable(direction, x, y)){
	// retvalue++;
	// }
	// }
	// return retvalue;
	// }
	//
	// public int findYEdgeOfBlockable(Entity entity, Movement direction) {
	// return 0;
	// }
}
