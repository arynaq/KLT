package gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.GameEventListener;
import engine.GameState;

public class GameFrame {
	private JFrame frame;
	private JPanel panel;
	private Canvas canvas;
	private BufferStrategy bufferStrategy;
	private GameEventListener listener;
	private Graphics2D g;
	private int width;
	private int height;
	
	public GameFrame(Color bgColor) {
		this.width = (int) GameState.getInstance().getFrameWidth();
		this.height = (int) GameState.getInstance().getFrameHeight();
		this.frame = new JFrame();
		this.panel = (JPanel) frame.getContentPane();
		this.panel.setPreferredSize(new Dimension(width, height));
		this.panel.setLayout(null);
		this.canvas = new Canvas();
		this.canvas.setBounds(0, 0, width, height);
		this.frame.setBackground(bgColor);
		this.canvas.setIgnoreRepaint(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.frame.setResizable(false);
		this.frame.pack();
		this.canvas.setBackground(bgColor);
		this.panel.add(canvas);
		this.frame.setLocationRelativeTo(null);
		this.frame.setTitle("KLT - The RPG");
		this.frame.setVisible(true);
		this.canvas.createBufferStrategy(2);
		this.bufferStrategy = canvas.getBufferStrategy();
		this.g = (Graphics2D) bufferStrategy.getDrawGraphics();
		this.canvas.requestFocus();

        // g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        // RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        // g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        // RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        System.out.println("GameFrame set up.");
		clearScreen();

	}
	
	public void clearScreen() {
		this.g.drawRect(0, 0, width, height);
	}

	public BufferStrategy getBufferStrategy() {
		return bufferStrategy;
	}

	public Graphics2D getGraphics() {
		return g;
	}
	
	public void setBackgroundColor(Color color) {
		this.panel.setBackground(color);
	}

	public void setTitle(String title) {
		this.frame.setTitle(title);
	}

	public BufferStrategy createBufferStrategy() {
		return null;
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public boolean isVisible() {
		return frame.isVisible();
	}

	public void addListener(GameEventListener listener) {
		if (this.listener == null) {
			this.listener = listener;
			this.canvas.addMouseListener(listener);
			this.canvas.addKeyListener(listener);
			this.frame.addWindowListener(listener);
		}
	}
}
