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
    private FontRenderContext frc;
    private BasicStroke stroke;
    private BasicStroke gStroke;
    private Graphics2D g;

    /*
     * The font used for the statics, "Level", "Health" etc.
     */
    private Font staticFont;
    /*
     * The font used for the variable values hp, xp etc.
     */
    private Font volatileFont;


    /*
     * Holders for old values, to check whether the shapes need updating.
     */
    private boolean executedBefore;
    private int xp = -1;
    private int xpreq = -1;
    private int hp = -1;
    private int lvl = -1;
    private int potion = -1;

    private Shape lvlShape;
    private Shape xpShape;
    private Shape hpShape;
    private Shape potShape;
    /*
     * These are the static display shapes
     */
    private Shape LEVELSHAPE;
    private Shape XPSHAPE;
    private Shape POTIONSHAPE;
    private Shape HPSHAPE;

    public GameHUD(Player player) {
        this.player = player;
        this.staticFont = new Font("Commodore 64 Pixelized",
                Font.BOLD, 10);
        this.volatileFont = new Font("Commodore 64 Pixelized", Font.BOLD, 18);
        this.stroke = new BasicStroke(3f);

    }


    @Override
    public void render(Graphics2D g) {
        this.gStroke = (BasicStroke) g.getStroke();
        this.g = g;

        /*
         * Check whether the static shapes have been set or not.
         */
        if (!executedBefore) {
            setUpStaticHudShapes(g);
            executedBefore = true;
        }

        /*
         * Level indicator
         */
        renderString(LEVELSHAPE, Color.yellow);
        if (levelChanged()) {
            lvlShape = getShape("" + player.getLevels().getLevel(), 40, 40);
        }
        renderString(lvlShape, Color.white);

        /*
         * XP indicator
         */
        renderString(XPSHAPE, Color.yellow);
        if (XPChanged()) {
            xpShape = getShape(player.getXP() + "/"
                    + player.getLevels().getReqXP(), 70, 505);
        }
        renderString(xpShape, Color.white);

        /*
         * HP indicator
         */
        renderString(HPSHAPE, Color.yellow);
        if (HPChanged()) {
            hpShape = getShape(
                    player.getHealth() + "/" + player.getMaxHealth(), 455, 40);
        }
        renderString(hpShape, Color.white);

        /*
         * Potion indicator
         */
        // renderString("Potion", 455, 485, 10, Color.yellow, true);
        renderString(POTIONSHAPE, Color.yellow);
        if (potionChanged()) {
            potShape = getShape("" + player.getPotionsSize(), 455, 505);
        }
        renderString(potShape, Color.white);
        // renderString("" + player.getPotionsSize(), 455, 505, 18,
        // Color.white,
        // true);

        g.setStroke(gStroke);
    }


    private Shape getShape(String string, int x, int y) {
        int h = y;
        int w = x;
        AttributedString as = new AttributedString(string);
        as.addAttribute(TextAttribute.FONT, volatileFont, 0, string.length());
        AttributedCharacterIterator aci = as.getIterator();
        TextLayout tl = new TextLayout(aci, frc);
        float sw = (float) tl.getBounds().getWidth();
        Shape shape = tl.getOutline(AffineTransform.getTranslateInstance(w
                - (sw / 2), h));
        return shape;
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

    }

    /**
     * The geometric calculations of the HUD is very expensive in memory and
     * cpu. Some helper methods.
     */
    private boolean HPChanged() {
        if (player.getHealth() != hp) {
            hp = player.getHealth();
            return true;
        }
        return false;

    }

    private boolean XPChanged() {
        if (xp != player.getXP()) {
            xp = player.getXP();
            return true;
        }
        return false;
    }

    private boolean XPRequirementChanged() {
        if (xpreq != player.getLevels().getReqXP()) {
            xpreq = player.getLevels().getReqXP();
            return true;
        }
        return false;
    }

    private boolean levelChanged() {
        if (lvl != player.getLevels().getLevel()) {
            lvl = player.getLevels().getLevel();
            return true;
        }
        return false;
    }

    private boolean potionChanged() {

        if (potion != player.getPotionsSize()) {
            potion = player.getPotionsSize();
            return true;
        }
        return false;
    }

    private void setUpStaticHudShapes(Graphics2D g) {
        /*
         * Set fontrenderingcontext
         */

        this.frc = g.getFontRenderContext();

        /*
         * Set up "Level"
         */
        int w = 40;
        int h = 20;

        AttributedString as = new AttributedString("Level");
        as.addAttribute(TextAttribute.FONT, staticFont, 0, "Level".length());
        AttributedCharacterIterator aci = as.getIterator();
        TextLayout tl = new TextLayout(aci, frc);
        float sw = (float) tl.getBounds().getWidth();
        LEVELSHAPE = tl.getOutline(AffineTransform.getTranslateInstance(w
                - (sw / 2), h));

        /*
         * Set "HP"
         */
        w = 455;
        h = 20;
        as = new AttributedString("Health");
        as.addAttribute(TextAttribute.FONT, staticFont, 0, "Health".length());
        aci = as.getIterator();
        tl = new TextLayout(aci, frc);
        sw = (float) tl.getBounds().getWidth();
        HPSHAPE = tl.getOutline(AffineTransform.getTranslateInstance(w
                - (sw / 2), h));

        /*
         * Set "XP"
         */
        w = 70;
        h = 485;
        as = new AttributedString("XP");
        as.addAttribute(TextAttribute.FONT, staticFont, 0, "XP".length());
        aci = as.getIterator();
        tl = new TextLayout(aci, frc);
        sw = (float) tl.getBounds().getWidth();
        XPSHAPE = tl.getOutline(AffineTransform.getTranslateInstance(w
                - (sw / 2), h));

        /*
         * Set "Potion"
         */
        w = 455;
        h = 485;
        as = new AttributedString("Potion");
        as.addAttribute(TextAttribute.FONT, staticFont, 0, "Potion".length());
        aci = as.getIterator();
        tl = new TextLayout(aci, frc);
        sw = (float) tl.getBounds().getWidth();
        POTIONSHAPE = tl.getOutline(AffineTransform.getTranslateInstance(w
                - (sw / 2), h));

    }


    public void renderString(Shape shape, Color color) {
        g.setColor(Color.black);
        g.setStroke(stroke);
        g.draw(shape);
        g.setColor(color);
        g.fill(shape);
    }

    /**
     * Endret mye her, hvis alle statics på huden har samme størrelse så slipper
     * vi veldig mye objektgenerering og slipper da memoryleaks og heavy
     * cpu-bruk.
     */

}
