package me.hii488.graphics.gui.premadeTypes;

import java.awt.Graphics;

import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.GUISet;
import me.hii488.graphics.gui.style.GUIStyle;

public class GUIOption extends GUIStandardBox {

	protected GUIOptionBox parentBox;
	
	public GUIOption() {super();}
	public GUIOption(GUIStyle s) {
		super(s);
	}
	
	public void onSelect() {}
	
	protected void setParentGUISet(GUISet s) {
		this.parentGuiSet = s;
	}
	
	public void render(Graphics g) {
		super.render(g, Vector.ORIGIN);
	}
	
}
