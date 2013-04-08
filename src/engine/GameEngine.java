package engine;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import sfx.GameSound;


public class GameEngine {
	private Map<String, Entity> entities;

	public GameEngine(Map<String, Entity> entities,
			Map<String, ArrayList<BufferedImage>> images,
			Map<String, GameSound> sounds) {

		this.entities = entities;

	}

	public void start() {
		
	}

	public void update() {
		// TODO Auto-generated method stub

	}



}
