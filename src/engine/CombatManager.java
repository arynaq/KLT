package engine;

import engine.Entity.State;
import gfx.ScrollingCombatText;
import items.Potion;

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
    private DamageEngine dmgEngine;
    private LevelManager levelManager;

    public CombatManager(Player player, GameEngine gameEngine) {
        this.player = player;
        this.engine = gameEngine;
        this.enemySCT = (ScrollingCombatText) engine.getRenderables().get(
                "enemySCT");
        this.playerSCT = (ScrollingCombatText) engine.getRenderables().get(
                "playerSCT");
        this.dmgEngine = new DamageEngine();
        this.levelManager = engine.getLevelManager();
    }


    /**
     * The player attacks if his cooldown is ready.
     */
    public void playerAttack() {
        if (!player.isReadyToAttack())
            return;
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
                player.attack(combatable, dmg);
                player.doSomethingToOtherOnAttack(combatable);
                enemySCT.changeString("RAPED: " + dmg);
                enemySCT.setX(combatable.getX());
                enemySCT.setY(combatable.getY());

                if (combatable.getState() == State.DEAD) {
                    levelManager.xpGain(50);
                    rollTheDiceAndGivePlayerLoot();
                }
            }

        }
    }

    private void rollTheDiceAndGivePlayerLoot() {
        if (Math.random() > 0.9) {
            player.givePotion(new Potion('h', player.getMaxHealth()));
        }
    }

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

    private void combatablesAttackPlayer() {
        for (String key : engine.getEntities().keySet()) {
            if (key.equals("player"))
                continue;
            Entity e = engine.getEntities().get(key);
            if (!(e instanceof Combatable))
                continue;
            Combatable combatable = (Combatable) e;

            if (combatable.isReadyToAttack()) {
                if (!(combatable.getAttackBounds().intersects(player.getBounds())))
                    continue;
                int dmg = dmgEngine.calculateDamage(combatable.getDamage());
                combatable.attack(player, dmg);
                player.getAttacked(dmg);
                combatable.doSomethingToOtherOnAttack(player);
                playerSCT.changeString("-" + dmg + "HP");
                playerSCT.setX(player.getX());
                playerSCT.setY(player.getY());
            }
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
}
