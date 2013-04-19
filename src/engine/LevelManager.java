package engine;

import gfx.Renderable;
import gfx.ScrollingCombatText;

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

	/**
	 * Constructor for LevelManager
	 * 
	 * @param player
	 * @param scrollingXPText
	 */
	public LevelManager(Player player, Renderable scrollingXPText) {
		this.player = player;
		this.scrollingXpText = scrollingXPText;
		simpleLevels();
		initLevel();
	}

	/**
	 * Initiates levelgeneration and sets player variables accordingly
	 */
	public void initLevel() {
		playerLevel = 1;
		player.setXP(0);
		player.setHealth(levels.get(playerLevel - 1).getHP());
		player.setMaxHealth(levels.get(playerLevel - 1).getHP());
	}

	/**
	 * Simple levelGenerator.
	 */
	public void simpleLevels() {
		levels = new ArrayList<Level>();
		for (int i = 1; i <= 100; i++) {
			levels.add(new Level(i * 10, i * 100, i));
		}
	}

	/**
	 * Awards the player experience and levels him up accordingly.
	 * 
	 * @param xpGain
	 */
	public void xpGain(int xpGain) {
		player.setXP(xpGain);
		xpChange += 1;
		if (player.getXP() >= levels.get(playerLevel - 1).getXP()) {
			levelUp();
		}
		int textX = player.getX()% GameState.getInstance().DIMENSION.width;
		int textY = player.getY() % GameState.getInstance().DIMENSION.height;
		scrollingXpText.setX(textX);
		scrollingXpText.setY(textY);
		((ScrollingCombatText) scrollingXpText).changeString("+" + xpGain + "XP");

	}

	/**
	 * Is executed when the player has reached the required XP to level up
	 */
	public void levelUp() {
		player.setHealth(levels.get(playerLevel).getHP());
		player.setMaxHealth(levels.get(playerLevel).getHP());
		player.setDmg(levels.get(playerLevel).getDmg());
		playerLevel += 1;
	}

	/**
	 * Returns true if there has been a change in experience, is used to print
	 * only one SCT.
	 * 
	 * @return
	 */
	public boolean hasXPChanged() {
		if (xpChange > xpPrint) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * A simple set method to increase xpPrinted
	 */
	public void setxpPrint() {
		xpPrint += 1;
	}

	/**
	 * Level class contains hp, required XP for level up and the damage level of
	 * the player.
	 * 
	 * @author frela
	 * 
	 */
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
