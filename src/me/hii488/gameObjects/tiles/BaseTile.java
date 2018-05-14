package me.hii488.gameObjects.tiles;

import java.awt.Graphics;

import me.hii488.dataTypes.Grid;
import me.hii488.dataTypes.Vector;
import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.ITexturedObject;

public abstract class BaseTile implements ITexturedObject, IGameObject{
	
	protected String tileName;
	protected Grid<BaseTile> parentGrid;
	protected boolean canPassThrough;
	
	public void setGrid(Grid<BaseTile> g) {
		parentGrid = g;
	}
	
	public Grid<BaseTile> getGrid() {
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
	
	public void render(Graphics g) {}
	public void render(Graphics g, Vector position) {
		g.drawImage(getTexture(), position.getIX(), position.getIY(), null);
	}
}
