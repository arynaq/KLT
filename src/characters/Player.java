package characters;

import java.util.ArrayList;

import engine.Entity;
import engine.GameInput;
import engine.GameInput.Movement;
import engine.GameState;
import engine.Potion;
import gfx.AttackMoveAnimated;
import gfx.Renderable;

public class Player extends GameCharacter implements Entity {

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
	private GameInput.Movement facing;
	private int x;
	private int y;
	private int speedX = 5;
	private int speedY = 5;
	private int playerWidth;
	private int playerHeight;
	private boolean isWalking;
	private int health;
	private int maxHealth;

	public Player(Renderable animatedSprite) {
		this.x = 120;
		this.y = 340;
		this.fullSheet = animatedSprite;
		AttackMoveAnimated sprite = (AttackMoveAnimated) fullSheet;
		this.moveSouthSheet = sprite.getSouthMovementSheet();
		this.moveWestSheet = sprite.getWestMovementSheet();
		this.moveEastSheet = sprite.getEastMovementSheet();
		this.moveNorthSheet = sprite.getNorthMovementSheet();
		this.playerWidth = sprite.getSheeet().getImages().get(0).getWidth();
		this.playerHeight = sprite.getSheeet().getImages().get(0).getHeight();
		this.facing = Movement.RIGHT;
		this.health = 10;
		this.maxHealth = 100;
		HealthPotions.add(new Potion('h', 50));

		// System.out.println("NULL CHECK IN PLAYER:");
		// System.out.println("South: " + moveSouthSheet);
		// System.out.println("West: " + moveWestSheet);
		// System.out.println("East: " + moveEastSheet);
		// System.out.println("North: " + moveNorthSheet);
		// System.out.println("Facing: " + facing);
	}

	@Override
	public Renderable getRenderable() {
		updateCurrentRenderablesX();
		updateCurrentRenderablesY();
		if (facing == Movement.RIGHT) {
			currentRenderable = moveEastSheet;
		}

		else if (facing == Movement.LEFT) {

			currentRenderable = moveWestSheet;
		}

		else if (facing == Movement.UP) {
			currentRenderable = moveNorthSheet;
		}

		else if (facing == Movement.DOWN) {
			currentRenderable = moveSouthSheet;
		}
		return currentRenderable;
	}

	@Override
	public int getX() {
		return x;
	}

	private void updateCurrentRenderablesX() {
		this.moveSouthSheet.setX(x % GameState.DIMENSION.width);
		this.moveWestSheet.setX(x % GameState.DIMENSION.width);
		this.moveEastSheet.setX(x % GameState.DIMENSION.width);
		this.moveNorthSheet.setX(x % GameState.DIMENSION.width);
	}

	@Override
	public int getY() {
		return y;
	}

	private void updateCurrentRenderablesY() {
		this.moveSouthSheet.setY(y % GameState.DIMENSION.width);
		this.moveWestSheet.setY(y % GameState.DIMENSION.width);
		this.moveEastSheet.setY(y % GameState.DIMENSION.width);
		this.moveNorthSheet.setY(y % GameState.DIMENSION.width);
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

	private void setReturnRenderable() {
		if (state == State.DEAD) {
			// He is dead, send the dying animation
			return;
		}

		if (facing == Movement.DOWN) {
		}
	}

	public boolean isAlive() {
		if (state == State.DEAD) {
			return false;
		}
		return true;
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

	public void setFacing(GameInput.Movement facing) {
		this.facing = facing;
	}

	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}

	public int getWidth() {
		return this.playerWidth;
	}

	public int getHeight() {
		return this.playerHeight;
	}

	public int getFeetX() {
		return getX();
	}

	public int getFeetY() {
		return getHeight() / 2 + getY();
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
			System.out
					.println("USING POTION MAFAKKA IN DA FACE YO GANGSTA NIGGAS BE TRIPPIN YO");
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
}
