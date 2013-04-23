package characters;

import java.awt.Rectangle;

import engine.GameInput.Movement;

public abstract class BaseEnemy implements Combatable {
	private AttackBoundBox attackBox;
	private Rectangle bounds;
	private int dmg;
	private int health;
	private int x;
	private int y;
	private int width;
	private int height;
	private int attackRange;
	private State state;
	private int attackCooldown;
	private Movement facing;
	private long t0, timer;
	private int speed, speedCounter;
	private boolean diagonal;
	private int lastx;
	private int lasty;

	public BaseEnemy(int x, int y, int width, int height, int damage,
			int health, int attackRange, int attackCooldown) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.dmg = damage;
		this.health = health;
		this.attackRange = attackRange;
		this.attackCooldown = attackCooldown;
		this.bounds = new Rectangle(x, y, width, height);
	}

	public BaseEnemy(int x, int y, int width, int height, int speed) {
		this(x, y, width, height, 1, 30, 20, 1000);
		this.speed = speed;
	}

	public BaseEnemy(int x, int y, int width, int height) {
		this(x, y, width, height, 1);
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public Movement getFacing() {
		return this.facing;
	}

	@Override
	public void setFacing(Movement facing) {
		this.facing = facing;

	}

	/**
	 * Compares this combatable to the other combatably by distance from the
	 * upper-left corner, which is the origin (0,0).
	 */
	@Override
	public int compareTo(Combatable o) {
		double r = Math.sqrt(this.getX() * this.getX() + this.getY()
				* this.getY());
		double rOther = Math.sqrt(o.getX() * o.getX() + o.getY() * o.getY());

		if (r == rOther) {
			return 0;
		}

		else if (r > rOther) {
			return 1;
		}

		else {
			return -1;
		}
	}

	@Override
	public void attack(Combatable other, int dmg) {
		other.getAttacked(dmg);
	}

	@Override
	public void seek(Player player) {
		int dx, dy, xface, yface;
		if (this.x - player.getX() >= 0) {
			xface = 1;
		} else {
			xface = -1;
		}
		if (this.y - player.getY() >= 0) {
			yface = 1;
		} else {
			yface = -1;
		}
		dx = Math.abs(this.x - player.getX());
		dy = Math.abs(this.y - player.getY());

		if (dx == 0 && dy == 0)
			return;
		if (player.getX() != lastx || player.getY() != lasty) {
			diagonal = false;
		}
		if (dx == dy)
			diagonal = true;

		if (Math.abs(dx - dy) <= speed) {
			diagonal = true;
		}

		if (dx == 0)
			diagonal = false;
		if (diagonal) {
			if (dx < speed) {
				this.x = player.getX();
				// return;
			} else if (xface == -1) {
				this.x += speed;
			} else if (xface == 1) {
				this.x -= speed;
			}
		}

		else {
			if (dx > dy) {
				if (dx < speed) {
					this.x = player.getX();
					return;
				}

				if (this.x < player.getX()) {
					this.x += speed;

				}

				else if (this.x > player.getX()) {
					this.x -= speed;
				}
			}

			else if (dy > dx) {
				if (dy < speed) {
					this.y = player.getY();
					return;
				}
				if (this.y < player.getY()) {
					this.y += speed;
				}

				else if (this.y > player.getY()) {
					this.y -= speed;
				}
			}
		}
		lastx = player.getX();
		lasty = player.getY();
	}

	@Override
	public void getAttacked(int damage) {
		this.health -= damage;
	}

	@Override
	public Rectangle getAttackBounds() {
		return attackBox.getRectangle();
	}

	@Override
	public Rectangle getBounds() {
		bounds.setBounds(x, y, width, height);
		return bounds;
	}

	public void setAttackBox(AttackBoundBox attackBox) {
		this.attackBox = attackBox;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	@Override
	public int getAttackRange() {
		return attackRange;
	}

	@Override
	public boolean isReadyToAttack() {
		boolean ret;
		long delta = System.currentTimeMillis() - t0;
		timer += delta;
		if (timer > attackCooldown) {
			timer = 0;
			ret = true;
		} else {
			ret = false;
		}
		t0 = System.currentTimeMillis();
		return ret;
	}

	@Override
	public int getDamage() {
		return dmg;
	}

	private void setFacingRelativeToPlayer(Player player, int dx, int dy) {

		if (dx * dx > dy * dy) {
			setFacing((dx < 0) ? Movement.RIGHT : Movement.LEFT);
		}

		else if (dy * dy > dx * dx) {
			setFacing((dy < 0) ? Movement.DOWN : Movement.UP);
		}
	}

}
