package engine;

import java.awt.image.BufferedImage;

import worldmap.MapType;

public class CollisionDetector {
	private BufferedImage collisionMap;
	private MapType mapID;

	public CollisionDetector() {
		this.mapID = MapType.OUT1;
		loadCollisionMap(mapID);
	}

	private void loadCollisionMap(String mapID) {

	}
}
