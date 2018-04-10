package me.hii488.graphics.GUI;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUIChoiceBox extends GUILabel{
	
	public ArrayList<String> options;
	public int selected;
	
	public GUIChoiceBox() {
		super();
		options = new ArrayList<String>();
		selected = 0;
	}
	
	public GUIChoiceBox(String... choices) {
		this();
		addChoices(choices);
	}
	
	public void onKeyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_KP_UP) {
			if(selected > 0) selected--;
			else selected = options.size()-1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			selected++;
			selected %= options.size();
		}
	}
	
	public GUIChoiceBox setChoices(String... choices) {
		options.clear();
		addChoices(choices);
		return this;
	}
	
	public GUIChoiceBox addChoices(String... choices) {
		for(String s : choices)
			options.add(s);
		
		text = "";
		for(int i = 0; i < options.size(); i++)
			text += ( i == selected ? ">" : " ") + "  " + options.get(i);
		
		
		return this;
	}
	
}
