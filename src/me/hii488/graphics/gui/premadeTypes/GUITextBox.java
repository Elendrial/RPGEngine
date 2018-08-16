package me.hii488.graphics.gui.premadeTypes;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import me.hii488.graphics.gui.GUIElement;
import me.hii488.graphics.gui.style.GUIStyle;

public class GUITextBox extends GUIElement {
	
	public String text;
	
	public GUITextBox() {}
	public GUITextBox(GUIStyle s) {
		style = s;
	}
	
	@Override
	public void render(Graphics g) {
		// Background rendering
		g.setColor(style.backgroundStyle.getBackgroundColor());
		// TODO: Load picture at some stage and render here (after background colour, before text).
		
		// Text rendering
		g.setColor(style.textStyle.textColor);
		g.setFont(style.textStyle.font);
		
		// TODO: Think about changing this so that this is all set to local vars when the style is set/changes.
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		
		String[] s = text.replace("\t", "    ").split("\n");
		double x,y;
		
		for(int i = 0; i < s.length; i++){
			if(style.textStyle.horizontalJustification == 0)       x = position.getX() + (dimensions.getX() - metrics.stringWidth(s[i])) / 2;
			else if(style.textStyle.horizontalJustification == -1) x = position.getX();
			else                                  				   x = position.getX() + dimensions.getX() - metrics.stringWidth(s[i]);
			
			if(style.textStyle.verticalJustification == 0)       y = position.getY() + ((dimensions.getY() - metrics.getHeight()) / 2) + ((i-s.length/2) * metrics.getHeight()) + metrics.getAscent(); // TODO: Make this centre better
			else if(style.textStyle.verticalJustification == -1) y = position.getY() + dimensions.getY() + metrics.getHeight() * (i-s.length+1); 
			else                               					 y = position.getY() - metrics.getHeight() * (i-s.length+1); 
			g.drawString(s[i], (int)x, (int)y);
		}
		
	}

	@Override
	public void onClick(MouseEvent e) {}

	public GUITextBox setText(String s) {
		text = s;
		return this;
	}
	
}
