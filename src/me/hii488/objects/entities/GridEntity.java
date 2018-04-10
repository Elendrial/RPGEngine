package me.hii488.objects.entities;

import me.hii488.misc.Vector;

public abstract class GridEntity extends BaseEntity{
	public Vector gridPosition;
	
	public GridEntity() {
		this(0,0);
	}
	
	public GridEntity(Vector v) {
		super();
		gridPosition = v.getLocation();
	}
	
	public GridEntity(int x, int y) {
		super();
		gridPosition = new Vector(x, y);
	}
	
}
