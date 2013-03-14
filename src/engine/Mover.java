package engine;

import characters.GameCharacter;
import characters.MapObject;
import characters.Player;

/***
 * Contains the methods used to move an object around the map the abastract and
 * static keywords mean we don't need an instance of this class
 * 
 * @author Maki
 * 
 */
public abstract class Mover {
	

	/**
	 * Playermovement might be special, he might move at a different speed at
	 * the worldmap, at a higher framerate or other special crap
	 * 
	 * @param player
	 */
	public static void moveCharacter(Player player, int dx, int dy) {
		// If a player is mounted he has double the speed, for now
		if (player.isMounted()) {
			dx *= 2;
			dy *= 2;
		}

		MapObject characterNeighbor = Collision.getNeighbor(player, dx, dy);
		player.setX(player.getX() + dx);
		player.setY(player.getY() + dy);
		

	}
	
	/**
	 * Placeholder for character-mover
	 * 
	 * @param gc
	 * @param dy
	 * @param dx
	 */
	public static void moveCharacter(GameCharacter gc, int dx, int dy) {
		

	}

	public static void main(String[] args) {
		Player terje = new Player("Terje");
	}

}
