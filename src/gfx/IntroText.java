package gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class IntroText implements Renderable {

    private Font font = new Font("Commodore 64 Pixelized", Font.PLAIN, 12);
    private int cooldown = 2000;
    private int timer;
    private int indx = 0;
    private String[] text = {
            "Beware the mighty blue rectangle on the next screen",
            "Long and hard have we fought to end his evil reign.",
            "But without a strong hero we have failed.",
            "Say, can you try and kill him?" };


    @Override
    public void render(Graphics2D g) {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(Graphics2D g, int deltaTime) {
        if (indx == text.length)
            return;
        timer += deltaTime;
        String s = text[indx];
        if (timer >= cooldown) {
            g.setColor(Color.green);
            indx++;
            timer = 0;
        }
        g.setFont(font);
        g.drawString(s, 100, 100);

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
