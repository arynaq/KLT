package gfx;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;

import engine.GameState;

public class ScrollingCombatText implements Renderable {

    private String xpMessage;
    private Font stringFont;
	private int x;
	private int y;
	private Color color;
    private final Color resetColor;
    private int oldY;
    private int gameHeight =  GameState.DIMENSION.height;
    private int gameWidth = GameState.DIMENSION.width;


	public ScrollingCombatText(String message, int x, int y, int size, Color color) {
		this.xpMessage = message;
		this.x = x;
		this.y = y;
        this.color = color;
        this.resetColor = color;
        this.stringFont = new Font("Commodore 64 Pixelized", Font.BOLD, size);
        // new Font(Font.SANS_SERIF, Font.BOLD, size);
    }

    public void changeString(String newString, Color color) {
		this.xpMessage = newString;
        this.color = color;
	}

	@Override
	public void render(Graphics2D g) {
        if (x < 0 && x > gameWidth)
            return;
        if (y < 0 && y > gameWidth)
            return;
        if (oldY < y - 100) {
            return;
        }
        Composite gComp = g.getComposite();
        g.setFont(stringFont);
        Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                1 - 0.8f * ((y - oldY) / (float) (100)));
        g.setComposite(c);
        g.setColor(color);
        g.drawString(xpMessage, x % gameWidth, oldY % gameHeight);
        g.setComposite(gComp);
        oldY -= 1.5;

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

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor() {
        this.color = resetColor;
    }

}
