package engine;

import java.util.Map;

import characters.Player;

public class MovementManager {
	private Map<String, Entity> entities;
	private Player player;

	public MovementManager(Map<String, Entity> entities) {
		this.entities = entities;
		this.player = (Player) entities.get("player");
	}
	
	public void movePlayer(int dx, int dy) {
		if (this.player == null) {
			this.player = (Player) entities.get("player");
		}
		player.setX(player.getX() + dx * (player.getSpeedX()));
		player.setY(player.getY() + dy * (player.getSpeedY()));
	}

	public void interact() {
		Player playerTwo = new Player(GraphicsEngine.getSprite("player"));
		double theta = 2 * Math.PI * Math.random();
		int x = (int) (100 * Math.cos(theta) + player.getX());
		int y = (int) (100 * Math.sin(theta) + player.getY());
		playerTwo.setX(x);
		playerTwo.setY(y);
		entities.put(x + "" + y, playerTwo);

		for (String key : entities.keySet()) {
			if (!key.equals("player")) {
				Player p = (Player) entities.get(key);
				if (p.getY() > GameState.getInstance().getFrameHeight()) {
					entities.remove(key);
					continue;
				}
				p.setY((int) (p.getY() + 20));
			}
		}
		System.out.println(entities.size());
	}
}
