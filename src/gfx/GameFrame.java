package gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
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

	// public static void main(String[] args) throws InterruptedException {
	// GameFrame gf = new GameFrame(Color.black);
	// gf.g.fillRect(0, 0, gf.width, gf.height);
	// Map<String, ArrayList<BufferedImage>> images = new HashMap<String,
	// ArrayList<BufferedImage>>();
	// new ImageLoader(images);
	// ArrayList<BufferedImage> bahamut = images.get("bahamutANIMATED");
	// do {
	// for (int i = 0; i < bahamut.size(); i++) {
	// gf.g.setColor(Color.black);
	// gf.g.fillRect(0, 0, gf.width, gf.height);
	// BufferedImage img = bahamut.get(i);
	// gf.g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), 0,
	// 0,
	// img.getWidth(), img.getHeight(), Color.black, null);
	// gf.bufferStrategy.show();
	// }
	// } while (gf.bufferStrategy.contentsRestored());
	// // System.out.println("Panel: " + gf.panel.getBounds());
	// // System.out.println("Frame: " + gf.frame.getBounds());
	// // System.out.println("Canvas:" + gf.frame.getBounds());
	// //
	// // for (int R = 0; R < 255; R++) {
	// // for (int B = 0; B < 255; B++) {
	// // for (int G = 0; G < 255; G++) {
	// // gf.g.setColor(Color.black);
	// // gf.g.fillRect(0, 0, gf.width, gf.height);
	// // gf.g.setColor(new Color(R, B, G));
	// // gf.g.fillRect(0, 0, gf.width, gf.height);
	// // gf.bufferStrategy.show();
	// // }
	// // }
	// // }
	//
	// }

	// public static void main(String[] args) throws InterruptedException,
	// MalformedURLException, IOException {
	// GameFrame gf = new GameFrame(Color.black);
	// Graphics2D g = gf.g;
	// BufferStrategy b = gf.bufferStrategy;
	// g.setColor(Color.blue);
	// g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	// RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
	//
	// long end = 0;
	// long start = 0;
	// int spriteX = 0;
	// int spriteY = 0;
	//
	// while (true) {
	//
	// Thread.sleep(1);
	// spriteX++;
	// spriteY++;
	// if (spriteX > 600 && spriteY > 800) {
	// spriteX *= -1;
	// spriteY *= -1;
	// }
	// start = System.nanoTime();
	//
	// int x = (int) (Math.random() * gf.width);
	// int y = (int) (Math.random() * gf.height);
	//
	// int R = (int) (Math.random() * 255);
	// int B = (int) (Math.random() * 255);
	// int G = (int) (Math.random() * 255);
	//
	// g.setColor(Color.black);
	// g.fillRect(0, 0, gf.width, gf.height);
	//
	// g.setColor(Color.white);
	// g.drawString("FPS: " + (1.0 / ((start - end) / 1E+9)), 0, 10);
	// g.setColor(new Color(R, B, G));
	// g.drawString("HELLO", x, y);
	// b.show();
	// end = System.nanoTime();
	// }
	// // g.dispose();
	// }
}
