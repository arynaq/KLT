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
	private DamageEngine damageEngine;
	private ScrollingTextXP dmgSCT;

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

	public void dealDmg(int dmg) {
		if (player.getHealth() <= 0) {
			System.out.println("Player is dead!");
		} else {
			player.setHealth(player.getHealth() - dmg);
		}
		int textX = player.getX() % GameState.getInstance().DIMENSION.width;
		int textY = player.getY() % GameState.getInstance().DIMENSION.height;
		dmgSCT.setX(textX);
		dmgSCT.setY(textY);
		((ScrollingTextXP) dmgSCT).changeString("-" + dmg + "HP");
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

	public void setDamageSCT(ScrollingTextXP SCT){
		this.dmgSCT = SCT;
	}
}
