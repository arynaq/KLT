package items;

public abstract class GameItem implements Comparable<GameItem> {
	
	protected String id;
	protected static int nextID = 0;
	protected String type;

	public String getId() {
		return id;
	}
}
