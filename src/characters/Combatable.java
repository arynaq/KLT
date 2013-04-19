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
public interface Combatable extends Entity {

	/**
	 * Attacks the other Combatable entity.
	 * 
	 * @param other
	 */
	public void attack(Combatable other);

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
	 * Combatables attack. In our game this rectangle is cast from the players
	 * center in the direction he is facing.
	 * 
	 * @return
	 */
	public Rectangle getAttackBounds();

	public int getAttackRange();
}
