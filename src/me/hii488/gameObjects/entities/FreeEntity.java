package me.hii488.gameObjects.entities;

import java.awt.Graphics;

import me.hii488.dataTypes.Vector;
import me.hii488.dataTypes.VectorBox;

public abstract class FreeEntity extends BaseEntity{
	protected Vector position;
	
	public FreeEntity() {
		super();
	}
	
	public Vector getPosition() {
		return position.getLocation();
	}

	public void setPosition(Vector position) {
		this.position = position.getLocation();
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getTexture(), position.getIX(), position.getIY(), null);
	}
	
	@Override
	public VectorBox getCollisionArea() {
		return new VectorBox(position, position.getLocation().translate(getTexture().getWidth(null), getTexture().getHeight(null)));
	}
	
	// TODO: Centre on x pos, y pos, pos functions.
	
}
