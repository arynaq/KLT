package worldmap;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.GameState;


public class WorldMap {

	private BufferedImage image;
	private ArrayList<ArrayList<BufferedImage>> subMaps;
	private GameMap currentMap;

	public WorldMap(ArrayList<BufferedImage> images) {
		this.image = images.get(0);
		this.subMaps = new ArrayList<ArrayList<BufferedImage>>();
		setSubMaps();
	}

	/**
	 * Loads the different GameMaps that are constructed from this worldmap
	 * 
	 */
	private void setSubMaps() {
		int w = (int) GameState.getInstance().getFrameWidth();
		int h = (int) GameState.getInstance().getFrameHeight();
		for (int i = 0; i < image.getWidth() / w; i++) {
			subMaps.add(new ArrayList<BufferedImage>());
			for (int j = 0; j < image.getHeight() / h; j++) {
				BufferedImage submap = image.getSubimage(j * w, i * h, w, h);
				subMaps.get(i).add(submap);
			}
		}

		currentMap = new GameMap(subMaps.get(0).get(0));
	}


	public GameMap getGameMap() {
		return currentMap;
	}

	/*
	 * Find which submap the player is, then update the current map to that map
	 */

	public void updateGameMap(int x, int y) {
		int i = y / GameState.getInstance().getFrameHeight();
		int j = x / GameState.getInstance().getFrameWidth();
		currentMap.setImage(subMaps.get(i).get(j));
	}

}
