package characters;

import engine.Mover;

public abstract class GameCharacter extends MapObject {
	
	private String name;
	private Facing facing;
	
	public void doMove(int dx, int dy) {
		Mover.moveCharacter(this, dx, dy);
	}
	
	public void setFacing(Facing facing) {
		this.facing = facing;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Facing getFacing() {
		return facing;
	}

}
