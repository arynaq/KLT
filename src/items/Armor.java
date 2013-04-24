package items;


public class Armor extends GameItem {
    private int defense;
	public Armor() {
		setDefense(1);
		type = "ARMR";
		id = type + (++GameItem.nextID);
	}

    @Override
    public int compareTo(GameItem arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

}
