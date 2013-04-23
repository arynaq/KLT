package engine;

import gfx.ScrollingCombatText;

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

    public CombatManager(Player player, GameEngine gameEngine) {
        this.player = player;
        this.engine = gameEngine;
        this.enemySCT = (ScrollingCombatText) engine.getRenderables().get(
                "enemySCT");
        this.playerSCT = (ScrollingCombatText) engine.getRenderables().get(
                "playerSCT");

    }


    /**
     * The player attacks if his cooldown is ready.
     */
    public void playerAttack() {
        if (player.isReadyToAttack()) {
            playerAttackCombatable();
        }
    }

    private void playerAttackCombatable() {
        for (String key : engine.getEntities().keySet()) {
            if (key.equals("player"))
                continue;
            Entity e = engine.getEntities().get(key);
            if (!(e instanceof Combatable))
                continue;
            Combatable c = (Combatable) e;

            if (!(GameState.getInstance().isInCurrentMap(c))) {
                //
                // System.out.println("The current combatable " + c
                // + " is not in screen, skipping.");
                continue;
            }

            else {
                // System.out.println("The current combatable " + c
                // + " is in screen, checking combat");
            }

            if (player.getAttackBounds().intersects(c.getBounds())) {
                // System.out.println("Enemy " + c +
                // " within range, attacking");
                player.attack(c);
                playerSCT.changeString(player.getDamage() + "");
                playerSCT.setX(c.getX());
                playerSCT.setY(c.getY());
                player.doSomethingToOtherOnAttack(c);
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

    public void updateCombatables() {
        // combatablesAttackPlayer();
        combatablesSearchPlayer();

    }

    private void combatablesSearchPlayer() {
        for (String key : engine.getEntities().keySet()) {
            if (key.equals("player"))
                continue;
            Entity e = engine.getEntities().get(key);
            Combatable c = (Combatable) e;
            c.seek(player);
        }
    }

    private void combatablesAttackPlayer() {
        for (String key : engine.getEntities().keySet()) {
            if (key.equals("player"))
                continue;
            Entity e = engine.getEntities().get(key);
            if (!GameState.getInstance().isInCurrentMap(e))
                continue;

            if (!(e instanceof Combatable))
                continue;

            Combatable c = (Combatable) e;

            if (c.isReadyToAttack()) {
                if (c.getAttackBounds().intersects(player.getBounds())) {
                    c.attack(player);
                    enemySCT.changeString("" + 10);
                    enemySCT.setX(player.getX());
                    enemySCT.setY(player.getY());
                    c.doSomethingToOtherOnAttack(player);
                }
            }

        }

    }

}
