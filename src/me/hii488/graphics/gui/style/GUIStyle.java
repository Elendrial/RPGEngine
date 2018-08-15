package me.hii488.graphics.gui.style;

import java.awt.Color;
import java.awt.Font;

public class GUIStyle {
	
	public TextStyle textStyle;
	public BackgroundStyle backgroundStyle;
	
	public GUIStyle() {}
	public GUIStyle(TextStyle t, BackgroundStyle b) {
		textStyle = t;
		backgroundStyle = b;
	}
	
	public class TextStyle{
		public int horizontalJustification; // -1 = left, 0 = centred, 1 = right
		public int verticalJustification;  // -1 = down, 0 = centred, 1 = up
		public Font font;
		public Color textColor;
		
		public TextStyle() {}
		public TextStyle(Color c, Font f, int horiJustification, int vertJustification) {
			setTextColor(c);
			setFont(f);
			setHorizontalJustification(horiJustification);
			setVerticalJustification(vertJustification);
		}

		public Color getTextColor() {
			return textColor;
		}

		public TextStyle setTextColor(Color textColor) {
			this.textColor = textColor;
			return this;
		}

		public Font getFont() {
			return font;
		}

		public TextStyle setFont(Font f) {
			this.font = f;
			return this;
		}

		public int getHorizontalJustification() {
			return horizontalJustification;
		}

		public TextStyle setHorizontalJustification(int justification) {
			this.horizontalJustification = justification;
			return this;
		}

		public int getVerticalJustification() {
			return verticalJustification;
		}

		public TextStyle setVerticalJustification(int verticalJustification) {
			this.verticalJustification = verticalJustification;
			return this;
		}
	}
	
	public class BackgroundStyle{
		public Color backgroundColor;
		public String textureLocation;
		
		public BackgroundStyle() {}
		public BackgroundStyle(Color backgroundColor, String textureLocation) {
			this.backgroundColor = backgroundColor;
			this.textureLocation = textureLocation;
		}
		
		public Color getBackgroundColor() {
			return backgroundColor;
		}
		
		public BackgroundStyle setBackgroundColor(Color backgroundColor) {
			this.backgroundColor = backgroundColor;
			return this;
		}
		
		public String getTextureLocation() {
			return textureLocation;
		}
		
		public BackgroundStyle setTextureLocation(String textureLocation) {
			this.textureLocation = textureLocation;
			return this;
		}
	}
	
}
