package engine;

import gfx.Renderable;
import gfx.ScrollingCombatText;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import characters.Combatable;
import characters.Player;

public class CombatManager {
    private Player player;
    private GameEngine engine;
    private ScrollingCombatText dmgSCT;
    private BoundRect boundRect;

    public CombatManager(Player player, Renderable renderable,
            GameEngine gameEngine) {
        this.player = player;
        this.engine = gameEngine;
        this.dmgSCT = (ScrollingCombatText) renderable;
        this.boundRect = new BoundRect(0, 0, 0, 0);

    }

    /**
     * Enables visual bounding boxes for combat, these are drawn with red
     * outlines.
     */
    public void enableVisualTesting() {
        setUpVisualTestOfCombatRange();
    }

    /**
     * For testing only. Draws a rectangle in the direction the player is facing
     * and attacks anything within that rectangle.
     */
    private void setUpVisualTestOfCombatRange() {
        engine.getRenderables().put("attackRectanglePlayer", boundRect);
    }

    /**
     * The player attacks
     */
    public void attackPlayer() {
        Combatable c = nearestToPlayer();

    }

    private Combatable nearestToPlayer() {
        Player player = engine.getPlayer();
        for (String key: engine.getEntities().keySet()){
            if (key.equals("player"))
                continue;
            Entity e = engine.getEntities().get(key);

            if (!(e instanceof Combatable))
                continue;
            Combatable c = (Combatable) e;
            if (!player.getAttackBounds().intersects(c.getAttackBounds())) {
                System.out.println("Cant reach");
            }
 else if (player.getAttackBounds().intersects(c.getAttackBounds())) {
                System.out.println("I can reach other target");
                System.out.println("My rectangle: " + player.getAttackBounds());
                System.out.println("Other rectangle: " + c.getAttackBounds());
                player.attack(c);
                c.attack(player);
            }

        }
        return null;
    }

    /**
     * Sets up a bounding rectangle. Can be drawn to show the bounding boxes for
     * collisiontesting.
     * 
     * @author aryann
     * 
     */
    class BoundRect implements Renderable {
        private Rectangle rectangle;

        public BoundRect(int x, int y, int width, int height) {
            this.rectangle = new Rectangle(x, y, width, height);
        }

        @Override
        public void render(Graphics2D g) {
            g.setColor(Color.red);
            g.drawRect(getX(), getY(), getWidth(), getHeight());
        }

        @Override
        public void render(Graphics2D g, int deltaTime) {
        }

        @Override
        public void setX(int x) {
            rectangle.setLocation(x, getY());
        }

        @Override
        public void setY(int y) {
            rectangle.setLocation(getX(), y);
        }

        public void setWidth(int width) {
            rectangle.setBounds(getX(), getY(), width, getHeight());
        }

        public void setHeight(int height) {
            rectangle.setBounds(getX(), getY(), getWidth(), height);
        }

        public int getWidth() {
            return rectangle.width;
        }

        public int getHeight() {
            return rectangle.height;
        }

        public int getX() {
            return rectangle.x;
        }

        public int getY() {
            return rectangle.y;
        }

    }

}
