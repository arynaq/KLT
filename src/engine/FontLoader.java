package engine;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
	public FontLoader() {
		loadFonts();

	}

	private void loadFonts() {
		String[] fontNames = new String[] { "visitor2.ttf",
				"Commodore Pixelized v1.2.ttf", "m04.ttf", "m04b.ttf",
				"manaspc.ttf" };
		for (String f : fontNames) {
			InputStream is = FontLoader.class.getResourceAsStream("/fonts/" + f);
			try {
				Font font = Font.createFont(Font.TRUETYPE_FONT, is);
				GraphicsEnvironment ge = GraphicsEnvironment
						.getLocalGraphicsEnvironment();
				ge.registerFont(font);
			} catch (FontFormatException | IOException e) {
				e.printStackTrace();
			}

		}

		// getAvailableFontFamilyNames()
	}

}
