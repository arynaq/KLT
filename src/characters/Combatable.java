package characters;

public interface Combatable {

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

}
