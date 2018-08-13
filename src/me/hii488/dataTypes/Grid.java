package me.hii488.dataTypes;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import me.hii488.controllers.GameController;
import me.hii488.interfaces.IGameObject;
import me.hii488.interfaces.IRenderable;
import me.hii488.interfaces.ITickable;
import me.hii488.logging.LogSeverity;
import me.hii488.logging.Logger;

public class Grid<T> implements ITickable, IGameObject, IRenderable{
	
	// TODO: Might need to change this as I may need multiple T's in the same location
	// TODO: Maybe have both <T,Vector> and <Vector, T> ? then we have an easy way of finding either from the other?
	private Map<Vector, T> map, updatedMap;
	private Vector dimensions;
	private int gridScale;
	
	public Grid() {
		dimensions = new Vector(0,0);
		map = new HashMap<Vector, T>();
		updatedMap = new HashMap<Vector, T>();
		gridScale = -1;
	}
	
	public Grid(Grid<T> g) {
		this.dimensions = g.dimensions;
				
		g.map.forEach((v, t) -> map.put(v.getIV(), t)); 
	}
	
	public Grid(int x, int y) {
		this();
		setDimensions(x, y);
	}
	
	// Works out scale automatically based on window size and dimensions
	public void autoSetup(int width, int height) {
		setDimensions(width, height);
		setGridScale((GameController.getWindow().width/width));
	}
	
	// works out width/height based on window size and scale
	public void autoSetup(int scale) {
		setGridScale(scale);
		setDimensions(GameController.getWindow().width/scale, GameController.getWindow().height/scale);
	}
	
	public void setDimensions(int size) {
		setDimensions(size, size);
	}
	
	public void setDimensions(int sizeX, int sizeY) {
		dimensions.setX(sizeX); dimensions.setY(sizeY);
	}
	
	public Vector getDimensions() {
		return dimensions.getLocation();
	}

	@Override
	public void updateOnTick() {
		map.values().forEach(t -> {if(t instanceof ITickable) ((ITickable) t).updateOnTick();});
	}

	@Override
	public void updateOnSec() {
		map.values().forEach(t -> {if(t instanceof ITickable) ((ITickable) t).updateOnSec();});
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
	
	public T getObjectAtRealPosition(Vector v) {
		return map.get(v.getLocation().scale(1D/this.gridScale).getIV());
	}
	
	public void setObjectAt(int x, int y, T t) {
		setObjectAt(new Vector(x,y), t);
	}
	
	public void setObjectAt(Vector v, T t) {
		updatedMap.put(v.getIV(), t);
	}
	
	// TODO: Write overloading methods for this.
	public void fillDimensionsWith(int x1, int y1, int x2, int y2, Class<? extends T> c) {
		int nx1, nx2, ny1, ny2;
		if(x1 < x2) {
			nx1 = x1;
			nx2 = x2;
		}
		else {
			nx1 = x2;
			nx2 = x1;
		}
		
		if(y1 < y2) {
			ny1 = y1;
			ny2 = y2;
		}
		else {
			ny1 = y2;
			ny2 = y1;
		}
		
		for(int i = nx1; i < nx2; i++) {
			for(int j = ny1; j < ny2; j++) {
				try {
					setObjectAt(new Vector(i,j), c.newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void clear() {
		this.map.clear();
		this.updatedMap.clear();
	}
	
	public void markToClear() {
		this.updatedMap.clear();
	}

	@Override
	public void render(Graphics g) {
		map.entrySet().stream().forEach(e -> {if(e.getValue() instanceof IRenderable) ((IRenderable) e.getValue()).render(g, e.getKey().getIV().scale(gridScale));});
	}
	
	public Stream<Entry<Vector, T>> stream() {
		return map.entrySet().stream();
	}
	
	public Stream<Entry<Vector, T>> streamUpdates() {
		return updatedMap.entrySet().stream();
	}

	// TODO: Currently is unsafe, change so it throws an error properly or something
	public Vector getPositionOf(T t) {
		return stream().filter(entry -> entry.getValue() == t).findFirst().get().getKey();
	}

	public int getGridScale() {
		return gridScale;
	}
	
	public void setGridScale(int scale) {
		if(gridScale == -1)
			gridScale = scale;
		else 
			Logger.getDefault().print(LogSeverity.MESSAGE, "Grid scale already set, it cannot be overriden.");
	}
	
	public int getWidth() {
		return dimensions.getIX();
	}
	
	public int getHeight() {
		return dimensions.getIY();
	}

}
