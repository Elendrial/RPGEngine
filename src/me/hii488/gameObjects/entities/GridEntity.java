package me.hii488.gameObjects.entities;

import java.awt.Graphics;

import me.hii488.dataTypes.Grid;
import me.hii488.dataTypes.IGridObject;
import me.hii488.dataTypes.Vector;
import me.hii488.dataTypes.VectorBox;

public abstract class GridEntity extends BaseEntity implements IGridObject{
	// Partially unsure about whether this is really needed at all, as it could be implemented entirely in free entity without a grid... this does ensure there aren't two in one spot though.
	
	protected Vector gridPosition;
	protected Grid<GridEntity> parentGrid;

	public GridEntity() {
		super();
	}
	
	public GridEntity(Vector v) {
		this();
		gridPosition = v.getIV();
	}
	
	public GridEntity(int x, int y) {
		this();
		gridPosition = new Vector(x,y);
	}
	
	public void setGrid(Grid<GridEntity> g) {
		parentGrid = g;
	}
	
	public Grid<GridEntity> getGrid() {
		return parentGrid;
	}
	
	public Vector getGridPosition() {
		return gridPosition.getLocation();
	}

	public void setGridPosition(Vector position) {
		this.gridPosition = position.getLocation();
		if(parentGrid != null) this.parentGrid.moveObject(this, position);
	}
	
	public void setGridPosition(int  x, int y) {
		this.gridPosition = new Vector(x, y);
	}
	
	/**
	 * @return The top left corner of the grid space in which the entity is.
	 */
	public Vector getAbsPosition() {
		Vector gridPos = getGridPosition();
		int scale = parentGrid.getGridScale();
		
		return gridPos.getLocation().scale(scale);
	}

	@Override
	public VectorBox getCollisionArea() {
		Vector absPos = getAbsPosition();
		
		return new VectorBox(absPos, absPos.getLocation().translate(getTextureWidth(), getTextureHeight()));
	}
	
	
	// will probably have to render these "bottom up". IE: position + tileSize - thisTextureSize

	@Override
	public void render(Graphics g) {
		Vector absPos = getAbsPosition();
		int tileSize = parentGrid.getGridScale(); // Grid may not line up with tiles.
		
		// Render in the middle of the grid 'tile'
		g.drawImage(getTexture(), absPos.getIX() + (tileSize - getTextureWidth()) / 2, absPos.getIY() + tileSize - getTextureHeight(), null);
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof GridEntity)) return false;
		
		GridEntity e = (GridEntity) o;
		if(!e.entityName.equals(entityName)) return false;
		if(!e.gridPosition.equals(gridPosition)) return false;
		if(e.parentLevel != parentLevel) return false; // Can use == here rather than .equals because it needs to literally be the same object, not just something that's functionally the same
		
		return true;
	}
	
	public int hashCode() {
		return entityName.hashCode() + 7*gridPosition.hashCode();
	}
	
}
