package characters;

public class Player extends GameCharacter {
	
	private int health;
	private int level;
	private int money;
	private int experience;
	
	public Player() {
		health = 10;
		level = 1;
		money = 100;
		this.setType(ObjType.PLAYER);
		id = "PLYR" + (++MapObject.nextID);
		
	}
	
	public Player(String name) {
		this();
		this.setName(name);
	}
	
	public void setMoney(int money) {
		this.money = money;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public void levelUp() {
		this.level++;
		this.health += (this.level * 2);
		this.experience = 0;
	}

	public int getExperience() {
		return experience;
	}

	public int getHealth() {
		return health;
	}

	public int getMoney() {
		return money;
	}

}
