package gfx;

import java.awt.Color;
import java.awt.Graphics2D;

public class QuestPanel implements Renderable {
	private String string;
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private float transp;
	private boolean round;

	public QuestPanel(int x, int y, int width, int height, Color color,
			float transp, boolean round) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.transp = transp;
		this.round = round;

	}
	@Override
	public void render(Graphics2D g) {
		if (round == true) {
			g.setColor(color);
			g.fillRoundRect(x, y, width, height, 5, 5);
		} else {
			g.setColor(color);
			g.fillRect(x, y, width, height);
		}
	}

	@Override
	public void render(Graphics2D g, int deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

}
