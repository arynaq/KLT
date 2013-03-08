package worldmap;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class MapGUI extends GraphicsProgram{
	
	private final String fileDir = getClass().getResource(
			"../images/worldmap/").getPath();
	
	public void drawBoard(int x, int y,int gameTile){
		System.out.println("Inni GUIdraw");
		GImage tile = new GImage(fileDir + gameTile + ".jpg");
		add(tile, x * 16, y * 16);
	}

}
