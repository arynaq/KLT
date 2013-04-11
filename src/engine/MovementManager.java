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
		int x = player.getX() + 20;
		int y = player.getY() + 30;
		playerTwo.setX(x);
		playerTwo.setY(y);
		entities.put(x + "" + y, playerTwo);

		for (String key : entities.keySet()) {
			if (!key.equals("player")) {
				Player p = (Player) entities.get(key);
				p.setY(p.getY() + 10);
			}
		}
		System.out.println(entities.size());
	}
}
