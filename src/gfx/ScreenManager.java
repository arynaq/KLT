package gfx;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JMenu;

public class ScreenManager {

	private JFrame frame;
	private GraphicsDevice gd;
	private DisplayMode defaultMode;
	private DisplayMode[] supportedModes;
	
	
	// Use with frame from elsewhere
	public ScreenManager(JFrame frame) {
		this();
		this.frame = frame;

	}


	// Used with a frame that is tied to instance
	public ScreenManager() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.gd = ge.getDefaultScreenDevice();
		setDefaultMode(new DisplayMode(800, 600, 16, 60));
		this.setSupportedModes();
		this.frame = new JFrame();
	}

	// Get the supported displayrates from current graphicsdevice
	private void setSupportedModes() {
		this.supportedModes = gd.getDisplayModes();
	}

	// Check if the supplied displaymode is supported by current device
	public boolean isSupportedDisplayMode(DisplayMode odm) {
		for (DisplayMode dm : this.supportedModes) {
			if (dm.getHeight() == odm.getHeight()
					&& dm.getWidth() == odm.getWidth()
					&& dm.getBitDepth() == odm.getBitDepth()
					|| odm.getBitDepth() == DisplayMode.BIT_DEPTH_MULTI
					&& dm.getRefreshRate() == odm.getBitDepth()
					|| odm.getRefreshRate() == DisplayMode.REFRESH_RATE_UNKNOWN)
				return true;

		}
		return false;
	}
	
	public void setFullScreen(DisplayMode displayMode) {
		this.setFullScreen(displayMode, frame);
	}

	public DisplayMode getDefaultMode() {
		return defaultMode;
	}

	public void setDefaultMode(DisplayMode defaultMode) {
		this.defaultMode = defaultMode;
	}

	// Set fullscreen if supported displaymode, else default displaymode
	public void setFullScreen(DisplayMode displayMode, JFrame frame) {
		if (gd.isFullScreenSupported()) {
			// Fullscreen on visible frame not allowed
			frame.setVisible(false);
			// Remove decoration and unresiable
			frame.setUndecorated(true);
			frame.setResizable(false);
			frame.setIgnoreRepaint(true);
			// Set frame as fullscreenwindow
			gd.setFullScreenWindow(frame);
			// Set default if requested not supported or null
			if (displayMode == null || !isSupportedDisplayMode(displayMode))
				gd.setDisplayMode(defaultMode);

			else
				gd.setDisplayMode(displayMode);
			// Create bufferstrategy
			frame.createBufferStrategy(2);
		}
	}
	
	// Make windowed
	public void setWindowed() {
		// // Windowed from fullscreen if fullscreen, otherwise we are probably
		// // windowed already
		if (gd.getFullScreenWindow() != null) {
			// gd.getFullScreenWindow().dispose();
			gd.setFullScreenWindow(null);
			// frame.setUndecorated(false);
			frame.setVisible(true);
		}
	}

	// Get the drawing graphics of this ScreenManagers bufferstrategy
	public Graphics2D getGraphics() {
		Window frame = gd.getFullScreenWindow();
		if (frame != null) {
			BufferStrategy bufferStrategy = frame.getBufferStrategy();
			return (Graphics2D) bufferStrategy.getDrawGraphics();
		}
		
		return null;
	}

	public void update() {
		Window frame = gd.getFullScreenWindow();
		if (frame != null) {
			BufferStrategy bufferStrategy = frame.getBufferStrategy();
			if (!bufferStrategy.contentsLost()) 
				bufferStrategy.show();
		}

		Toolkit.getDefaultToolkit().sync();
	}

	// Display in readable format, eg 800x600x32@60
	public String displayModeToString(DisplayMode dm) {
		return dm.getWidth() + "x" + dm.getHeight() + "x" + dm.getBitDepth()
				+ "@" + dm.getRefreshRate();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JFrame frame2 = new JFrame();
		frame.setSize(new Dimension(800, 600));
		frame2.setSize(new Dimension(600, 200));
		frame.setVisible(true);
		JMenu menu = new JMenu();
		menu.setVisible(true);
		frame.add(menu);
		frame2.setVisible(true);
	}

	// public static void main(String[] args) throws InterruptedException {
	// JFrame frame = new JFrame();
	// JPanel panel = (JPanel) frame.getContentPane();
	// panel.setPreferredSize(new Dimension(800, 600));
	// panel.setLayout(null);
	//
	// Canvas canvas = new Canvas();
	// canvas.setBounds(0, 0, 800, 600);
	// canvas.setIgnoreRepaint(true);
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//
	// frame.pack();
	// frame.setResizable(false);
	// frame.setVisible(true);
	//
	// panel.add(canvas);
	// panel.setBackground(Color.black);
	// canvas.createBufferStrategy(2);
	//
	// BufferStrategy bufferStrategy = canvas.getBufferStrategy();
	// Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
	//
	// canvas.requestFocus();
	// canvas.setBackground(Color.black);
	//
	// for (int i = 0; i < 50; i++) {
	// g.setColor(new Color(5 * i, 2 * i, 3 * i));
	// g.drawRect(0, 0, i * 16, i * 10);
	// }
	// g.setBackground(Color.black);
	// bufferStrategy.show();
	// g.dispose();
	//
	// }


}