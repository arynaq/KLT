package engine;

import characters.GameCharacter;
import characters.MapObject;

/**
 * Returns the neighbors of any given mapobject
 * 
 * @author Maki
 * 
 */
public abstract class Collision { 
	public static MapObject getNorth(GameCharacter gc) {
		// ..other stuff, probably shares more local variables and gc-states
		int x = gc.getX();
		int y = gc.getY();
		// ...dostuff before return
		return gc;
	}
	
	public static MapObject getSouth(GameCharacter gc) {
		// ..other stuff, probably shares more local variables and gc-states
		int x = gc.getX();
		int y = gc.getY();
		// ...dostuff before return
		return gc;
	}
	
	public static MapObject getWest(GameCharacter gc) {
		// ..other stuff, probably shares more local variables and gc-states
		int x = gc.getX();
		int y = gc.getY();
		// ...dostuff before return
		return gc;
	}
	
	public static MapObject getEast(GameCharacter gc) {
		// ..other stuff, probably shares more local variables and gc-states
		int x = gc.getX();
		int y = gc.getY();
		// ...dostuff before return
		return gc;
	}
	
	public static MapObject getNeighbor(GameCharacter gc, int dx, int dy) {
		 // Moving east
		if (dx == 1) {
			return getEast(gc);
		}
		// Moving west
		if (dx == -1) {
			return getWest(gc);
		}
		// Moving south
		if (dy == 1) {
			return getSouth(gc);
		}
		// Moving north
		if (dy == -1) {
			return getNorth(gc);
		}
		return gc;

	}

}
