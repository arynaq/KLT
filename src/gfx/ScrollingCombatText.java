package gfx;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;

public class ScrollingCombatText implements Renderable {

	private String xpMessage;
	private int x;
	private int y;
	private int size;
	private Color color;
	private int oldY;

	public ScrollingCombatText(String message, int x, int y, int size, Color color) {
		this.xpMessage = message;
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}

	public void changeString(String newString) {
		this.xpMessage = newString;
	}

	@Override
	public void render(Graphics2D g) {
		if (oldY < y - 100) {
			return;
		}
		Composite gComp = g.getComposite();
		Font stringFont = new Font(Font.SANS_SERIF, Font.BOLD, size);
		g.setFont(stringFont);
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				1 - ((y - oldY) / 100.f));
		g.setComposite(c);
		g.setColor(color);
		g.drawString(xpMessage, x, oldY);
		g.setComposite(gComp);
		oldY -= 3;

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
		this.oldY = y;
	}

}
