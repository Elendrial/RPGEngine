package me.hii488.gameObjects.entities;

import java.awt.Color;
import java.awt.Graphics;

import me.hii488.EngineSettings;
import me.hii488.dataTypes.Grid;
import me.hii488.dataTypes.Vector;
import me.hii488.dataTypes.VectorBox;

public abstract class GridEntity extends BaseEntity{
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
	}
	
	public Vector getAbsPosition() {
		Vector gridPos = getGridPosition();
		int scale = parentGrid.getGridScale();
		
		return new Vector((gridPos.getX()) * scale - getTextureWidth()/2, (gridPos.getY()) * scale - getTextureHeight()/2);
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
		int tileSize = EngineSettings.Texture.tileSize;
		
		g.drawImage(getTexture(), absPos.getIX() + (tileSize - getTextureWidth()) / 2, absPos.getIY() + tileSize - getTextureHeight(), null);
		VectorBox b = getCollisionArea();
		Color c = g.getColor();
		g.setColor(Color.red);
		g.drawRect(b.getUpperLeftCorner().getIX(), b.getUpperLeftCorner().getIY(), (int) b.getWidth(), (int) b.getHeight());
		g.setColor(c);
	}
}
