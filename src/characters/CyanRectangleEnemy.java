package characters;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.Entity;
import engine.GameInput.Movement;
import engine.GameState;
import gfx.Renderable;

/**
 * A simple targetdummy which can attack and be attacked, represented by a blue
 * square.
 * 
 * @author aryann
 * 
 */
public class CyanRectangleEnemy implements Entity, Combatable {

	private Renderable renderable;
	private int dmg;
	private int health;
	private int x;
	private int y;
	private int width;
	private int height;
	private int attackRange;

	public CyanRectangleEnemy(int damage, int health) {
		this.dmg = 1;
		this.health = 100;
		this.x = 375;
		this.y = 345;
		this.width = 20;
		this.height = 20;
		this.attackRange = 10;
		this.renderable = new BlueRectangle(width, height);
	}

	@Override
	public void attack(Combatable other) {
		other.getAttacked(dmg);
	}

	@Override
	public void seek(Combatable other) {
	}

	@Override
	public void seek(Player player) {
	}

	@Override
	public void getAttacked(int damage) {
		this.health -= damage;

	}

	@Override
	public Renderable getRenderable() {
		renderable.setX(x % GameState.DIMENSION.width);
		renderable.setY(y % GameState.DIMENSION.height);
		return renderable;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public State getState() {
		if (health > 0)
			return State.ALIVE;
		return State.DEAD;
	}

	class BlueRectangle implements Renderable {
		int width, height, x, y;
		public BlueRectangle(int width, int height) {
			this.width = width;
			this.height = height;
		}

		@Override
		public void render(Graphics2D g) {
			Composite cG = g.getComposite();
			if (getState() == State.DEAD) {
				g.setColor(Color.red);
				g.fillRect(x, y, width, height);
				return;
			}
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.5f));
			g.setColor(Color.cyan);
			g.fillRect(x, y, width, height);
			g.setComposite(cG);
		}

		@Override
		public void render(Graphics2D g, int deltaTime) {

		}

		@Override
		public void setX(int x) {
			this.x = x;
		}

		@Override
		public void setY(int y) {
			this.y = y;
		}

	}

	@Override
	public Rectangle getAttackBounds() {
		return null;
	}

	@Override
	public int getAttackRange() {
		return attackRange;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Movement getFacing() {
		return Movement.LEFT;
	}

}
