package engine;

import gfx.Renderable;
import gfx.ScrollingTextXP;

import java.util.ArrayList;

import characters.Player;

public class LevelManager {
	private ArrayList<Level> levels;
	private Player player;
	private Renderable scrollingXpText;
	private int playerLevel;
	private int xpChange;
	private int xpPrint;
	private DamageEngine damageEngine;

	public LevelManager(Player player, Renderable scrollingXPText) {
		this.player = player;
		this.scrollingXpText = scrollingXPText;
		simpleLevels();
		initLevel();
	}

	public void initLevel() {
		playerLevel = 1;
		player.setXP(0);
		player.setHealth(levels.get(playerLevel - 1).getHP());
		player.setMaxHealth(levels.get(playerLevel - 1).getHP());
	}

	public void simpleLevels() {
		levels = new ArrayList<Level>();
		for (int i = 1; i <= 100; i++) {
			levels.add(new Level(i * 10, i * 100, i));
		}
	}

	public void xpGain(int xpGain) {
		damageEngine = new DamageEngine();
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				damageEngine.calculateDamage(i);
			}
			// System.out.println("Dmg: " + i);
		}
		player.setXP(xpGain);
		xpChange += 1;
		if (player.getXP() >= levels.get(playerLevel - 1).getXP()) {
			levelUp();
			System.out.println("Level " + playerLevel + "!");
		}

		int textX = player.getX()% GameState.getInstance().DIMENSION.width;
		int textY = player.getY() % GameState.getInstance().DIMENSION.height;
		scrollingXpText.setX(textX);
		scrollingXpText.setY(textY);
		((ScrollingTextXP) scrollingXpText).changeString("+" + xpGain + "XP");

	}

	public void levelUp() {
		player.setHealth(levels.get(playerLevel).getHP());
		player.setMaxHealth(levels.get(playerLevel).getHP());
		player.setDmg(levels.get(playerLevel).getDmg());
		playerLevel += 1;
	}

	public boolean hasXPChanged() {
		if (xpChange > xpPrint) {
			return true;
		} else {
			return false;
		}
	}

	public void setxpPrint() {
		xpPrint += 1;
	}

	class Level {
		private int hp;
		private int reqXP;
		private int dmg;

		public Level(int hp, int reqXP, int dmg) {
			this.hp = hp;
			this.reqXP = reqXP;
			this.dmg = dmg;
		}

		public int getHP() {
			return hp;
		}

		public int getXP() {
			return reqXP;
		}

		public int getDmg() {
			return dmg;
		}
	}
}
