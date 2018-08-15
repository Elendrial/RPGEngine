package me.hii488.graphics.gui;

import java.awt.Graphics;
import java.util.HashSet;

public class GUISet implements Comparable<GUISet>{
	private boolean hidden = false;
	private HashSet<GUIElement> elements = new HashSet<GUIElement>();
	private GUIStyle style;
	private Priority priority;
	
	
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
	
	
	public enum Priority{HIGH, MID, LOW};

	@Override
	public int compareTo(GUISet e) {
		if(e.priority.ordinal() > this.priority.ordinal())
            return 1;
        else if(e.priority.ordinal() < this.priority.ordinal())
            return -1;
        else
        	return 0;
	}
	
}
