package characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.AttackBoundBox;
import engine.GameInput.Movement;
import engine.GameState;
import gfx.Renderable;

/**
 * A simple targetdummy which can attack and be attacked, represented by a
 * square. This is used for testing.
 * 
 * @author aryann
 * 
 */
public class CyanRectangleEnemy implements Combatable {

    private BlueRectangle renderable;
    private AttackBoundBox attackBox;
	private int dmg;
	private int health;
	private int x;
	private int y;
	private int width;
	private int height;
	private int attackRange;
    private State state;
    private int attackCooldown = 10000;
    private Movement facing = Movement.UP;
    private Color color = Color.blue;
    private long t0, timer;

	public CyanRectangleEnemy(int damage, int health) {
        this.dmg = 1;
        this.health = 1000;
		this.x = 375;
		this.y = 345;
        this.width = 30;
        this.height = 30;
        this.attackRange = 10;
        this.renderable = new BlueRectangle(width, height, color);
        this.attackBox = new AttackBoundBox(this);
        this.state = State.ALIVE;
        this.attackCooldown = 500;
	}

    public CyanRectangleEnemy(int damage, int health, int width, int height,
            int attackRange, int x, int y, Color color) {
        this.dmg = damage;
        this.health = health + 1000;
        this.width = width;
        this.height = height;
        this.attackRange = attackRange;
        this.x = x;
        this.y = y;
        this.color = color;
        this.renderable = new BlueRectangle(width, height, color);
        this.attackBox = new AttackBoundBox(this);
        this.state = State.ALIVE;
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
        if (getState() == State.DEAD) {
            System.out.println("I am dead, please stop attacking me ;(");
            return;
        }
		this.health -= damage;

	}

	@Override
	public Renderable getRenderable() {
        if (!GameState.getInstance().isInCurrentMap(this)) {
            renderable.setX(GameState.DIMENSION.width + 1);
            renderable.setY(GameState.DIMENSION.height + 1);
            return renderable;
        }
        renderable.setX(x);
        renderable.setY(y);
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
        private Color color;

        public BlueRectangle(int width, int height, Color color) {
			this.width = width;
			this.height = height;
            this.color = color;
		}

		@Override
		public void render(Graphics2D g) {
            g.setColor(color);
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

	@Override
	public Rectangle getAttackBounds() {
        return attackBox.getRectangle();
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
        return facing;
	}

    @Override
    public Rectangle getBounds() {
        Rectangle rect = new Rectangle(x, y, width, height);
        return rect;
    }

    @Override
    public void setFacing(Movement facing) {
        this.facing = facing;
    }

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
    public void setX(int x) {
        this.x = x;

    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void doSomethingToOtherOnAttack(Combatable other) {
        int w = other.getWidth();
        int h = other.getHeight();
        int x = 0;
        int y = 0;
        int dx = this.getFacing().getDX();
        int dy = this.getFacing().getDY();
        while (x <= w) {
            if (other.getX() <= 0
                    || other.getX() + w >= GameState.DIMENSION.width - 1) {
                break;
            }
            other.setX(other.getX() + dx);
            x++;
        }

        while (y <= h) {
            if (other.getY() <= 0
                    || other.getY() + h >= GameState.DIMENSION.width - 1)
                break;
            other.setY(other.getY() + dy);
            y++;
        }
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

}
