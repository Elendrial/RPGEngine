package me.hii488.graphics.gui.premadeTypes;

import java.awt.Graphics;
import java.util.ArrayList;

import me.hii488.dataTypes.KeyBind;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.GUIElement;
import me.hii488.graphics.gui.style.GUIStyle;

public class GUIOptionBox extends GUIElement{
	
	protected int currentSelection = 0;
	protected ArrayList<GUIOption> options = new ArrayList<GUIOption>();
	
	public GUIOptionBox() {}
	public GUIOptionBox(GUIStyle s) {
		style = s;
	}
	
	public void addOption(GUIOption o) {
		options.add(o);
		o.setParentGUISet(getParentGuiSet());
		o.parentBox = this;
	}
	
	public void positionOptions(boolean vertical) {
		if(vertical) {
			Vector offset = new Vector(0,0);
			double yDist = (position.getIY() - options.stream().mapToDouble(o -> (o.getBoundingBox().getHeight())).sum()) / (options.size() + 1);
			
			for(GUIOption option : options) {
				offset.setX((this.getBoundingBox().getWidth() - option.getBoundingBox().getWidth())/2);
				offset.translate(0, yDist);
				
				option.setPosition(position.getLocation().translate(offset));
			}
		}
		else {
			Vector offset = new Vector(0,0);
			double xDist = (position.getIX() - options.stream().mapToDouble(o -> (o.getBoundingBox().getWidth())).sum()) / (options.size() + 1);
			
			for(GUIOption option : options) {
				offset.setY((this.getBoundingBox().getHeight() - option.getBoundingBox().getHeight())/2);
				offset.translate(xDist, 0);
				
				option.setPosition(position.getLocation().translate(offset));
			}
		}
	}
	
	@Override
	public void onKeyTyped(KeyBind e){

		if (e.equals(KeyBind.MOVE_RIGHT) || e.equals(KeyBind.MOVE_DOWN))
			currentSelection++;
		
		else if (e.equals(KeyBind.MOVE_LEFT) || e.equals(KeyBind.MOVE_UP))
			currentSelection--;
		
		currentSelection %= options.size();
		
		if (currentSelection < 0)
			currentSelection += options.size();
		
		if(e.equals(KeyBind.INTERACT))
			options.get(currentSelection).onSelect();

	}
	
	public void setSelected(int selection) {
		currentSelection = selection;
	}

	@Override
	public void render(Graphics g) {
		options.forEach(o -> o.render(g));
	}
	
}
