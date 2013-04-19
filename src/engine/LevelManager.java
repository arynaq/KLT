package engine;

import gfx.Renderable;
import gfx.ScrollingCombatText;

import java.util.ArrayList;

import characters.Player;

public class LevelManager {
	private ArrayList<Level> levels;
	private Player player;
	private Renderable scrollingXpText;
	private DamageEngine damageEngine;
	private ScrollingCombatText dmgSCT;
	private ScrollingCombatText levelUpSCT;
	private ScrollingCombatText hpSCT;

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
		player.setLevels(levels.get(1));
		player.setXP(0);
		player.givePotion(new Potion('h', 100));
		// damageEngine = new DamageEngine();
		// for (int i = 1; i <= 100; i++) {
		// for (int j = 1; j <= 10; j++) {
		// damageEngine.calculateDamage(i);
		// }
		// }
	}

	/**
	 * Simple levelGenerator.
	 */
	public void simpleLevels() {
		// experienceFormula = (50x³/3)-100x² + (850x/3.0) - 200)
		levels = new ArrayList<Level>();
		for (int i = 1; i <= 100; i++) {
			int reqXP = (int) Math.round(((50 * (i * i * i)) / 3.0)
					- (100 * (i * i)) + ((850 * i) / 3.0) - 200);
			levels.add(new Level(i * 10, reqXP, i - 10, i - 1));
		}
	}

	/**
	 * Awards the player experience and levels him up accordingly.
	 * 
	 * @param xpGain
	 */
	public void xpGain(int xpGain) {
		player.setXP(xpGain);
		if (player.getXP() >= player.getLevels().getReqXP()) {
			levelUp();
		}
		int textX = player.getX()% GameState.getInstance().DIMENSION.width;
		int textY = player.getY() % GameState.getInstance().DIMENSION.height;
		scrollingXpText.setX(textX);
		scrollingXpText.setY(textY);
		((ScrollingCombatText) scrollingXpText).changeString("+" + xpGain + "XP");
	}

	public void dealDmg(int dmg) {
		player.setHealth(player.getHealth() - dmg);
		if (player.getHealth() <= 0) {
			player.setHealth(0);
			GameState.getInstance().setState(GameCondition.GAMEOVER);
		}
		int textX = player.getX() % GameState.getInstance().DIMENSION.width;
		int textY = player.getY() % GameState.getInstance().DIMENSION.height;
		dmgSCT.setX(textX);
		dmgSCT.setY(textY);
		((ScrollingCombatText) dmgSCT).changeString("-" + dmg + "HP");
	}

	/**
	 * Heals the player the amount of healing from the proper potion
	 * 
	 * @param potion
	 */
	public void usePotion(Potion potion) {
		int textX = player.getX() % GameState.getInstance().DIMENSION.width;
		int textY = player.getY() % GameState.getInstance().DIMENSION.height;
		if (player.getNumPotions() > 0
				&& player.getHealth() < player.getMaxHealth()) {
			hpSCT.setX(textX);
			hpSCT.setY(textY);
			if ((player.getMaxHealth() - player.getHealth()) < player
					.getPotions().get(0).getValue()) {
				((ScrollingCombatText) hpSCT).changeString("+"
						+ (player.getMaxHealth() - player.getHealth()) + "HP");
				player.setHealth(player.getMaxHealth());
				player.removePotion();
			} else {
				player.setHealth(player.getHealth()
						+ player.getPotions().get(0).getValue());
				((ScrollingCombatText) hpSCT).changeString("+"
						+ player.getPotions().get(0).getValue() + "HP");
				player.removePotion();
			}
		}
	}

	/**
	 * Is executed when the player has reached the required XP to level up
	 */
	public void levelUp() {
		int nextlvl = player.getLevels().getLevel() + 1;
		player.setLevels(levels.get(nextlvl));
		player.getLevels().setDmg(levels.get(nextlvl).getDmg());
		player.getLevels().setReqXP(levels.get(nextlvl).getReqXP());
		int textX = player.getX() % GameState.getInstance().DIMENSION.width;
		int textY = player.getY() % GameState.getInstance().DIMENSION.height;
		levelUpSCT.setX(textX - 30);
		levelUpSCT.setY(textY - 20);
		((ScrollingCombatText) levelUpSCT).changeString("LEVEL "
				+ player.getLevels().getLevel()
				+ "!");
	}

	/**
	 * Level class contains hp, required XP for level up and the damage level of
	 * the player.
	 * 
	 * @author frela
	 * 
	 */
	public class Level {
		private int hp;
		private int reqXP;
		private int dmg;
		private int level;

		public Level(int hp, int reqXP, int dmg, int level) {
			this.hp = hp;
			this.reqXP = reqXP;
			this.dmg = dmg;
			this.level = level;
		}
		public int getHP() {
			return hp;
		}

		public void setHP(int hp) {
			this.hp = hp;
		}

		public int getDmg() {
			return dmg;
		}

		public void setDmg(int dmg) {
			this.dmg = dmg;
		}

		public int getReqXP() {
			return reqXP;
		}

		public void setReqXP(int reqXP) {
			this.reqXP = reqXP;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}
	}

	public void setDamageSCT(ScrollingCombatText SCT){
		this.dmgSCT = SCT;
	}

	public void setLevelUpSCT(ScrollingCombatText SCT) {
		this.levelUpSCT = SCT;
	}

	public void setHpSCT(ScrollingCombatText SCT) {
		this.hpSCT = SCT;
	}
}
