package engine;

import gfx.Animated;

import java.util.Map;

import worldmap.CollisionMap;
import characters.Player;

public class MovementManager {
	private Map<String, Entity> entities;
	private Player player;
	private LevelManager levelManager;
	private CollisionMap collisionMap;
	private GameEngine engine;

	public MovementManager(Map<String, Entity> entities) {
		this.entities = entities;
		this.player = (Player) entities.get("player");
	}

	public MovementManager(GameEngine engine) {
		this.engine = engine;
		this.player = engine.getPlayer();
		this.levelManager = engine.getLevelManager();
	}

	public void movePlayer(GameInput.Movement direction) {
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
			// player.setWalking(true);
		}
		if (newY >= 0 && newY <= 2047) {
			player.setY(oldY + direction.getDY()
					* (player.getSpeedY()));
			// player.setWalking(true);
		}
		int i = player.getY() / GameState.getInstance().getFrameHeight();
		int j = player.getX() / GameState.getInstance().getFrameHeight();
		((Animated) player.getRenderable()).move(5);
		player.setFacing(direction);
	}

	public void interact() {

	}

	public void testGameOver() {
		GameState.getInstance().setState(GameCondition.GAMEOVER);
	}

	// Deals dmg to player
	public void testPlayerDamage() {
		levelManager.dealDmg(10);
	}

	// Player uses potion
	public void usePotion() {
		if (player.getPotions().size() > 0) {
			levelManager.usePotion(player.getPotions().get(0));
		}
	}

	// Player gets new potion
	public void givePotion() {
		// int potionvalue = 10;
		player.givePotion(new Potion('h', 10));
	}

	public void giveXp() {
		levelManager.xpGain(20);
	}

	public void resumeGame() {
		GameState.getInstance().setState(GameCondition.RUNNING);
	}

	public void pauseGame() {
		GameState.getInstance().setState(GameCondition.PAUSED);
	}

	public void addCollisionMap(CollisionMap collisionMap) {
		this.collisionMap = collisionMap;
	}

	public void tellEngineToPlayXpSound() {

	}
}