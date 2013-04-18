package characters;

import items.Potion;

import java.util.ArrayList;

import engine.Entity;
import engine.GameInput.Movement;
import engine.GameState;
import gfx.AttackMoveAnimated;
import gfx.Renderable;

public class Player extends GameCharacter implements Entity, Combatable {

	private Renderable fullSheet;
	private Renderable moveSouthSheet;
	private Renderable moveWestSheet;
	private Renderable moveEastSheet;
	private Renderable moveNorthSheet;
	private Renderable attackSouthSheet;
	private Renderable attackWestSheet;
	private Renderable attackEastSheet;
	private Renderable attackNorthSheet;
	private Renderable currentRenderable;

	private ArrayList<Potion> HealthPotions = new ArrayList<Potion>();
	private State state;
	// private GameInput.Movement facing;
	private int x;
	private int y;
	private int speedX = 5;
	private int speedY = 5;
	private int playerWidth;
	private int playerHeight;
	private int health;
	private int maxHealth;
	private int xp;
	private int dmg = 20;
	/**
	 * The player is initialized with its sprite represented by the renderable.
	 * @param renderable
	 */
	public Player(Renderable renderable) {
		this.x = 120;
		this.y = 340;
		this.fullSheet = renderable;
		super.setFacing(Movement.RIGHT);
	}

	/**
	 * The player is initialized with its sprite being fully animated.
	 * 
	 * @param animatedSpriteSheet
	 */
	public Player(AttackMoveAnimated animatedSpriteSheet) {
		this.x = 120;
		this.y = 340;
		this.fullSheet = animatedSpriteSheet;
		this.moveSouthSheet = animatedSpriteSheet.getSouthMovementSheet();
		this.moveWestSheet = animatedSpriteSheet.getWestMovementSheet();
		this.moveEastSheet = animatedSpriteSheet.getEastMovementSheet();
		this.moveNorthSheet = animatedSpriteSheet.getNorthMovementSheet();
		this.playerWidth = animatedSpriteSheet.getSheeet().getImages().get(0)
				.getWidth();
		this.playerHeight = animatedSpriteSheet.getSheeet().getImages().get(0)
				.getHeight();
		super.setFacing(Movement.RIGHT);
	}

	@Override
	public Renderable getRenderable() {
		if (getFacing() == Movement.RIGHT) {
			currentRenderable = moveEastSheet;
		}

		else if (getFacing() == Movement.LEFT) {

			currentRenderable = moveWestSheet;
		}

		else if (getFacing() == Movement.UP) {
			currentRenderable = moveNorthSheet;
		}

		else if (getFacing() == Movement.DOWN) {
			currentRenderable = moveSouthSheet;
		}
		updateCurrentRenderable();
		return currentRenderable;
	}

	@Override
	public int getX() {
		return x;
	}

	private void updateCurrentRenderable() {
		currentRenderable.setX(x % GameState.DIMENSION.width);
		currentRenderable.setY(y % GameState.DIMENSION.height);

	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	// Returns state of player. Returns DEAD or ALIVE
	public State getState() {
		if (this.health <= 0) {
			this.state = State.DEAD;
		} else if (this.health > 0) {
			this.state = State.ALIVE;
		}
		return state;
	}

	public void setX(int x) {
		this.x = x;
		this.currentRenderable.setX(x % GameState.DIMENSION.width);
	}

	public void setY(int y) {
		this.y = y;
		this.currentRenderable.setY(y % GameState.DIMENSION.height);
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public int getWidth() {
		return this.playerWidth;
	}

	public int getHeight() {
		return this.playerHeight;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int newHealth) {
		health = newHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int newMaxHealth) {
		maxHealth = newMaxHealth;
	}

	// Bruker HealthPotion for now
	public void usePotion(char potionType) {

		if (HealthPotions.size() > 0 && health < maxHealth) {
			if ((maxHealth - health) < HealthPotions.get(0).getValue()) {
				health = maxHealth;
				HealthPotions.remove(0);
			} else {
				health += HealthPotions.get(0).getValue();
				HealthPotions.remove(0);
			}
		}

	}

	public int getNumPotions() {
		return HealthPotions.size();
	}

	public void setXP(int xpGain) {
		xp += xpGain;
	}

	public int getXP() {
		return xp;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	@Override
	public void attack(Combatable other) {
		other.getAttacked(dmg);
	}

	@Override
	public void seek(Combatable other) {
		// TODO Auto-generated method stub

	}

	@Override
	public void seek(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAttacked(int damage) {
		// int dx = getFacing().opposite().getDX() * 2 * speedX;
		// int dy = getFacing().opposite().getDY() * 2 * speedY;
		// setX(getX() + dx);
		// setY(getY() + dy);
		this.health -= damage;
	}
}
