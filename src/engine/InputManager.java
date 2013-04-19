package engine;

import java.util.Map;

import worldmap.CollisionMap;
import characters.Player;
import engine.GameInput.Movement;
import gfx.Animated;

public class InputManager {
	private Map<String, Entity> entities;
	private Player player;
	private LevelManager levelManager;
	private CollisionMap collisionMap;
	private GameEngine engine;
	private CombatManager combatManager;

	public InputManager(Map<String, Entity> entities) {
		this.entities = entities;
		this.player = (Player) entities.get("player");
	}

	public InputManager(GameEngine engine) {
		this.engine = engine;
		this.player = engine.getPlayer();
		this.levelManager = engine.getLevelManager();
		this.combatManager = engine.getCombatManager();
		entities = this.engine.getEntities();
	}

	public void movePlayer(Movement direction) {
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

		((Animated) player.getRenderable()).move(GameState.PLAYERSTEPS);
		player.setFacing(direction);

	}

	public void interact() {

	}

	public void testGameOver() {
		GameState.getInstance().setState(GameCondition.GAMEOVER);

	}

	// Player bruker potion
	public void usePotion() {
		player.usePotion('h');
	}

	public void giveXp() {
		levelManager.xpGain(10);
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

	public void attack() {
		combatManager.attackPlayer();
	}
}
