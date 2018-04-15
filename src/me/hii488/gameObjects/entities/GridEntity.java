package me.hii488.gameObjects.entities;

import me.hii488.dataTypes.Vector;

public abstract class GridEntity extends BaseEntity{
	protected Vector position;

	public Vector getPosition() {
		return position.getLocation();
	}

	public void setPosition(Vector position) {
		this.position = position.getLocation();
	}
	
}
