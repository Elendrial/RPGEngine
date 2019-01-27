package me.hii488.graphics.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import me.hii488.dataTypes.KeyBind;
import me.hii488.dataTypes.Vector;
import me.hii488.dataTypes.VectorBox;
import me.hii488.graphics.gui.style.GUIStyle;
import me.hii488.registries.TextureRegistry;

public abstract class GUIElement {
	
	protected GUISet parentGuiSet;
	protected GUIStyle style = GUIStyle.getDefault();
	protected boolean hidden = false;
	protected String elementName;
	protected Vector position;
	protected Vector dimensions;
	protected ArrayList<String> tags = new ArrayList<String>();
	private BufferedImage backgroundPicture;
	
	public abstract void render(Graphics g);
	// These don't _need_ to be implemented, but may need to be in specific situations.
	public boolean onClick(MouseEvent e) {return false;}
	public void onUnclick(MouseEvent e) {} // IE: When anywhere else on the screen is clicked.
	public void onKeyTyped(KeyBind e) {}

	public void setHidden(boolean b) {
		hidden = b;
	}
	
	public void hide() {
		hidden = true;
		unloadBackgroundPicture();
	}
	
	public void show() {
		hidden = false;
		loadBackgroundPicture();
	}
	
	public boolean isHidden() {
		return hidden;
	}
	
	public GUIElement setPosition(Vector v) {
		position = v.getIV();
		return this;
	}
	
	public GUIElement setDimensions(Vector v) {
		dimensions = v.getIV();
		return this;
	}
	
	public GUIElement setElementName(String i){
		elementName = i;
		return this;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public Vector getDimensions() {
		return dimensions;
	}
	
	public String getElementName() {
		return elementName;
	}
	
	public GUIStyle getStyle() {
		return style;
	}
	
	public void setStyle(GUIStyle style, boolean overWriteMeta) {
		this.style.overwrite(style);
		
		if(overWriteMeta) {
			this.position = style.metaStyle.getPosition();
			this.dimensions = style.metaStyle.getDimensions();
		}
	}
	
	public VectorBox getBoundingBox() {
		return new VectorBox(position, dimensions.getX(), dimensions.getY() );
	}
	
	public GUISet getParentGuiSet() {
		return parentGuiSet;
	}
	
	public void addTag(String s) {
		tags.add(s);
	}
	
	public void removeTag(String s) {
		tags.remove(s);
	}
	
	public void clearTags() {
		tags.clear();
	}

	public boolean hasTag(String s) {
		return tags.contains(s);
	}
	
	public BufferedImage getBackGroundPicture() {
		if(backgroundPicture == null) return fetchBackGroundPic();
		return backgroundPicture;
	}
	
	private void loadBackgroundPicture() {
		backgroundPicture = fetchBackGroundPic();
	}
	
	private void unloadBackgroundPicture() {
		backgroundPicture = null;
	}
	
	private BufferedImage fetchBackGroundPic() {
		if(style.backgroundStyle.getTextureKey() != null)
			return (BufferedImage) TextureRegistry.getTexture(style.backgroundStyle.getTextureKey(), style.backgroundStyle.getTextureState());
		return null;
	}
	
}
