package engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ImageLoader {

	private Map<String, ArrayList<BufferedImage>> images;
	private String SPRITEDIR = "/sprites/";
	private String MAPDIR = "/maps/";
	private String PORTRAITDIR = "/images/portrait/";


	public ImageLoader(String imageListFile,
			Map<String, ArrayList<BufferedImage>> imgs) {
		this.images = imgs;
		loadImages(imageListFile);
	}

	/**
	 * Loads all the image files in the format specified by imagelistfile, puts
	 * them in hashmap
	 */
	public void loadImages(String imagelistFile) {
		Scanner readFile = null;
		InputStream is = getClass().getResourceAsStream(imagelistFile);
		readFile = new Scanner(is);

		while (readFile.hasNextLine()) {
			String line = readFile.nextLine();
			// annotation or empty
			if (line.startsWith("#") || line.isEmpty())
				continue;
			// Sprite
			if (line.startsWith("s")) {
				String[] strings = line.split("\\s+");
				loadSprite(strings[1], strings[2]);
			}
			// AnimatedSprite
			if (line.startsWith("a")) {
				String[] strings = line.split("\\s+");
				loadAnimatedSprite(strings[1], strings[2], strings[3],
						strings[4]);
			}
			// Worldmap
			if (line.startsWith("w")) {
				String[] strings = line.split("\\s+");
				loadWorldMap(strings[1], strings[2]);
			}
			// Indoor map
			if (line.startsWith("i")) {
				String[] strings = line.split("\\s+");
				loadIndoorMap(strings[1], strings[2]);
			}
			// Characterportrait
			if (line.startsWith("p")) {
				String[] strings = line.split("\\s+");
				loadCharacterPortrait(strings[1], strings[2]);
			}

            // SplashBackGround
			if (line.startsWith("b")){
                String[] strings = line.split("\\s+");
                loadSplashBackground(strings[1], strings[2]);
			}

		}
		readFile.close();
	}

	
    private void loadSplashBackground(String key, String fileName) {
        key += "BACKGROUND";
        ArrayList<BufferedImage> list = new ArrayList<BufferedImage>(1);
        list.add(loadIMG(SPRITEDIR + fileName));
        images.put(key, list);
    }

    private void loadCharacterPortrait(String key, String fileName) {
		key += "PORTRAIT";
		ArrayList<BufferedImage> list = new ArrayList<BufferedImage>(1);
		list.add(loadIMG(PORTRAITDIR + fileName));
		images.put(key, list);
	}
	
	/** Loads the indoor map of buildings */
	private void loadIndoorMap(String key, String fileName) {
		key += "INDOOR";
		ArrayList<BufferedImage> list = new ArrayList<BufferedImage>(1);
		list.add(loadIMG(MAPDIR + fileName));
		images.put(key, list);
	}
	
	/** Loads the worldmap */
	private void loadWorldMap(String key, String filename) {
		key += "WORLDMAP";
		ArrayList<BufferedImage> list = new ArrayList<BufferedImage>(1);
		list.add(loadIMG(MAPDIR + filename));
		images.put(key, list);
	}

	/**
	 * Loads a single sprite, puts a single image in an arraylist, puts that in
	 * hashmap
	 */
	private void loadSprite(String key, String fileName) {
		key += "SPRITE";
		ArrayList<BufferedImage> list = new ArrayList<BufferedImage>(1);
		list.add(loadIMG(SPRITEDIR + fileName));
		images.put(key, list);
	}

	/** Loads the animated sprite, puts an ararylist of frames in the hashmap */
	private void loadAnimatedSprite(String key, String fileName, String row,
			String col) {
		key += "ANIMATED";
		ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();
		BufferedImage img = loadIMG(SPRITEDIR + fileName);
		BufferedImage subimg = null;
        int frameWidth = img.getWidth() / Integer.valueOf(row);
        int frameHeight = img.getHeight() / Integer.valueOf(col);

		
		for(int i=0; i<Integer.valueOf(col); i++)
			for (int j = 0; j < Integer.valueOf(row); j++) {

				subimg = img.getSubimage(j * frameWidth, i * frameHeight,
						frameWidth, frameHeight);
				list.add(subimg);
			}
		images.put(key, list);
	}


	/** Helper method for loading images */
	public BufferedImage loadIMG(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedImage img = ImageIO.read(is);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

