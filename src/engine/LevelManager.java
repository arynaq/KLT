package engine;

import gfx.ScrollingCombatText;
import items.Potion;

import java.util.ArrayList;

import characters.Level;
import characters.Player;

public class LevelManager {
    private ArrayList<Level> levels;
    private Player player;
    private ScrollingCombatText scrollingXpText;
    private DamageEngine damageEngine;
    private ScrollingCombatText dmgSCT;
    private ScrollingCombatText levelUpSCT;
    private ScrollingCombatText hpSCT;
    private SoundEngine soundEngine;

    /**
     * Constructor for LevelManager
     * 
     * @param player
     * @param scrollingXPText
     */
    public LevelManager(Player player, ScrollingCombatText scrollingXPText,
            SoundEngine soundEngine) {
        this.player = player;
        this.scrollingXpText = scrollingXPText;
        this.soundEngine = soundEngine;
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
            levels.add(new Level(i * 10, reqXP, (i * 2) - 2, i - 1));
            System.out.println("Required xp for lvl " + i + " is: " + reqXP);
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
        int textX = player.getX() % GameState.getInstance().DIMENSION.width;
        int textY = player.getY() % GameState.getInstance().DIMENSION.height;
        scrollingXpText.setX(textX);
        scrollingXpText.setY(textY);
        ((ScrollingCombatText) scrollingXpText).changeString("+" + xpGain
                + "XP");
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
                + player.getLevels().getLevel() + "!");
    }

    public void setDamageSCT(ScrollingCombatText SCT) {
        this.dmgSCT = SCT;
    }

    public void setLevelUpSCT(ScrollingCombatText SCT) {
        this.levelUpSCT = SCT;
    }

    public void setHpSCT(ScrollingCombatText SCT) {
        this.hpSCT = SCT;
    }
}
