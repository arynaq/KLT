package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
	private ArrayList<BufferedImage> images;
	private int rows;
	private int columns;
	private int size;

	public SpriteSheet(ArrayList<BufferedImage> images, int northRow,
			int southRow, int westRow, int eastRow) {
		this.images = images;
		this.size = images.size();
	}

	public SpriteSheet(List<BufferedImage> images, int rows, int columns) {
		this.images = new ArrayList<BufferedImage>(images);
		this.size = images.size();
		this.rows = rows;
		this.columns = columns;
	}

	public ArrayList<BufferedImage> getImages() {
		return images;
	}

	// Returns the number of frames in this spritesheet
	public int getSize() {
		return size;
	}

	public BufferedImage getFrame(int frameNumber) {
		BufferedImage img = null;
		img = this.images.get(frameNumber);
		return img;
	}

	/**
	 * Returns a spritesheet containing only the images on a select row of this
	 * spritesheet. Rownumbering starts at 0 (first row)
	 * 
	 * @param row
	 *            - The rownumber
	 * @return
	 */
	public SpriteSheet getRow(int row) {
		int startIndex = row * (columns);
		int endIndex = startIndex + 4;
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>(
				this.images.subList(startIndex, endIndex));

		return new SpriteSheet(images, 1, columns);
	}
}
