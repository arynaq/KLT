package characters;

import engine.AttackBoundBox;
import engine.GameInput.Movement;
import engine.GameState;
import engine.SpriteBoundBox;
import gfx.AttackMoveAnimated;
import gfx.Renderable;
import items.Potion;

import java.awt.Rectangle;
import java.util.ArrayList;

import worldmap.CollisionMap;

/**
 * This class holds the player, multiple players allowed but only the first will
 * respond to input in our game.
 * 
 * @author arynaq
 * 
 */

public class Player extends GameCharacter {

    private Renderable fullSheet;
    private Renderable moveSouthSheet;
    private Renderable moveWestSheet;
    private Renderable moveEastSheet;
    private Renderable moveNorthSheet;
    private Renderable attackSouthSheet;
    private Renderable attackWestSheet;
    private Renderable attackEastSheet;
    private Renderable attackNorthSheet;
    private Renderable currentRenderable;

    private ArrayList<Potion> HealthPotions = new ArrayList<Potion>();
    private int speedX = 2;
    private int speedY = 2;
    private int health;
    private int maxHealth;
    private int xp;
    private int attackRange = 20;
    private int attackCooldown = 600;
    private long t0, timer;
    private AttackBoundBox attackBox;
    private Level playerLevel;

    /**
     * The player is initialized with its sprite represented by the renderable.
     * 
     * @param renderable
     */
    public Player(Renderable renderable) {
        super.setX(120);
        super.setY(340);
        this.fullSheet = renderable;
        super.setFacing(Movement.RIGHT);
        super.setState(State.ALIVE);
    }

    /**
     * The player is initialized with its sprite being fully animated. This is
     * the player we use in the game.
     * 
     * @param attackMoveAnimated
     */
    public Player(AttackMoveAnimated attackMoveAnimated) {
        super.setX(120);
        super.setY(340);

        this.fullSheet = attackMoveAnimated;
        this.moveSouthSheet = attackMoveAnimated.getSouthMovementSheet();
        this.moveWestSheet = attackMoveAnimated.getWestMovementSheet();
        this.moveEastSheet = attackMoveAnimated.getEastMovementSheet();
        this.moveNorthSheet = attackMoveAnimated.getNorthMovementSheet();

        super.setWidth(attackMoveAnimated.getSheeet().getImages().get(0)
                .getWidth());
        super.setHeight(attackMoveAnimated.getSheeet().getImages().get(0)
                .getHeight());
        super.setFacing(Movement.RIGHT);
        super.setState(State.ALIVE);
        super.setSpriteBox(new SpriteBoundBox(this));
        this.attackBox = new AttackBoundBox(this);
        this.playerLevel = new Level(1);
    }

    @Override
    public Renderable getRenderable() {
        if (getFacing() == Movement.RIGHT) {
            currentRenderable = moveEastSheet;
        }

        else if (getFacing() == Movement.LEFT) {
            currentRenderable = moveWestSheet;
        }

        else if (getFacing() == Movement.UP) {
            currentRenderable = moveNorthSheet;
        }

        else if (getFacing() == Movement.DOWN) {
            currentRenderable = moveSouthSheet;
        }

        updateCurrentRenderable();
        return currentRenderable;
    }

    private void updateCurrentRenderable() {
        currentRenderable.setX(getX() % GameState.DIMENSION.width);
        currentRenderable.setY(getY() % GameState.DIMENSION.height);

    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int newMaxHealth) {
        maxHealth = newMaxHealth;
    }

    // Bruker HealthPotion for now
    public void usePotion(char potionType) {
        if (HealthPotions.size() > 0 && health < maxHealth) {
            if ((maxHealth - health) < HealthPotions.get(0).getValue()) {
                health = maxHealth;
                HealthPotions.remove(0);
            } else {
                health += HealthPotions.get(0).getValue();
                HealthPotions.remove(0);
            }
        }

    }

    public int getNumPotions() {
        return HealthPotions.size();
    }

    public void setXP(int xpGain) {
        xp += xpGain;
    }

    public int getXP() {
        return xp;
    }

    @Override
    public void attack(Combatable other) {
        other.getAttacked(getDamage());
    }

    @Override
    public void seek(Combatable other) {
        // TODO Auto-generated method stub

    }

    @Override
    public void seek(Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getAttacked(int damage) {
        this.health -= damage;
    }

    public int getAttackRange() {
        return attackRange;
    }

    @Override
    public Rectangle getAttackBounds() {
        return attackBox.getRectangle();
    }

    public AttackBoundBox getAttackBox() {
        return attackBox;
    }

    @Override
    public Rectangle getBounds() {
        return super.getSpriteBox().getRectangle();
    }

    /**
     * The player is unique
     */
    @Override
    public int compareTo(Combatable o) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Pushes the other combatable we are attacking. By default the other
     * combatable is pushed his full width/height.$Devnote: We could abstract
     * this method in a base combatclass since every combatable in our game just
     * pushes a target back if they are attacked but we have some cool futures
     * in mind, like a special attack that blows the enemy out of the screen,
     * oneshotting it. We want this to be unique. TODO Do that.$
     */
    @Override
    public void doSomethingToOtherOnAttack(Combatable other) {
        int w = other.getWidth();
        int h = other.getHeight();
        int x = 0;
        int y = 0;
        int dx = this.getFacing().getDX();
        int dy = this.getFacing().getDY();
        while (x <= w) {
            if (other.getX() <= 0
                    || other.getX() + w >= GameState.DIMENSION.width - 1) {
                break;
            }
            if (!CollisionMap
                    .isWalkable(other, other.getX() + dx, other.getY())) {
                break;
            }
            other.setX(other.getX() + dx);
            x++;
        }

        while (y <= h) {
            if (other.getY() <= 0
                    || other.getY() + h >= GameState.DIMENSION.width - 1)
                break;
            if (!CollisionMap
                    .isWalkable(other, other.getX(), other.getY() + dy)) {
                break;
            }
            other.setY(other.getY() + dy);
            y++;
        }

    }

    @Override
    public boolean isReadyToAttack() {
        boolean ret;
        long delta = System.currentTimeMillis() - t0;
        timer += delta;
        if (timer > attackCooldown) {
            timer = 0;
            ret = true;
        } else {
            ret = false;
        }
        t0 = System.currentTimeMillis();
        return ret;
    }

    @Override
    public int getDamage() {
        return playerLevel.getDmg();
    }

    public void setDmg(int dmg) {
    }

    public void setLevels(Level level) {
        playerLevel = level;
        health = level.getHP();
        maxHealth = level.getHP();
    }

    public void givePotion(Potion potion) {
        HealthPotions.add(potion);
    }

    public Level getLevels() {
        return playerLevel;
    }

    public int getPotionsSize() {
        return HealthPotions.size();
    }

}
