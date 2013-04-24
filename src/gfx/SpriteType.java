package gfx;

/**
 * Not used in game, do as you wish with it.
 * 
 * @author Maki
 * 
 */

public enum SpriteType {
	PLAYER, DOG, COW, TREE, CAR, PLAYERANIM, DOGANIM, COWANIM, TREEANIM;


	public String fileName() {
		return this.getClass().getResource("../images/sprites/").toString()
				+ this.name() + ".png";

	}

}
