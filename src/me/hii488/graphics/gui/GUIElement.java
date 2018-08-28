package me.hii488.graphics.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import me.hii488.dataTypes.KeyBind;
import me.hii488.dataTypes.Vector;
import me.hii488.dataTypes.VectorBox;
import me.hii488.graphics.gui.style.GUIStyle;

public abstract class GUIElement {
	
	protected GUISet parentGuiSet;
	protected GUIStyle style;
	protected boolean hidden = false;
	protected String elementName;
	protected Vector position;
	protected Vector dimensions;
	
	public abstract void render(Graphics g);
	// These don't _need_ to be implemented, but may need to be in specific situations.
	public void onClick(MouseEvent e) {}
	public void onUnclick(MouseEvent e) {} // IE: When anywhere else on the screen is clicked.
	public void onKeyTyped(KeyBind e) {}

	public void setHidden(boolean b) {
		hidden = b;
	}
	
	public void hide() {
		hidden = true;
	}
	
	public void show() {
		hidden = false;
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
	
	public void setStyle(GUIStyle style) {
		this.style = style;
	}
	
	public VectorBox getBoundingBox() {
		return new VectorBox(position, dimensions);
	}
	
	public GUISet getParentGuiSet() {
		return parentGuiSet;
	}
	
}
