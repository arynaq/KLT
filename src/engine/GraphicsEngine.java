package engine;

import gfx.GameFrame;
import gfx.Renderable;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;



public class GraphicsEngine {

    private Map<String, Entity> entities;
    private Map<String, ArrayList<BufferedImage>> images;
    private Map<String, Renderable> renderables;
    private int width;
    private int height;
    private long timer, t0;

    private Graphics2D g;
    private BufferStrategy bufferStrategy;
    private GameFrame frame;
    private FontMetrics fontMetrics;


    public GraphicsEngine(Map<String, Entity> entities,
            Map<String, Renderable> renderables,
            Map<String, ArrayList<BufferedImage>> images, GameFrame frame) {

        this.entities = entities;
        this.renderables = renderables;
        this.images = images;
        this.frame = frame;

        this.g = frame.getGraphics();
        this.fontMetrics = g.getFontMetrics();
        this.bufferStrategy = frame.getBufferStrategy();
        this.width = (int) GameState.getInstance().getFrameWidth();
        this.height = (int) GameState.getInstance().getFrameHeight();

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        clearScreen();
        System.out.println("GraphicsEngine loaded.");
    }


    public void start() {
        frame.setVisible(true);
        Renderable currentMap = GameState.getInstance().getCurrentMap();
        renderables.put("currentMap", currentMap);
    }

    public void clearScreen() {
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
    }

    public void showBuffer() {
        bufferStrategy.show();
    }

    public void drawAll() {

    }

    public void render(int delta) {
        timer += delta;
        t0 = System.currentTimeMillis();
        if (timer >= (1000.0 / GameState.GAMEFPS)) {
        } else
            return;
        do {
            clearScreen();
            renderables.get("currentMap").render(g);
            for (String key : renderables.keySet()) {
                if (key.equals("currentMap"))
                    continue;
                if (key.equals("gameOverScreen"))
                    continue;
                if (key.equals("pauseScreen"))
                    continue;
                renderables.get(key).render(g);
                renderables.get(key).render(g, (int) timer);

            }
            for (String key : entities.keySet()) {
                entities.get(key).getRenderable().render(g, (int) timer);
                entities.get(key).getRenderable().render(g);
            }
            showBuffer();
        } while (bufferStrategy.contentsLost());
        timer = 0;
    }

    public void renderGameOver() {
        clearScreen();
        renderables.get("gameOverScreen").render(g);
        showBuffer();
    }

    public void renderPause() {
        renderables.get("pauseScreen").render(g);
        showBuffer();
    }

    public void renderRectangle(int x, int y, int w, int h, String color) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, w, h);
    }

    public void renderSplash() {
        clearScreen();
        g.drawImage(images.get("splashBACKGROUND").get(0), 0, 0, null);
        renderables.get("splash").render(g);
        showBuffer();
    }

}

