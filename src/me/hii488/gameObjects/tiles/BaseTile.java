package me.hii488.gameObjects.tiles;

import java.awt.Graphics;

import me.hii488.dataTypes.Grid;
import me.hii488.dataTypes.Vector;
import me.hii488.gameObjects.entities.GridEntity;
import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.ITexturedObject;

public abstract class BaseTile implements ITexturedObject, IGameObject{
	
	protected String tileName;
	protected Grid<GridEntity> parentGrid;
	
	public void setGrid(Grid<GridEntity> g) {
		parentGrid = g;
	}
	
	public Grid<GridEntity> getGrid() {
		return parentGrid;
	}
	
	public String getTileName() {
		return tileName;
	}
	
	public void render(Graphics g) {}
	public void render(Graphics g, Vector position) {
		g.drawImage(getTexture(), position.getIX(), position.getIY(), null);
	}
}
