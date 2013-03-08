package worldmap;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class MainClass extends GraphicsProgram{
	WorldMap dritern = new WorldMap();
	MapGUI mapGui = new MapGUI();
	private final String fileDir = getClass().getResource(
			"../images/worldmap/").getPath();

	public void run(){
		constructMap(0, 0);
	}

	public void constructMap(int playerX, int playerY) {
		int skjermen[][] = new int[16][16];
		int xDraw = ((int) Math.floor(playerX / 16.0));
		int yDraw = ((int) Math.floor(playerY / 16.0));
		for (int y = (yDraw*16); y < 16 + (yDraw * 16); y++) {
			for (int x = (xDraw*16); x < 16 + (xDraw * 16); x++) {
				skjermen[x-(16*xDraw)][y-(16*yDraw)]=dritern.getMap(x, y);
				drawMap(x-(16*xDraw),y-(16*yDraw),skjermen[x-(16*xDraw)][y-(16*yDraw)]);
			}
		}
	}
	public void drawMap(int x, int y,int gameTile){
		GImage tile = new GImage(fileDir + gameTile + ".jpg");
		add(tile, x * 16, y * 16);
	}
}


