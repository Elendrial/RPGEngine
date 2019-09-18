package me.hii488.graphics.gui.premadeTypes;

import java.awt.Canvas;
import java.awt.FontMetrics;
import java.awt.Graphics;
import me.hii488.dataTypes.Vector;
import me.hii488.graphics.gui.GUIElement;
import me.hii488.graphics.gui.style.GUIStyle;

public class GUIStandardBox extends GUIElement {
	
	protected String[] text;
	protected Vector[] textRenderPositions;
	
	public GUIStandardBox() {setText("");}
	public GUIStandardBox(GUIStyle s) {
		setStyle(s, true);
		setText("");
	}
	
	@Override
	public void render(Graphics g) {
		render(g, Vector.ORIGIN);
	}
	
	public void render(Graphics g, Vector offset) {
		Vector renderPos = anchor.getRealPositionFromAnchoredPosition(getPosition());
		
		// Background rendering
		if(style.backgroundStyle.getBackgroundColor() != null) {
			g.setColor(style.backgroundStyle.getBackgroundColor());
			g.fillRect(renderPos.getIX() + offset.getIX(), renderPos.getIY() + offset.getIY(), this.dimensions.getIX(), this.dimensions.getIY());
		}
		
		if(getBackGroundPicture() != null) g.drawImage(getBackGroundPicture(), renderPos.getIX() + offset.getIX(), renderPos.getIY() + offset.getIY(), this.dimensions.getIX(), this.dimensions.getIY(), null);
		
		if(style.backgroundStyle.getBorderColor() != null) {
			System.out.println("bordc");
			g.setColor(style.backgroundStyle.getBorderColor());
			g.drawRect(renderPos.getIX() + offset.getIX(), renderPos.getIY() + offset.getIY(), this.dimensions.getIX(), this.dimensions.getIY());
		}
		
		// Text rendering
		g.setColor(style.textStyle.textColor);
		g.setFont(style.textStyle.font);

		for(int i = 0; i < text.length; i++) {
			g.drawString(text[i], textRenderPositions[i].getIX() + offset.getIX(), textRenderPositions[i].getIY() + offset.getIY());
		}
	}
	
	public void updateTextRenderPosition() {
		textRenderPositions = new Vector[text.length];
		
		FontMetrics metrics = new Canvas().getFontMetrics(style.textStyle.getFont());
		Vector renderPos = anchor.getRealPositionFromAnchoredPosition(getPosition());
		
		double x,y;
		for(int i = 0; i < text.length; i++){
			if(style.textStyle.horizontalJustification == 0)       x = renderPos.getX() + (dimensions.getX() - metrics.stringWidth(text[i])) / 2;
			else if(style.textStyle.horizontalJustification == -1) x = renderPos.getX();
			else                                  				   x = renderPos.getX() + dimensions.getX() - metrics.stringWidth(text[i]);
			
			if(style.textStyle.verticalJustification == 0)       y = renderPos.getY() + ((dimensions.getY() - metrics.getHeight()) / 2) + ((i-text.length/2) * metrics.getHeight()) + metrics.getAscent(); // TODO: Make this centre better
			else if(style.textStyle.verticalJustification == -1) y = renderPos.getY() + dimensions.getY() + metrics.getHeight() * (i-text.length+1); 
			else                               					 y = renderPos.getY() - metrics.getHeight() * (i-text.length+1);
			
			textRenderPositions[i] = new Vector(x,y);
		}
	}
	
	public GUIStandardBox setText(String s) {
		text = s.replace("\t", "    ").split("\n");
		updateTextRenderPosition();
		return this;
	}
	
	public GUIElement setPosition(Vector v) {
		position = v.getIV();
		updateTextRenderPosition();
		return this;
	}
	
	public GUIElement setDimensions(Vector v) {
		dimensions = v.getIV();
		updateTextRenderPosition();
		return this;
	}
	
}
