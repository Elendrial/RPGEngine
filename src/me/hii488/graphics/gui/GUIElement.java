package me.hii488.graphics.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import me.hii488.dataTypes.KeyBind;
import me.hii488.dataTypes.Vector;
import me.hii488.dataTypes.VectorBox;
import me.hii488.graphics.Camera;
import me.hii488.graphics.gui.style.GUIStyle;
import me.hii488.interfaces.IGUIAnchor;
import me.hii488.registries.TextureRegistry;

public abstract class GUIElement implements IGUIAnchor{
	
	protected GUISet parentGuiSet;
	protected GUIStyle style = GUIStyle.getDefault();
	protected boolean hidden = false;
	protected String elementName;
	protected Vector position;
	protected Vector dimensions;
	protected ArrayList<String> tags = new ArrayList<String>();
	protected IGUIAnchor anchor = Camera.get();
	private BufferedImage backgroundPicture;
	
	public abstract void render(Graphics g);
	// These don't _need_ to be implemented, but may need to be in specific situations.
	public boolean onClick(MouseEvent e) {return false;}
	public boolean onClick(MouseEvent e, Vector ingameLocation) {return onClick(e);}
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
	
	// IE: getPositionFromAnchor()
	public Vector getPosition() {
		return position;
	}
	
	public Vector getRealPositionInLevel() {
		return anchor.getRealPositionFromAnchoredPosition(getPosition());
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
	
	public GUIElement setAnchor(IGUIAnchor a) {
		anchor = a;
		return this;
	}
	
	public IGUIAnchor getAnchor() {
		return anchor;
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
	
	// Here so that no one tries to use the other methods below to get this element's various positions
	public Vector getRealPosition() {
		return anchor.getRealPositionFromAnchoredPosition(getPosition());
	}
	
	public Vector getScreenPosition() {
		return anchor.getScreenPositionFromAnchoredPosition(getPosition());
	}
	
	// Changed these to deal with nested anchors.
	/**
	 * Gets the real, in world, position from a position that uses this element as an anchor.
	 * WARNING: THESE ARE ONLY FOR OBJECTS THAT USE THIS ELEMENT AS AN ANCHOR
	 */
	public Vector getRealPositionFromAnchoredPosition(Vector v) {
		return anchor.getRealPositionFromAnchoredPosition(getPosition()).translate(v);
	}
	
	/**
	 * Gets the real, in world, position from a position that uses this element as an anchor.
	 * WARNING: THESE ARE ONLY FOR OBJECTS THAT USE THIS ELEMENT AS AN ANCHOR
	 */
	public Vector getRealPositionFromAnchoredPosition(int x, int y) {
		return anchor.getRealPositionFromAnchoredPosition(getPosition()).translate(x,y);
	}
	
	/**
	 * Gets the anchored position from a "real", in world, position. Uses this element as the anchor.
	 * WARNING: THESE ARE ONLY FOR OBJECTS THAT USE THIS ELEMENT AS AN ANCHOR
	 */
	public Vector getAnchoredPositionFromRealPosition(Vector v) {
		return anchor.getAnchoredPositionFromRealPosition(v).translate(getPosition().negated());
	}
	
	/**
	 * Gets the anchored position from a "real", in world, position. Uses this element as the anchor.
	 * WARNING: THESE ARE ONLY FOR OBJECTS THAT USE THIS ELEMENT AS AN ANCHOR
	 */
	public Vector getAnchoredPositionFromRealPosition(int x, int y) {
		return anchor.getAnchoredPositionFromRealPosition(x,y).translate(getPosition().negated());
	}
	
}
