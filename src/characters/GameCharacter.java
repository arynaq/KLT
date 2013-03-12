package characters;

import engine.Mover;

public abstract class GameCharacter extends MapObject {
	
	private String name;
	private Facing facing;
	private int stepSize = 16;
	
	/**
	 * Move this character dx,dy step
	 * 
	 * @param dx
	 * @param dy
	 */
	public void doMove(int dx, int dy) {
		Mover.moveCharacter(this, dx, dy);
	}
	
	/**
	 * Set this characters facing
	 * 
	 * @param facing
	 */
	public void setFacing(Facing facing) {
		this.facing = facing;
	}
	
	/**
	 * Set this characters name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the stepsize for each movement
	 * 
	 * @param stepSize
	 */
	public void setStepSize(int stepSize) {
		this.stepSize = stepSize;
	}

	public String getName() {
		return name;
	}

	public Facing getFacing() {
		return facing;
	}
	
	public int getStepSize() {
		return stepSize;
	}



}
