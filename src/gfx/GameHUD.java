package gfx;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import characters.Player;


public class GameHUD implements Renderable {
	private Player player;
	Graphics2D g;

	public GameHUD(Player player){
		this.player = player;

	}
	public void renderString(String string, int x, int y, int size,
			Color color, boolean bold) {
		Font stringFont;
		if (bold == true) {
			stringFont = new Font("Commodore 64 Pixelized", Font.BOLD,
					size);
		} else {
			stringFont = new Font("Commodore 64 Pixelized", Font.PLAIN,
					size);
		}
		int h = y;
		int w = x;
		FontRenderContext frc = g.getFontRenderContext();
		AttributedString as = new AttributedString(string);
		as.addAttribute(TextAttribute.FONT, stringFont, 0, string.length());
		AttributedCharacterIterator aci = as.getIterator();
		TextLayout tl = new TextLayout(aci, frc);
		float sw = (float) tl.getBounds().getWidth();
		Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(w
				- (sw / 2), h));

		g.setColor(Color.black);
		g.setStroke(new BasicStroke(3f));
		g.draw(sha);
		g.setColor(color);
		g.fill(sha);


	}
	@Override
	public void render(Graphics2D g) {
		this.g = g;
		/*
		 * Level indicator
		 */
		renderString("Level", 40, 20, 12, Color.yellow, false);
		renderString("" + player.getLevels().getLevel(), 40, 40, 18,
				Color.white, true);

		/*
		 * XP indicator
		 */
		renderString("XP", 70, 485, 10, Color.yellow, true);
		renderString(player.getXP() + "/" + player.getLevels().getReqXP(), 70,
				505, 18, Color.white, true);

		/*
		 * HP indicator
		 */
		renderString("Health", 455, 20, 10, Color.yellow, true);
		renderString(player.getHealth() + "/" + player.getMaxHealth(), 455, 40,
				18, Color.white, true);

		/*
		 * Potion indicator
		 */
		renderString("Potion", 455, 485, 10, Color.yellow, true);
		renderString("" + player.getPotions().size(), 455, 505, 18,
				Color.white, true);


	}


	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}
	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g, int deltaTime) {
		// TODO Auto-generated method stub

	}
}
