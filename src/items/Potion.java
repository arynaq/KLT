package items;

public class Potion extends GameItem {

	public Potion(){
		type = "PTN";
		id = type + (++GameItem.nextID);
	}


}
