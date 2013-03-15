package gfx;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class will encapsulate the raw images we need to draw on the scren, this
 * will also serve as a base class for Sprites and animatedSprites.
 * 
 * @author Maki
 * 
 */
public class GameImage {

	private int x;
	private int y;
	private BufferedImage img;

	public GameImage(String file) {
		loadImage(file);
		this.x = 0;
		this.y = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public BufferedImage getImg() {
		return img;
	}

	public int getWidth() {
		if (img != null)
			return img.getWidth();
		return 0;
	}

	public int getHeight() {
		if (img != null)
			return img.getHeight();
		return 0;
	}


	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setImg(BufferedImage img) {
		this.img = img;

	}

	public void loadImage(String file) {
		try {
			this.img = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**********************************
	 * DOES NOT GET TO PRODUCTION
	 **********************************/
	public Rectangle getBounds() {
		return new Rectangle(x, y, getWidth(), getHeight());
	}

}
