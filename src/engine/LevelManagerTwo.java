package engine;

import characters.Player;

public class LevelManagerTwo {
	private Player player;
	private Level currentLevel;
	private int oldXP, newXP;

	public LevelManagerTwo(Player player) {
		this.player = player;
	}

	public void gainXP() {
		player.setXP(player.getXP() + 10);
		newXP = player.getXP();
		if (player.getXP() >= currentLevel.getXp()) {
			player.setMaxHealth(currentLevel.getHp());
		}
		oldXP = player.getXP();
	}

	class Level {
		int xp;
		int hp;

		public Level(int level) {
			this.xp = level * 100;
			this.hp = level * 10;
		}

		public int getHp() {
			return hp;
		}

		public int getXp() {
			return xp;
		}
	}

}
