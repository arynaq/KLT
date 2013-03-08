package worldObjects;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;
public class MainClass extends GraphicsProgram{
	WorldMap dritern = new WorldMap();

	public void run(){
		drawBoard(14,14);
	}
	
	public void drawBoard(int playerX, int playerY) {
		int xDraw = ((int) Math.floor(playerX/16.0 / 1.0));
		int yDraw = ((int) Math.floor(playerY/16.0 / 1.0));
		for(int y=(1*(yDraw)*16); y<16+(yDraw*16); y++) {
			for(int x=(1*(xDraw)*16); x<16+(xDraw*16); x++) {
				int gameTile = dritern.getMap(x,y);
				GImage tile = new GImage("C:/Users/Ferd/workspace/KLT/src/tiles/"+gameTile+".jpg");
				add(tile, (x-xDraw*16)*16,(y-yDraw*16)*16);
			}
		}
	}
}

