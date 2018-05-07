package me.hii488.dataTypes;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.IRenderable;
import me.hii488.interfaces.ITicking;

public class Grid<T> implements ITicking, IGameObject, IRenderable{
	
	// TODO: Might need to change this as I may need multiple T's in the same location
	private Map<Vector, T> map, updatedMap;
	private Vector dimensions;
	
	public Grid() {
		dimensions = new Vector(0,0);
		map = new HashMap<Vector, T>();
		updatedMap = new HashMap<Vector, T>();
	}
	
	public Grid(Grid<T> g) {
		this.dimensions = g.dimensions;
				
		g.map.forEach((v, t) -> map.put(v.getIV(), t)); 
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
	public void endOfTick() {
		updatedMap.entrySet().stream().forEach(e -> map.put(e.getKey(), e.getValue()));
		updatedMap.clear();
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
	
	public void setObjectAt(int x, int y, T t) {
		setObjectAt(new Vector(x,y), t);
	}
	
	public void setObjectAt(Vector v, T t) {
		updatedMap.put(v.getIV(), t);
	}

	@Override
	public void render(Graphics g) {
		map.entrySet().stream().forEach(e -> {if(e.getValue() instanceof IRenderable) ((IRenderable) e.getValue()).render(g, e.getKey());});
	}
	
	public Stream<Entry<Vector, T>> stream() {
		return map.entrySet().stream();
	}

}
