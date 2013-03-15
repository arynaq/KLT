package gfx;

import javax.swing.JFrame;

import worldmap.WorldMap;

public abstract class Render extends JFrame {

	public static void renderMap(WorldMap map) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		String abc = "abcdefghijklmnopqrstvwxyz";
		for(char c : abc.toCharArray())
			System.out.println((int) c);
	}

}
