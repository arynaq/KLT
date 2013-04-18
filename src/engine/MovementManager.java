package engine;

import gfx.Animated;

import java.util.Map;

import worldmap.CollisionMap;
import characters.Player;

public class MovementManager {
	private Map<String, Entity> entities;
	private Player player;
	private CollisionMap collisionMap;

	public MovementManager(Map<String, Entity> entities) {
		this.entities = entities;
		this.player = (Player) entities.get("player");
	}
	
	public void movePlayer(GameInput.Movement direction) {

		if (this.player == null) {
			this.player = (Player) entities.get("player");
		}
		int oldX = player.getX();
		int oldY = player.getY();
		int newX = oldX + direction.getDX() * player.getSpeedX();
		int newY = oldY + direction.getDY() * player.getSpeedY();
		if (!collisionMap.isWalkable(player, direction)) {
			player.setFacing(direction);
			return;
		}
		if (newX >= 0 && newX <= GameState.WORLDBOUNDS.width) {
			player.setX(oldX + direction.getDX()
					* (player.getSpeedX()));
		}

		if (newY >= 0 && newY <= GameState.WORLDBOUNDS.height) {
			player.setY(oldY + direction.getDY()
					* (player.getSpeedY()));
		}
		


		((Animated) player.getRenderable()).move(5);

		player.setFacing(direction);

	}

	public void interact() {
	}

	public void testGameOver() {
		GameState.getInstance().setState(GameCondition.GAMEOVER);

	}

	public void testPauseMovement() {
	}

	public void addCollisionMap(CollisionMap collisionMap) {
		this.collisionMap = collisionMap;
	}
}
