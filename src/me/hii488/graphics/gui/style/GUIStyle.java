package me.hii488.graphics.gui.style;

import java.awt.Color;
import java.awt.Font;

import me.hii488.dataTypes.Vector;

public class GUIStyle {
	
	public TextStyle textStyle;
	public BackgroundStyle backgroundStyle;
	public MetaStyle metaStyle;
	
	public GUIStyle() {
		textStyle = new TextStyle();
		backgroundStyle = new BackgroundStyle();
	}
	public GUIStyle(MetaStyle s, TextStyle t, BackgroundStyle b) {
		textStyle = t;
		backgroundStyle = b;
		metaStyle = s;
	}
	

	public void overwrite(GUIStyle style) {
		textStyle.overwrite(style.textStyle);
		backgroundStyle.overwrite(style.backgroundStyle);
		metaStyle.overwrite(style.metaStyle);
	}
	
	public static class MetaStyle{
		public Vector position;
		public Vector dimensions;
		
		public MetaStyle(){}
		public MetaStyle(Vector position, Vector dimensions) {
			this.position = position.getLocation();
			this.dimensions = dimensions.getLocation();
		}
		
		public void overwrite(MetaStyle metaStyle) {
			this.position.setLocation(metaStyle.position);
			this.dimensions.setLocation(metaStyle.dimensions);
		}
		
		public Vector getPosition() {
			return position;
		}
		
		public MetaStyle setPosition(Vector position) {
			this.position = position;
			return this;
		}
		
		public Vector getDimensions() {
			return dimensions;
		}
		
		public MetaStyle setDimensions(Vector dimensions) {
			this.dimensions = dimensions;
			return this;
		}
	}
	
	public static class TextStyle{
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

		public void overwrite(TextStyle textStyle) {
			this.horizontalJustification = textStyle.horizontalJustification;
			this.verticalJustification = textStyle.verticalJustification;
			this.font = textStyle.font;
			this.textColor = textStyle.textColor;
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
	
	public static class BackgroundStyle{
		private Color color;
		private String textureKey;
		private int textureState;
		
		public BackgroundStyle() {}
		public BackgroundStyle(Color backgroundColor, String textureKey) {
			this.color = backgroundColor;
			this.textureKey = textureKey;
		}
		
		public void overwrite(BackgroundStyle backgroundStyle) {
			this.color = backgroundStyle.color;
			this.textureKey = backgroundStyle.textureKey;
		}
		
		public Color getBackgroundColor() {
			return color;
		}
		
		public BackgroundStyle setBackgroundColor(Color backgroundColor) {
			this.color = backgroundColor;
			return this;
		}
		
		public String getTextureKey() {
			return textureKey;
		}
		
		public BackgroundStyle setTextureKey(String textureKey) {
			this.textureKey = textureKey;
			return this;
		}
		
		public int getTextureState() {
			return textureState;
		}
		
		public void setTextureState(int textureState) {
			this.textureState = textureState;
		}
	}
	
}
