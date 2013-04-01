package gfx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class QuestPanel implements Renderable {
	private Color bgColor = Color.BLUE;
	private Rectangle panel;
	private final int height = 100;

	public QuestPanel(){
		this.panel = new Rectangle(0, GameInit.GAMEDIMENSION[1] - height,
				GameInit.GAMEDIMENSION[0], height);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(bgColor);
		g.fill(panel);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
