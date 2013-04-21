package engine;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
	public FontLoader(String fontName) {
		InputStream is = FontLoader.class
				.getResourceAsStream("/fonts/" + fontName);
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			// String dufern[] = ge.getAvailableFontFamilyNames();
			// for (int i = 0; i < dufern.length; i++) {
			// System.out.println(dufern[i]);
			// }
			ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}


}
