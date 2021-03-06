package characters;

public abstract class MapObject {
	static int nextID = 0;
	private int x;
	private int y;
	protected String id;
	private ObjType type;

	public ObjType getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setType(ObjType type) {
		this.type = type;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String getId() {
		return id;
	}
	
}
