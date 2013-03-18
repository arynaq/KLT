package gfx;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

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
		this.defaultMode = new DisplayMode(800, 600, 16, 60);
		this.setSupportedModes();
		this.frame = new JFrame();
	}

	private void setSupportedModes() {
		this.supportedModes = gd.getDisplayModes();
	}

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

	// Set fullscreen if supported displaymode, else default
	private void setFullScreen(DisplayMode displayMode, JFrame frame) {

		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setIgnoreRepaint(true);
		gd.setFullScreenWindow(frame);

		if (gd.isFullScreenSupported()) {

			if (displayMode == null || !isSupportedDisplayMode(displayMode))
				gd.setDisplayMode(defaultMode);

			else
				gd.setDisplayMode(displayMode);
			frame.createBufferStrategy(2);
		}
	}
	
	// Make windowed
	public void setWindowed() {
		if (gd.getFullScreenWindow() != null) {
			gd.getFullScreenWindow().dispose();
		}
		gd.setFullScreenWindow(null);
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
		ScreenManager sm = new ScreenManager(new JFrame());
		sm.setWindowed();
	}


}