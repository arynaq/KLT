package engine;

import gfx.Renderable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import characters.GameCharacter;

public class SpriteBoundBox implements Renderable {
		GameCharacter gc;
		Rectangle rectangle;

		public SpriteBoundBox(GameCharacter gc) {
			this.gc = gc;
			this.rectangle = new Rectangle(gc.getX(), gc.getY(), gc.getWidth(),
					gc.getHeight());
		}

		@Override
		public void render(Graphics2D g) {
			g.setColor(Color.blue);
		g.drawRect(gc.getX() % GameState.DIMENSION.width, gc.getY()
				% GameState.DIMENSION.height, gc.getWidth(), gc.getHeight());
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
		
		public Rectangle getRectangle() {
			rectangle.setBounds(gc.getX(), gc.getY(), gc.getWidth(),
					gc.getHeight());
			return rectangle;
		}
	}