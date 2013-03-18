package gfx;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import javax.swing.ImageIcon;

/**
 * This class will encapsulate the raw images we need to draw on the screen,
 * this will also serve as a base class for Sprites and animatedSprites.
 * 
 * @author Maki
 * 
 */
public class GameImage {

	private int x;
	private int y;
	private BufferedImage img;
	private int imgHeight;
	private int imgWidth;

	public GameImage(String file) {
		loadImage(file);
		this.x = 0;
		this.y = 0;
	}

	public synchronized int getX() {
		return x;
	}

	public synchronized int getY() {
		return y;
	}

	/**
	 * Return a copy of the image, we don't want you to mess with my objects
	 * 
	 * @return
	 */
	public Image getImgCopy() {
		return deepCopy((BufferedImage) img);
	}

	public BufferedImage getImg() {
		return img;
	}

	public int getWidth() {
		return imgWidth;
	}

	public int getHeight() {
		return imgHeight;
	}


	public synchronized void setX(int x) {
		this.x = x;
	}

	public synchronized void setY(int y) {
		this.y = y;
	}

	public void setImg(BufferedImage img) {
		this.img = img;

	}

	public void loadImage(String file) {
		ImageIcon imageIcon = new ImageIcon(file);
		this.img = (BufferedImage) imageIcon.getImage();
		this.imgHeight = imageIcon.getIconHeight();
		this.imgWidth = imageIcon.getIconWidth();
	}

	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	/**********************************
	 * DOES NOT GET TO PRODUCTION
	 **********************************/
	public Rectangle getBounds() {
		return new Rectangle(x, y, getWidth(), getHeight());
	}

}
