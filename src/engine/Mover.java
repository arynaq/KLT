package engine;

import characters.GameCharacter;
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
		player.setX(player.getX() + dx);
		player.setY(player.getY() + dx);
		

	}
	
	/**
	 * Placeholder
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
