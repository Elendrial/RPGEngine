package me.hii488.graphics.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.function.Function;

import me.hii488.dataTypes.Vector;

public abstract class GUIElement {

	private boolean hidden = false;
	private String elementName;
	private Vector position;
	private Vector dimensions;
	
	public abstract void render(Graphics g, GUIStyle s);
	public abstract void onClick(MouseEvent e);
	public void onKeyTyped(KeyEvent e){}

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
	
	
	
}
