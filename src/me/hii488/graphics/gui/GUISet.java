package me.hii488.graphics.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

import me.hii488.dataTypes.KeyBind;
import me.hii488.graphics.gui.style.GUIStyleGroup;
import me.hii488.logging.LogSeverity;
import me.hii488.logging.Logger;

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
		e.parentGuiSet = this;
		if(e.style == null) e.setStyle(style.getDefault(), true);
		return this;
	}
	
	public void removeElement(GUIElement e) {
		elements.remove(e);
	}
	
	public void removeElement(String s) {
		removeElement(getElement(s));
	}
	
	public void hideAllWithTag(String tag) {
		for(GUIElement e : elements) if(e.hasTag(tag)) e.hide();
	}
	
	public void showAllWithTag(String tag) {
		for(GUIElement e : elements) if(e.hasTag(tag)) e.show();
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
	
	public HashSet<GUIElement> getElements(){
		return elements;
	}
	
	public GUIElement getElement(String name) {
		for(GUIElement e : elements) if(e.elementName.equals(name)) return e;
		Logger.getDefault().print(LogSeverity.ERROR, "No element with the name " + name + " contained in this GUISet");
		return null;
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

	public boolean mouseClicked(MouseEvent event) {
		AtomicBoolean affected = new AtomicBoolean(false);
		elements.stream().filter(e -> !e.hidden).filter(e -> e.getBoundingBox().containsPoint(event.getX(), event.getY())).forEach(e -> {affected.set(affected.get() || e.onClick(event));});
		
		return affected.get();
	}

	public void keyTyped(KeyBind event) {
		elements.forEach(e -> e.onKeyTyped(event));
	}
	
}
