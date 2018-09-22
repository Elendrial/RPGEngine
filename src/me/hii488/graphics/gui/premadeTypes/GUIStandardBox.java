package me.hii488.graphics.gui.premadeTypes;

import java.awt.Canvas;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.GUIElement;
import me.hii488.graphics.gui.style.GUIStyle;

public class GUIStandardBox extends GUIElement {
	
	protected String[] text;
	protected Vector[] textRenderPositions;
	
	public GUIStandardBox() {setText("");}
	public GUIStandardBox(GUIStyle s) {
		setStyle(s);
		setText("");
	}
	
	@Override
	public void render(Graphics g) {
		// Background rendering
		if(style.backgroundStyle.getBackgroundColor() != null) g.setColor(style.backgroundStyle.getBackgroundColor());
		if(getBackGroundPicture() != null) g.drawImage(getBackGroundPicture(), this.position.getIX(), this.position.getIY(), this.dimensions.getIX(), this.dimensions.getIY(), null);
		
		// Text rendering
		g.setColor(style.textStyle.textColor);
		g.setFont(style.textStyle.font);
		
		for(int i = 0; i < text.length; i++) {
			g.drawString(text[i], textRenderPositions[i].getIX(), textRenderPositions[i].getIY());
		}
	}
	
	public void updateTextRenderPosition() {
		textRenderPositions = new Vector[text.length];
		
		FontMetrics metrics = new Canvas().getFontMetrics(style.textStyle.getFont());
		
		double x,y;
		
		for(int i = 0; i < text.length; i++){
			if(style.textStyle.horizontalJustification == 0)       x = position.getX() + (dimensions.getX() - metrics.stringWidth(text[i])) / 2;
			else if(style.textStyle.horizontalJustification == -1) x = position.getX();
			else                                  				   x = position.getX() + dimensions.getX() - metrics.stringWidth(text[i]);
			
			if(style.textStyle.verticalJustification == 0)       y = position.getY() + ((dimensions.getY() - metrics.getHeight()) / 2) + ((i-text.length/2) * metrics.getHeight()) + metrics.getAscent(); // TODO: Make this centre better
			else if(style.textStyle.verticalJustification == -1) y = position.getY() + dimensions.getY() + metrics.getHeight() * (i-text.length+1); 
			else                               					 y = position.getY() - metrics.getHeight() * (i-text.length+1);
			
			textRenderPositions[i] = new Vector(x,y);
		}
	}

	@Override
	public void onClick(MouseEvent e) {}

	public GUIStandardBox setText(String s) {
		text = s.replace("\t", "    ").split("\n");
		updateTextRenderPosition();
		return this;
	}
	
}
