package engine;

import worldmap.WorldMap;

public class Game {

	WorldMap map;
	public Game(){
		map = new WorldMap();
	}

	public static void main(String[] args) {
		Game game = new Game();
		while (true)
			Render.renderMap(game.map);
	}

}
