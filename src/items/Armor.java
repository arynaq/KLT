package items;


public class Armor extends GameItem {
	private int defense;
	public Armor() {
		defense = 1;
		type = "ARMR";
		id = type + (++GameItem.nextID);
	}

}
