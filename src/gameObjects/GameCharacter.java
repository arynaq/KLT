package gameObjects;

public class GameCharacter {
	private int health = 0;
	private int damage = 0;
	private String name = null;
	private Facing facing = Facing.EAST;

	public GameCharacter(int health, int damage, String name, Facing facing) {
		this.health = health;
		this.damage = damage;
		this.name = name;
		this.facing = facing;
	}
	
	public GameCharacter() {
		this.health = 100;
		this.damage = 10;
		this.name = "Unknown";
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Facing getFacing() {
		return facing;
	}
}
