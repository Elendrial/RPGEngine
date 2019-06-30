package me.hii488.gameObjects.tiles;

import java.awt.Graphics;

import me.hii488.dataTypes.Grid;
import me.hii488.dataTypes.IGridObject;
import me.hii488.dataTypes.Vector;
import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.ITexturedObject;

public abstract class BaseTile implements ITexturedObject, IGameObject, IGridObject{
	
	protected String tileName;
	protected Grid<BaseTile> parentGrid;
	protected boolean canPassThrough;
	
	@SuppressWarnings("unchecked")
	public void setParentGrid(Grid<? extends IGridObject> g) {
		parentGrid = (Grid<BaseTile>) g;
	}
	
	public Grid<BaseTile> getParentGrid() {
		if(parentGrid == null) throw new RuntimeException("No grid set.");
		return parentGrid;
	}
	
	public String getTileName() {
		return tileName;
	}
	
	public boolean canPassThrough() {
		return canPassThrough;
	}
	
	public void setPassThrough(boolean b) {
		canPassThrough = b;
	}
	
	public Vector getGridPosition() {
		return parentGrid.getPositionOf(this).getLocation();
	}
	
	public Vector getPosition() {
		return getGridPosition().scale(parentGrid.getGridScale());
	}
	
	public void render(Graphics g) {}
	public void render(Graphics g, Vector position) {
		g.drawImage(getTexture(), position.getIX(), position.getIY(), null);
		render(g);
	}
	
	public void onReplace() {}
	
}
