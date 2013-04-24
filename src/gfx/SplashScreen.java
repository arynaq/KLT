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

import engine.GameState;

public class SplashScreen implements Renderable {

    private Font titleFont;
    private Font subtitleFont;
    private Font creditFont;
    private Font optionsFont;
    private BasicStroke stroke;
    private BasicStroke defaultStroke;
    private FontRenderContext frc;
    private boolean executedBefore;
    private int centerX;

    private Shape title;
    private Shape credits;
    private Shape subtitle;
    private Shape options;

    public SplashScreen() {
        this.titleFont = new Font("Commodore 64 Pixelized", Font.BOLD, 98);
        this.optionsFont = new Font("Commodore 64 Pixelized", Font.BOLD, 18);
        this.subtitleFont = new Font("Commodore 64 Pixelized", Font.PLAIN, 12);
        this.creditFont = new Font("Commodore 64 Pixelized", Font.PLAIN, 12);
        this.stroke = new BasicStroke(3f);
        this.centerX = (GameState.DIMENSION.width / 2);
    }

    @Override
    public void render(Graphics2D g) {
        if (!executedBefore) {
            setUpShapes(g);
            executedBefore = true;
        }
        this.defaultStroke = (BasicStroke) g.getStroke();
        renderString(g, title, Color.black, Color.white);
        renderString(g, subtitle, Color.white, Color.black);
        renderString(g, options, Color.white, Color.blue);
        renderString(g, credits, Color.white, Color.black);
        g.setStroke(defaultStroke);
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

    private void setUpShapes(Graphics2D g) {
        frc = g.getFontRenderContext();
        title = getShape("KLT", centerX, 410, "title");
        subtitle = getShape("- The Role Playing Game -", centerX, 430,
                "subtitle");
        credits = getShape(
                "A game by: Fredrik 'Ferd' Larsen and Aryan Naqid (2013)",
                centerX, 506, "credits");
        options = getShape("START GAME", centerX, 460, "options");
    }

    private Shape getShape(String string, int x, int y, String type) {
        int h = y;
        int w = x;
        AttributedString as = new AttributedString(string);
        if (type.equals("title")) {
            as.addAttribute(TextAttribute.FONT, titleFont, 0, string.length());
        }

        else if (type.equals("subtitle")) {
            as.addAttribute(TextAttribute.FONT, subtitleFont, 0,
                    string.length());
        }

        else if (type.equals("credits")) {
            as.addAttribute(TextAttribute.FONT, creditFont, 0, string.length());
        }

        else if (type.equals("options")) {
            as.addAttribute(TextAttribute.FONT, optionsFont, 0, string.length());
        }

        AttributedCharacterIterator aci = as.getIterator();
        TextLayout tl = new TextLayout(aci, frc);
        float sw = (float) tl.getBounds().getWidth();
        Shape shape = tl.getOutline(AffineTransform.getTranslateInstance(w
                - (sw / 2), h));
        return shape;
    }

    public void renderString(Graphics2D g, Shape shape, Color color,
            Color strokeColor) {
        g.setColor(strokeColor);
        g.setStroke(stroke);
        g.draw(shape);
        g.setColor(color);
        g.fill(shape);
    }
}
