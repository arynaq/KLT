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
		int w = t.getWidth() + t.getAttackRange();
		int h = t.getHeight();

		int x = t.getX() + (t.getWidth() / 2);
		int y = t.getY();

		if (t.getFacing().getDX() < 0) {
			x = x - w;
		}

		if (t.getFacing().getDY() < 0) {
			x = x - w + y / 2;
		}
		rectangle.setBounds(x, y, w, h);
	}

	public Rectangle getRectangle() {
		setUpBox();
		return rectangle;
	}
}