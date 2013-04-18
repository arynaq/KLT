package engine;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
	public FontLoader() {

		InputStream is = FontLoader.class
				.getResourceAsStream("/fonts/visitor2.ttf");
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		// getAvailableFontFamilyNames()
	}

}
