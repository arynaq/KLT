package items;

public class Potion extends GameItem {

	public Potion(){
		type = "PTN";
		id = type + (++GameItem.nextID);
	}

	@Override
	public int compareTo(GameItem arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
