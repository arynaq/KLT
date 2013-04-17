package engine;

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
		if (!collisionMap.isWalkable(direction, player.getX() + 16,
				player.getY() + 32)) {
			player.setFacing(direction);
			return;
		}
		if (newX >= 0 && newX <= 2047) {
			player.setX(oldX + direction.getDX()
					* (player.getSpeedX()));
			player.setWalking(true);
		}

		if (newY >= 0 && newY <= 2047) {
			player.setY(oldY + direction.getDY()
					* (player.getSpeedY()));
			player.setWalking(true);
		}
		int i = player.getY() / GameState.getInstance().getFrameHeight();
		int j = player.getX() / GameState.getInstance().getFrameHeight();

		player.setFacing(direction);

		GameState.getInstance().getWorldMap().updateGameMap(i, j);

		// System.out.println("(FX,FY): " + "(" + player.getFeetX() + ","
		// + player.getFeetY() + ")");
		// System.out.println(collisionMap.isWalkable(direction,
		// player.getFeetX(),
		// player.getFeetY()));
		//
		// System.out.println(player.getX() + "," + player.getY());

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

	// gjør DMG til player
	public void testPlayerDamage(int dmg) {
		if (this.player == null) {
			this.player = (Player) entities.get("player");
		}
		if (player.getHealth() > dmg) {
			player.setHealth(player.getHealth() - dmg);
		} else {
			player.setHealth(0);
		}
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
}
