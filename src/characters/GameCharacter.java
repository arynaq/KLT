package characters;

public abstract class GameCharacter extends MapObject {
	
	private String name;
	private Facing facing;
	
	public void doMove(int dx, int dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}
	
	
}
