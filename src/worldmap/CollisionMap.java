package worldmap;

import java.awt.image.BufferedImage;

import engine.GameInput.Movement;

public class CollisionMap {
	private BufferedImage collisionMap;

	public CollisionMap(BufferedImage collisionMap) {
		this.collisionMap = collisionMap;
	}

	public boolean isWalkable(Movement direction, int x, int y) {
		int midX = x + direction.getDX() * 5;
		int midY = y + direction.getDY() * 5;
		if (collisionMap.getRGB(midX, midY) == -1) {
			return true;
		}
		return false;
	}
}
