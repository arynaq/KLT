package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {
	private ArrayList<BufferedImage> images;

	public SpriteSheet(ArrayList<BufferedImage> images, int northRow,
			int southRow, int westRow, int eastRow) {
		this.images = images;
	}
}
