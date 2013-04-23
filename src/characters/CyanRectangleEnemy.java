package characters;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.AttackBoundBox;
import engine.GameInput.Movement;
import engine.GameState;
import gfx.Renderable;

/**
 * A simple targetdummy which can attack and be attacked, represented by a
 * square. This is used for testing.
 * 
 * @author aryann
 * 
 */
public class CyanRectangleEnemy extends BaseEnemy {
    private BlueRectangle renderable;

    public CyanRectangleEnemy(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.renderable = new BlueRectangle(x, y, width, height, color);
        setFacing(Movement.UP);
        setAttackBox(new AttackBoundBox(this));
        setBounds(new Rectangle(x, y, width, height));
    }

    public CyanRectangleEnemy(int x, int y, int width, int height, int speed,
            Color color) {
        super(x, y, width, height, speed);
        this.renderable = new BlueRectangle(x, y, width, height, color);
        setFacing(Movement.UP);
        setAttackBox(new AttackBoundBox(this));
        setBounds(new Rectangle(x, y, width, height));
    }

    @Override
    public void seek(Combatable other) {
    }

    @Override
    public Renderable getRenderable() {
        renderable.setX(getX());
        renderable.setY(getY());
        if (!GameState.getInstance().isInCurrentMap(this)) {
            renderable.setOutOfMap(true);
        }
 else {
            renderable.setOutOfMap(false);
        }
        return renderable;
    }

    class BlueRectangle implements Renderable {
        int width, height, x, y;
        private Color color;
        private State state;
        private boolean outOfMap;

        public BlueRectangle(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
            this.state = State.ALIVE;
        }

        @Override
        public void render(Graphics2D g) {
            if (state == State.DEAD)
                return;
            if (outOfMap)
                return;
            Composite cG = g.getComposite();
            Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    0.5f);
            g.setComposite(c);
            g.setColor(color);
            g.fillRect(x % GameState.DIMENSION.width, y
                    % GameState.DIMENSION.height, width, height);
            g.setComposite(cG);
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
        }

        public void setState(State state) {
            this.state = state;
        }

        public void setOutOfMap(boolean value) {
            this.outOfMap = value;
        }

    }

}
