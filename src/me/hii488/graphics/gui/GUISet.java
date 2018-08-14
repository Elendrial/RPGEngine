package me.hii488.graphics.gui;

import java.awt.Graphics;
import java.util.HashSet;

public class GUISet {
	private HashSet<GUIElement> elements = new HashSet<GUIElement>();
	private boolean hidden = false;
	private GUIStyle style;
	
	
	public void render(Graphics g) {
		if(!hidden) elements.forEach(e -> {if(!e.isHidden()) e.render(g, style);});
	}
	
	public GUISet addElement(GUIElement e){
		elements.add(e);
		return this;
	}

	public void unhideAll() {
		hidden = false;
	}
	
	public GUIStyle getGUIStyle() {
		return style;
	}
	
	public GUISet setGUIStyle(GUIStyle s) {
		style = s;
		return this;
	}
	
	public enum priority{HIGH, MID, LOW};
	
}
