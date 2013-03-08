package characters;

public class Player extends GameCharacter {
	
	private int health;
	private String name = "Gukern";
	private int level;
	private int money;
	private int experience;
	private int defense;
	private int attack;
	
	public Player() {
		health = 10;
		level = 1;
		money = 100;
		defense = 0;
		attack = 2;
		
	}
	
	public Player(String name) {
		this();
		this.name = name;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getName() {
		return name;
	}

}
