package me.hii488.gameObjects.entities;

import java.awt.Graphics;

import me.hii488.dataTypes.Grid;
import me.hii488.dataTypes.Vector;

public abstract class GridEntity extends BaseEntity{
	// Partially unsure about whether this is really needed at all, as it could be implemented entirely in free entity without a grid... this does ensure there aren't two in one spot though.
	
	protected Vector gridPosition;
	protected Grid<GridEntity> parentGrid;

	public GridEntity() {
		super();
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
	}
	
	public Vector getAbsPosition() {
		// TODO
		return null;
	}
	
	// will probably have to render these "bottom up". IE: position + tileSize - thisTextureSize


	@Override
	public void render(Graphics g) {
		// TODO
	}
}
