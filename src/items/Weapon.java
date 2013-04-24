package items;

public class Weapon extends GameItem {
	
	public Weapon() {
		type = "WPN";
		id = type + (++GameItem.nextID);
	}
	
	public enum Type {
		LONGSWRD("Longsword", 10, 1), SHRTSWRD("Shortsword", 5, 0.5), 
		DGGER("Dagger", 3, 0.2);

		String type;
		int damage;
		double cooldown;
		Type(String type, int damage, double cooldown) {
			this.type = type;
			this.damage = damage;
			this.cooldown = cooldown;
		}
	}

    @Override
    public int compareTo(GameItem o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
