package me.hii488.gameObjects.entities;

import java.awt.Color;
import java.awt.Graphics;

import me.hii488.dataTypes.Vector;
import me.hii488.dataTypes.VectorBox;

public abstract class FreeEntity extends BaseEntity{
	protected Vector position;
	
	public FreeEntity() {
		super();
	}
	
	public Vector getPosition() {
		return position.getCopy();
	}

	public void setPosition(Vector position) {
		this.position = position.getCopy();
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getTexture(), position.getIX(), position.getIY(), null);
		
		VectorBox b = getCollisionArea();
		Color c = g.getColor();
		g.setColor(Color.red);
		g.drawRect(b.getUpperLeftCorner().getIX(), b.getUpperLeftCorner().getIY(), (int) b.getWidth(), (int) b.getHeight());
		g.setColor(c);
	}
	
	@Override
	public VectorBox getCollisionArea() {
		return new VectorBox(position, position.getCopy().translate(getTextureWidth(), getTextureHeight()));
	}
	
	// TODO: Centre on x pos, y pos, pos functions.
	
}
