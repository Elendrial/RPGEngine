package me.hii488.graphics.gui.premadeTypes;

import me.hii488.dataTypes.KeyBind;
import me.hii488.graphics.gui.GUIElement;

public abstract class GUIOptionBox extends GUIElement{
	
	protected int currentSelection = 0;
	protected boolean listHorizontal = false; // Whether to list options vertically or horizontally
	
	protected abstract int optionAmount();
	
	@Override
	public void onKeyTyped(KeyBind e){
		if(listHorizontal) {
			if(e.equals(KeyBind.MOVE_RIGHT)) currentSelection++;
			else if(e.equals(KeyBind.MOVE_LEFT)) currentSelection--;
			currentSelection %= optionAmount();
			if(currentSelection < 0) currentSelection += optionAmount();
		}
		else {
			if(e.equals(KeyBind.MOVE_DOWN)) currentSelection++;
			else if(e.equals(KeyBind.MOVE_UP)) currentSelection--;
			currentSelection %= optionAmount();
			if(currentSelection < 0) currentSelection += optionAmount();
		}
	}
	
	public void setSelected(int selection) {
		currentSelection = selection;
	}
	
}
