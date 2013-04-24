package engine;

import engine.Entity.State;
import gfx.ScrollingCombatText;
import items.Potion;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import characters.Combatable;
import characters.Player;

public class CombatManager {
    private Player player;
    private GameEngine engine;
    private ScrollingCombatText enemySCT;
    private ScrollingCombatText playerSCT;
    private ScrollingCombatText lootSCT;
    private DamageEngine dmgEngine;
    private LevelManager levelManager;

    public CombatManager(Player player, GameEngine gameEngine) {
        this.player = player;
        this.engine = gameEngine;
        this.enemySCT = (ScrollingCombatText) engine.getRenderables().get(
                "enemySCT");
        this.playerSCT = (ScrollingCombatText) engine.getRenderables().get(
                "playerSCT");
        this.lootSCT = new ScrollingCombatText("", 0, 0, 14, Color.GREEN);
        this.dmgEngine = new DamageEngine();
        this.levelManager = engine.getLevelManager();
        this.engine.getRenderables().put("lootSCT", lootSCT);
    }


    /**
     * The player attacks if his cooldown is ready.
     */
    public void playerAttack() {
        if (!player.isReadyToAttack())
            return;
        player.setAttacking(true);
        playerAttackCombatable();
    }

    /**
     * Updates the combatables AI, they will search and attack the player if
     * they can reach and find him.
     */
    public void updateCombatables() {
        combatablesSearchPlayer();
        combatablesAttackPlayer();

    }

    private void playerAttackCombatable() {
        for (String key : engine.getEntities().keySet()) {
            if (key.equals("player"))
                continue;

            Entity e = engine.getEntities().get(key);
            if (!(e instanceof Combatable))
                continue;

            Combatable combatable = (Combatable) e;
            if (!(GameState.getInstance().isInCurrentMap(combatable)))
                continue;
            if (combatable.getState() == State.DEAD)
                continue;

            if (player.getAttackBounds().intersects(combatable.getBounds())) {
                int dmg = dmgEngine.calculateDamage(player.getDamage());
                boolean isCrit = dmgEngine.isCrit();
                player.attack(combatable, dmg);
                player.doSomethingToOtherOnAttack(combatable);
                engine.getSoundEngine().playPlayerAttack();

                if(isCrit)
                    enemySCT.changeString("CRITICAL! " + dmg, Color.YELLOW);
                else
                    enemySCT.changeString("Hit: " + dmg, Color.yellow);
                enemySCT.setX(combatable.getX());
                enemySCT.setY(combatable.getY());

                if (combatable.getState() == State.DEAD) {
                    engine.getSoundEngine().playEnemyDead();
                    levelManager.xpGain(50);
                    rollTheDiceAndGivePlayerLoot();
                }
            }

        }
    }

    /**
     * Gives the player loot when he kills an enemy. Just a 10% chance to get a
     * potion for now, one that heals relative to the level. Quite overpowered,
     * we know.
     */
    private void rollTheDiceAndGivePlayerLoot() {
        if (Math.random() > 0.9) {
            player.givePotion(new Potion('h', player.getMaxHealth() / 2));
            lootSCT.changeString("Gained a potion!", Color.green);
        }
    }

    /**
     * Let all of the combatables try and get closer to the player.
     */
    private void combatablesSearchPlayer() {
        for (String key : engine.getEntities().keySet()) {
            if (key.equals("player"))
                continue;
            Entity entity = engine.getEntities().get(key);
            if (!(entity instanceof Combatable))
                continue;

            Combatable combatable = (Combatable) entity;
            if (!(GameState.getInstance().isInCurrentMap(combatable))) {
                combatable.reset();
                continue;
            }
            if (combatable.getState() == State.DEAD)
                continue;

            combatable.seek(player);

        }
    }

    /**
     * Let combatables attack the player, if they are able and within range.
     */
    private void combatablesAttackPlayer() {
        for (String key : engine.getEntities().keySet()) {

            if (key.equals("player"))
                continue;
            Entity e = engine.getEntities().get(key);
            if (!(e instanceof Combatable))
                continue;

            Combatable combatable = (Combatable) e;
            if (combatable.getState() == State.DEAD)
                continue;
            if (!combatable.isReadyToAttack())
                continue;
            if (!(combatable.getAttackBounds().intersects(player.getBounds())))
                continue;

            int dmg = dmgEngine.calculateDamage(combatable.getDamage());
            combatable.attack(player, dmg);
            player.getAttacked(dmg);
            combatable.doSomethingToOtherOnAttack(player);
            playerSCT.changeString("-" + dmg + "HP", Color.red);
            playerSCT.setX(player.getX());
            playerSCT.setY(player.getY());
            engine.getSoundEngine().playEnemyAttack();

        }

    }








    /**
     * The below method returns the Combatable that is nearest to the player, if
     * no such thing can be found (no enemy on current screen) it returns null.
     * 
     * @return Enemy, null if no enemy on screen.
     */
    private Combatable nearestToPlayer() {

        List<Combatable> candidates = new ArrayList<Combatable>();
        for (String key : engine.getEntities().keySet()) {

            if (key.equals("player"))
                continue;

            Entity e = engine.getEntities().get(key);

            if (!(e instanceof Combatable))
                continue;

            if (!(GameState.getInstance().isInCurrentMap(e)))
                continue;

            Combatable c = (Combatable) e;
            candidates.add(c);
        }

        if (candidates.isEmpty()) {
            System.out.println("No enemies found in current map");
            return null;
        }

        Collections.sort(candidates);
        return candidates.get(0);

    }

    public void usePotion() {
        Potion potion = player.usePotion('h');
        if (potion==null)
            return;
        lootSCT.changeString("HEALED: " + potion.getValue(), Color.green);
        lootSCT.setX(player.getX());
        lootSCT.setY(player.getY());
        engine.getSoundEngine().playPotion();
    }
}
