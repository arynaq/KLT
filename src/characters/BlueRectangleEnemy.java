package characters;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.Entity;
import engine.GameState;
import gfx.Renderable;

public class BlueRectangleEnemy implements Entity, Combatable {

	private Renderable renderable;
	private int dmg;
	private int health;
	private int x;
	private int y;

	public BlueRectangleEnemy(int damage, int health) {
		this.dmg = 1;
		this.health = 100;
		this.renderable = new BlueRectangle(100, 100);
		this.x = 375;
		this.y = 345;
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
		return 0;
	}

	@Override
	public int getY() {
		return 0;
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
			if (getState() == State.DEAD) {
				g.setColor(Color.red);
				g.fillRect(x, y, width, height);
				return;
			}
			g.setColor(Color.blue);
			g.fillRect(x, y, width, height);
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

}
