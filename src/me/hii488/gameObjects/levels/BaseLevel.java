package me.hii488.gameObjects.levels;

import java.awt.Graphics;
import java.util.ArrayList;

import me.hii488.EngineSettings;
import me.hii488.dataTypes.Grid;
import me.hii488.dataTypes.Vector;
import me.hii488.dataTypes.VectorBox;
import me.hii488.gameObjects.entities.BaseEntity;
import me.hii488.gameObjects.entities.FreeEntity;
import me.hii488.gameObjects.entities.GridEntity;
import me.hii488.gameObjects.tiles.BaseTile;
import me.hii488.graphics.gui.GUI;
import me.hii488.handlers.InputHandler;
import me.hii488.interfaces.IGUIAnchor;
import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.IRenderable;
import me.hii488.interfaces.ITickable;

// Does this need to be abstract? I'm saying no, hence the change.
public class BaseLevel implements ITickable, IGameObject, IRenderable, IGUIAnchor{
	
	// GRID
	private Grid<BaseTile> tileGrid;
	
	// ENTITY GRID - TODO: determine if this is really necessary, the constraints could be in GridEntity itself?
	private Grid<GridEntity> entityGrid;
	
	// FREE FLOATING ENTITIES
	private ArrayList<FreeEntity> entities;
	private ArrayList<FreeEntity> entitiesToAdd;
	private ArrayList<FreeEntity> entitiesToDelete;
	
	// GUI
	private GUI gui;
	
	public BaseLevel() {
		tileGrid = new Grid<BaseTile>();
		entityGrid = new Grid<GridEntity>();
		
		entities = new ArrayList<FreeEntity>();
		entitiesToAdd = new ArrayList<FreeEntity>();
		entitiesToDelete = new ArrayList<FreeEntity>();
		
		tileGrid.setLevel(this);
		entityGrid.setLevel(this);
		
		gui = new GUI();
		InputHandler.addInputListener(gui);
	}
	
	@Override
	public void onLoad() {
		updateLevelContents();
		
		getTileGrid().onLoad();
		getEntityGrid().onLoad();
		entities.forEach(e -> e.onLoad());
	}
	
	@Override
	public void onUnload() {
		getTileGrid().onUnload();
		getEntityGrid().onUnload();
		entities.forEach(e -> e.onUnload());
	}
	
	@Override
	public void updateOnTick() {
		getTileGrid().updateOnTick();
		getEntityGrid().updateOnTick();
		entities.forEach(e -> {if(e instanceof ITickable) ((ITickable) e).updateOnTick();});
	}
	
	@Override
	public void updateOnSec() {
		getTileGrid().updateOnSec();
		getEntityGrid().updateOnSec();
		entities.forEach(e -> {if(e instanceof ITickable) ((ITickable) e).updateOnSec();});
	}
	
	@Override
	public void endOfTick() {
		entities.forEach(e -> {if(e instanceof ITickable) ((ITickable) e).endOfTick();});
		updateLevelContents();
	}
	
	public void updateLevelContents() {
		getTileGrid().streamUpdates().forEach(e -> e.getValue().setParentGrid(getTileGrid()));
		getTileGrid().endOfTick();
		getEntityGrid().endOfTick();
		
		entities.removeAll(entitiesToDelete);
		entitiesToDelete.forEach(e -> e.setParentLevel(null));
		
		entities.addAll(entitiesToAdd);
		
		entitiesToAdd.clear();
		entitiesToDelete.clear();
	}
	
	public void addEntity(BaseEntity e) {
		if(e instanceof FreeEntity)	entitiesToAdd.add((FreeEntity) e);
		else {
			getEntityGrid().setObjectAt(((GridEntity) e).getGridPosition(), (GridEntity) e);
			((GridEntity) e).setParentGrid(getEntityGrid());
		}
		e.setParentLevel(this);
	}
	
	public void removeEntity(BaseEntity e) {
		if(e instanceof FreeEntity)	entitiesToDelete.add((FreeEntity) e);
		else getEntityGrid().removeObject((GridEntity) e);
	}
	
	public void render(Graphics g) {
		getTileGrid().render(g);
		getEntityGrid().render(g);
		
		entities.forEach(e -> e.render(g));
		
		gui.render(g);
	}
	

	public Grid<BaseTile> getTileGrid() {
		return tileGrid;
	}

	public Grid<GridEntity> getEntityGrid() {
		return entityGrid;
	}

	public ArrayList<FreeEntity> getFreeEntities() {
		return entities;
	}

	// Should _not_ return the entity passed within the list.
	public ArrayList<BaseEntity> getIntersectingEntities(BaseEntity e) {
		ArrayList<BaseEntity> collidingWith = new ArrayList<BaseEntity>();
		
		VectorBox cb = e.getCollisionArea();
		
		entities.stream().filter(entity -> cb.intersectsArea(entity.getCollisionArea())).forEach(collidingWith::add);
		entityGrid.stream().filter(entry -> cb.intersectsArea(entry.getValue().getCollisionArea())).forEach(entry -> {collidingWith.add(entry.getValue());});
		
		return collidingWith;
	}

	// TODO: Test that this works correctly.
	public ArrayList<BaseTile> getIntersectingTiles(BaseEntity e) {
		ArrayList<BaseTile> tiles = new ArrayList<BaseTile>();
		VectorBox cb = e.getCollisionArea();
		
		VectorBox gridBox = new VectorBox(cb);
		gridBox.scale(1/EngineSettings.Texture.tileSize);
		for(int i = gridBox.getLowerLeftCorner().getIX(); i < gridBox.getLowerRightCorner().getX(); i++) {
			for(int j = gridBox.getLowerLeftCorner().getIY(); j < gridBox.getUpperRightCorner().getY(); j++) {
				tiles.add(tileGrid.getObjectAt(i, j));
			}
		}
		
		return tiles;
	}
	
	public GUI getGUI() {
		return gui;
	}

	@Override
	public Vector getPosition() {
		return Vector.ORIGIN; // Assume the top left of the level is 0,0.
	}
	
}
