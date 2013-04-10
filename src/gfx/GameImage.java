package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Wrapper for single-frame images, since every image is loaded into arraylist
 * 
 * @author Maki
 * 
 */
public class GameImage {
	private BufferedImage image;
	
	public GameImage(ArrayList<BufferedImage> image) {
		if (image == null) {
			System.out.println("Cannot construct image from null");
		}

		else if (image.size() != 1) {
			System.out.println("Cannot construct single-frame images from multiple frames");
			System.out.println("Attempting to use the first frame");
			this.image = image.get(0);
		}
		else {
			this.image = image.get(0);
		}
	}

	public BufferedImage getImage() {
		return image;
	}

}
