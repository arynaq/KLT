package klt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WorldMap {
	private int[][] worldMap = new int[32][32];
	
	public WorldMap(){
		try{
			String worldFil = "C:/Users/Ferd/workspace/KLT/src/worldmap.txt";
			Scanner readLevel = new Scanner(new File(worldFil));
			while (readLevel.hasNextInt()){
				for(int y=0;y<32;y++){
					for(int x = 0;x<32;x++){
						worldMap[x][y]=readLevel.nextInt(); 
					}
				}
				
			}
			readLevel.close();
		}catch(FileNotFoundException e){ 
			System.out.println("File not found.");
		}	
	}
	public int getMap(int x, int y){
		return worldMap[x][y];
	}
	public void setMap(int x, int y, int change){
		worldMap[x][y]=change;		
	}
}
