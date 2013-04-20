package engine;

import gfx.Renderable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import characters.Combatable;

public class AttackBoundBox implements Renderable {
	Combatable t;
	Rectangle rectangle;

	public AttackBoundBox(Combatable combatable) {
		this.t = combatable;
		this.rectangle = new Rectangle();
		setUpBox();
	}



	@Override
	public void render(Graphics2D g) {
		if (!GameState.getInstance().isInCurrentMap(t)) {
			return;
		}
		setUpBox();
		g.setColor(Color.red);
		g.drawRect(rectangle.x % GameState.DIMENSION.width, rectangle.y
				% GameState.DIMENSION.height, rectangle.width, rectangle.height);
	}

	@Override
	public void render(Graphics2D g, int deltaTime) {
	}

	@Override
	public void setX(int x) {
	}

	@Override
	public void setY(int y) {
	}

	private void setUpBox() {
	    int x = t.getX();
	    int y = t.getY();
        int w = t.getWidth();
        int h = t.getHeight();

        if (t.getFacing().getDX() != 0) {
            w += t.getAttackRange();
            x += (t.getWidth() + w * (t.getFacing().getDX() - 1)) / 2;
        }

        else {
            h = t.getWidth() + t.getAttackRange();
            w = t.getHeight();
            x -= t.getWidth() / 4;
            y += (h * (t.getFacing().getDY() - 1) + t.getHeight()) / 2;
        }

		rectangle.setBounds(x, y, w, h);

	}

	public Rectangle getRectangle() {
		setUpBox();
		return rectangle;
	}
}