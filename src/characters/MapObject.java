package characters;


public abstract class MapObject {
	protected static int nextID = 0;
	private int x;
	private int y;
	protected String id;
	private ObjType type;
	private boolean isWalkable;
	

	public ObjType getType() {
		return type;
	}

	public synchronized int getX() {
		return x;
	}

	public synchronized int getY() {
		return y;
	}

	public void setType(ObjType type) {
		this.type = type;
	}

	public synchronized void setX(int x) {
		this.x = x;
	}

	public synchronized void setY(int y) {
		this.y = y;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean getIsWalkable() {
		return isWalkable;
	}
	
	@Override
	public String toString(){
		return "MapObject: " + type + id + " at: " + "(" + x + "," + y + ")"
				+ "walkable: " + isWalkable;
	}


}
