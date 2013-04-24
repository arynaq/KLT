package gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import engine.GameState;

public class IntroText implements Renderable {

    private Font font = new Font("Manaspace", Font.BOLD, 12);
    private Color borderColor = Color.black;
    private Color paneColor = new Color(51, 102, 255);
    private int cooldown = 2000;
    private int timer;
    private int indx = 0;
    private String[] text = {
            "Beware <Gukern The Forsaken> who rules over KLT.",
            "In the dark eastern forest.",
            "Long and hard have we fought to end his evil reign.",
            "But without a strong hero we have failed.",
            "Say, can you, <The Chosen Savior>, destroy him? ",
 ".....",
            "The controls are WASD and Space for attack.",
            "P for pause, E for using potions.",
            "Good luck with the game (Alpha 1)!"

    };


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
            indx++;
            timer = 0;
        }

        g.setColor(paneColor);
        g.fillRect(0, GameState.DIMENSION.height - 110,
                GameState.DIMENSION.width, 110);
        g.setColor(Color.white);
        g.fillRoundRect(10, GameState.DIMENSION.height - 100, 512 - 20, 90, 10,
                10);
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(s, 15, GameState.DIMENSION.height - 85);
    }

    @Override
    public void setX(int x) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setY(int y) {
        // TODO Auto-generated method stub

    }

    public boolean isOver() {
        return indx == (text.length);
    }

    public void skipLine() {
        this.timer += cooldown;
    }

}
