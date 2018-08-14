package me.hii488.graphics.gui;

import java.awt.Graphics;

import me.hii488.dataTypes.Vector;

public abstract class GUIElement {

	private boolean hidden = false;
	private Vector position;
	
	public abstract void render(Graphics g, GUIStyle s);

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
	
}
