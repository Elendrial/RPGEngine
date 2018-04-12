package me.hii488.dataTypes;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.IRenderable;
import me.hii488.interfaces.ITicking;

public class Grid<T> implements ITicking, IGameObject, IRenderable{
	
	private Map<Vector, T> map;
	private Vector dimensions;
	
	public Grid() {
		dimensions = new Vector(0,0);
		map = new HashMap<Vector, T>();
	}
	
	public Grid(Grid<T> g) {
		this.dimensions = g.dimensions;
				
		g.map.forEach((v, t) -> map.put(v.getLocation(), t)); 
	}
	
	public Grid(int x, int y) {
		this();
		setDimensions(x, y);
	}
	
	public void setDimensions(int size) {
		setDimensions(size, size);
	}
	
	public void setDimensions(int sizeX, int sizeY) {
		dimensions.setX(sizeX); dimensions.setY(sizeY);
	}

	@Override
	public void updateOnTick() {
		map.values().forEach(t -> {if(t instanceof ITicking) ((ITicking) t).updateOnTick();});
	}

	@Override
	public void updateOnSec() {
		map.values().forEach(t -> {if(t instanceof ITicking) ((ITicking) t).updateOnSec();});
	}

	@Override
	public void onLoad() {
		map.values().forEach(t -> {if(t instanceof IGameObject) ((IGameObject) t).onLoad();});
	}

	@Override
	public void onUnload() {
		map.values().forEach(t -> {if(t instanceof IGameObject) ((IGameObject) t).onUnload();});
	}
	
	public T getObjectAt(int x, int y) {
		return getObjectAt(new Vector(x,y));
	}
	
	public T getObjectAt(Vector v) {
		return map.get(v);
	}

	@Override
	public void render(Graphics g) {
		
	}

}
