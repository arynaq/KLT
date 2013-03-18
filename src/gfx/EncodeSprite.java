package gfx;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

//Primite class that simply encodes an integer pair (i,j) into the (0,0) pixel.
//Also handles decoding.
public class EncodeSprite {
	BufferedImage img;
	URL file;
	
	public EncodeSprite(String file) {
		loadImage(getClass().getResource("../images/sprites/" + file));
	}

	public EncodeSprite(BufferedImage img) {
		this.img = img;
	}

	private void loadImage(URL file) {
		try {
			this.img = ImageIO.read(file);
			this.file = file;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveImage(String format) {
		try {
			ImageIO.write(img, format, new File(file.getPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void encode(int col, int row) {
		WritableRaster raster = this.img.getRaster();
		int[] pixelData = { col, row, 0, 0 };
		raster.setPixel(0, 0, pixelData);
		saveImage("png");
		System.out.println("Successfully encoded!");
	}
	
	public int[] decode() {
		WritableRaster raster = this.img.getRaster();
		int[] rowCol = new int[4];
		raster.getPixel(0, 0, rowCol);
		int[] ret = { rowCol[0], rowCol[1] };
		System.out.println("Successfully decoded");
		return ret;
	}

	 public static void main(String[] args) {
		long zeroTime = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
		(new Thread() {
			  public void run() {
				EncodeSprite e = new EncodeSprite("player.png");
				e.encode(211, 10);
				EncodeSprite ee = new EncodeSprite("player.png");
				int[] a = ee.decode();
			  }
		}).start();
		}

		long oneTime = System.currentTimeMillis() - zeroTime;
		System.out.println(oneTime);
	 }

}
