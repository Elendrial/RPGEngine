package me.hii488.gameObjects.levels;

import java.awt.Graphics;
import java.util.ArrayList;

import me.hii488.dataTypes.Grid;
import me.hii488.gameObjects.entities.BaseEntity;
import me.hii488.gameObjects.tiles.BaseTile;
import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.IRenderable;
import me.hii488.interfaces.ITicking;

public abstract class BaseLevel implements ITicking, IGameObject, IRenderable{
	
	// GRID
	private Grid<BaseTile> tileGrid;
	
	// ENTITY GRID
	private Grid<BaseEntity> entityGrid;
	
	// FREE FLOATING ENTITIES
	private ArrayList<BaseEntity> entities;
	private ArrayList<BaseEntity> entitiesToAdd;
	private ArrayList<BaseEntity> entitiesToDelete;
	
	public BaseLevel() {
		tileGrid = new Grid<BaseTile>();
		entityGrid = new Grid<BaseEntity>();
		entities = new ArrayList<>();
	}
	
	@Override
	public void onLoad() {
		tileGrid.onLoad();
		entityGrid.onLoad();
	}
	
	@Override
	public void onUnload() {
		tileGrid.onUnload();
		entityGrid.onUnload();
	}
	
	@Override
	public void updateOnTick() {
		tileGrid.updateOnTick();
		entityGrid.updateOnTick();
	}
	
	@Override
	public void updateOnSec() {
		tileGrid.updateOnSec();
		entityGrid.updateOnSec();
	}
	
	@Override
	public void endOfTick() {
		tileGrid.endOfTick();
		entityGrid.endOfTick();
	}
	
	public void addEntity(BaseEntity e) {
		entitiesToAdd.add(e);
	}
	
	public void removeEntity(BaseEntity e) {
		entitiesToDelete.add(e);
	}
	
	public void render(Graphics g) {
		tileGrid.render(g);
		entityGrid.render(g);
	}
	
}
