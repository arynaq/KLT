package gfx;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gukern {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel = (JPanel) frame.getContentPane();
		frame.setSize(1000, 1000);
		frame.setBackground(Color.red);
		frame.update(frame.getGraphics());
		frame.setVisible(true);
	}

}
