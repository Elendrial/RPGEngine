package me.hii488.graphics.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import me.hii488.interfaces.IInputListener;

public class GUI implements IInputListener{
	private ArrayList<GUISet> sets= new ArrayList<GUISet>();
	private boolean allHidden = false;
	
	public void render(Graphics g){
		if(!allHidden) sets.forEach(s -> s.render(g));
	}
	
	public GUI addGUISet(GUISet s) {
		sets.add(s);
		Collections.sort(sets);
		return this;
	}
	
	public void clear() {
		sets.clear();
	}
	
	public void hideAll() {
		allHidden = true;
	}
	
	// Not the same as show all, this just lifts the blanket hide.
	public void unhideAll() {
		allHidden = false;
	}
	
	public void showAll() {
		allHidden = false;
		sets.forEach(s -> s.unhideAll());
	}
	
}
