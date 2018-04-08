package me.hii488.graphics.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import me.hii488.Settings;
import me.hii488.interfaces.IInputUser;

public class GUI implements IInputUser{
	protected ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
	public String identifier = "";
	
	public void render(Graphics g){
		for(GUIElement e : elements) {
			if(Settings.Logging.debug) {
				g.setColor(Color.RED);
				g.drawRect(e.position.getIX()-1, e.position.getIY()-1, e.dimensions.getIX()+2, e.dimensions.getIY()+2);
			}
			e.render(g);
		}
	}
	
	public GUI addElement(GUIElement e){
		elements.add(e);
		sortList();
		return this;
	}
	
	public ArrayList<GUIElement> getElements(){
		return elements;
	}
	
	public void sortList(){
		Collections.sort(elements);
	}
	
	public void empty() {
		elements.clear();
	}
	
	public void mouseClicked(MouseEvent arg0) {
		for(GUIElement e : elements){
			if(!e.hidden) {
				if(arg0.getX() - e.position.getX() < e.dimensions.getX() && arg0.getX() > e.position.getX()){
					if(arg0.getY() - e.position.getY() < e.dimensions.getY() && arg0.getY() > e.position.getY()){
						e.onClick(arg0);
					}
				}
			}
		}
	}
	
	public void keyTyped(KeyEvent event){
		for(GUIElement e : elements){
			e.onKeyTyped(event);
		}
	}
	
	public void hideAll(){
		for(GUIElement e : elements) e.hidden = true;
	}
	
	public void showAll(){
		for(GUIElement e : elements) e.hidden = false;
	}

	public String getIdentifier() {
		return identifier;
	}

	public GUI setIdentifier(String identifier) {
		this.identifier = identifier;
		return this;
	}
	
}
