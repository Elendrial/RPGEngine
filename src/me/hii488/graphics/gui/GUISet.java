package me.hii488.graphics.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.HashSet;

import me.hii488.dataTypes.KeyBind;
import me.hii488.graphics.gui.style.GUIStyleGroup;

public class GUISet implements Comparable<GUISet>{
	private boolean hidden = false;
	private HashSet<GUIElement> elements = new HashSet<GUIElement>();
	private GUIStyleGroup style;
	private Priority priority;
	
	
	public void render(Graphics g) {
		if(!hidden) elements.forEach(e -> {if(!e.isHidden()) e.render(g);});
	}
	
	public GUISet addElement(GUIElement e){
		elements.add(e);
		if(e.style == null) e.setStyle(style.getDefault());
		return this;
	}

	public void unhideAll() {
		hidden = false;
	}
	
	public GUIStyleGroup getGUIStyle() {
		return style;
	}
	
	public GUISet setGUIStyle(GUIStyleGroup s) {
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

	public void mouseClicked(MouseEvent event) {
		elements.stream().filter(e -> !e.hidden).filter(e -> e.getBoundingBox().containsPoint(event.getX(), event.getY())).forEach(e -> e.onClick(event));
	}

	public void keyTyped(KeyBind event) {
		elements.forEach(e -> e.onKeyTyped(event));
	}
	
}
