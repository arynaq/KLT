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
		if (newX >= 0 && newX <= 2047) {
			player.setX(oldX + direction.getDX()
					* (player.getSpeedX()));
		}

		if (newY >= 0 && newY <= 2047) {
			player.setY(oldY + direction.getDY()
					* (player.getSpeedY()));
		}
		


		((Animated) player.getRenderable()).move(5);

		int i = player.getY() / GameState.getInstance().getFrameHeight();
		int j = player.getX() / GameState.getInstance().getFrameHeight();

		player.setFacing(direction);
		GameState.getInstance().getWorldMap().updateGameMap(i, j);

	}

	public void interact() {
		// //Player playerTwo = new Player(GraphicsEngine.getSprite("player"));
		// double theta = 2 * Math.PI * Math.random();
		// int x = (int) (100 * Math.cos(theta) + player.getX());
		// int y = (int) (100 * Math.sin(theta) + player.getY());
		// playerTwo.setX(x);
		// playerTwo.setY(y);
		// entities.put(x + "" + y, playerTwo);
		//
		// for (String key : entities.keySet()) {
		// if (!key.equals("player")) {
		// Player p = (Player) entities.get(key);
		// if (p.getY() > GameState.getInstance().getFrameHeight()) {
		// entities.remove(key);
		// continue;
		// }
		// p.setY((int) (p.getY() + 20));
		// }
		// }
		// System.out.println(entities.size());
	}

	public void testGameOver() {
		GameState.getInstance().setState(GameCondition.GAMEOVER);

	}

	public void testPauseMovement() {
		((Animated) player.getRenderable()).pause();
	}

	public void addCollisionMap(CollisionMap collisionMap) {
		this.collisionMap = collisionMap;
	}
}
