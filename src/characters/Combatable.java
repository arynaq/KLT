package characters;

import java.awt.Rectangle;

import engine.Entity;

/**
 * All attackers and entities that get attacked must implement this interface.
 * All implementors of combatable must also implement entity. That is a
 * convinience design.
 * 
 * @author aryann
 * 
 */
public interface Combatable extends Entity, Comparable<Combatable> {

    /**
     * Attacks the other combatable, dealing the specified damage.
     * 
     * @param other
     * @param dmg
     */
    public void attack(Combatable other, int dmg);

    /**
     * Seeks the other Combatable. A* algo is implemented in our game.
     * 
     * @param other
     */
    public void seek(Combatable other);

    /**
     * Seeks the player. A* is implemented in our game.
     * 
     * @param player
     */
    public void seek(Player player);

    /**
     * Take damage.
     * 
     * @param damage
     */
    public void getAttacked(int damage);

    /**
     * Returns a rectangle object that defines the boundaries of this
     * Combatables attack. In our game, for the player, this rectangle is cast
     * from the players center in the direction he is facing.
     * 
     * @return
     */
    public Rectangle getAttackBounds();

    /**
     * Returns a rectangle object that defines the boundaries of this
     * combatable. In our game, this is used to determine whether an attack is
     * out of range or not.
     * 
     * @return
     */
    public Rectangle getBounds();

    /**
     * Get the attackrange of this Combatable.
     * 
     * @return
     */
    public int getAttackRange();

    /**
     * Check whether this combatable is ready to attack. Usually we implement a
     * cooldown, see GameCharacter.
     * 
     * @return
     */
    public boolean isReadyToAttack();

    /**
     * Do something doable with the other combatable when you attack it. In our
     * game the attackers push eachother their full width when they are
     * attacked.
     * 
     * @param other
     */
    public void doSomethingToOtherOnAttack(Combatable other);

    /**
     * Returns the damage this combatable can inflict. Implementation up to
     * user. The playerclass has a variable damage that is decided by its
     * current level.
     * 
     * @return
     */
    public int getDamage();

    /**
     * Method for resetting the combatable.
     */
    public void reset();

    /**
     * Returns the XP this combatable rewards on death.
     * 
     * @return
     */
    public int getXP();

}
