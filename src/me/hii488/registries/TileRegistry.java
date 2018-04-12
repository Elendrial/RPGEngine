package me.hii488.registries;

import java.util.HashMap;

import me.hii488.gameObjects.tiles.BaseTile;

public class TileRegistry{
	
	protected static HashMap<String, Class<? extends BaseTile>> tiles = new HashMap<String,  Class<? extends BaseTile>>();
	
	public static void registerTile(BaseTile b){
		if(!tiles.containsKey(b.getTileName())) tiles.put(b.getTileName(), b.getClass());
	}
	
	public static void registerTile(String s, Class<? extends BaseTile> b) {
		if(!tiles.containsKey(s)) tiles.put(s, b);
	}
	
	public static BaseTile getTile(String identifier){
		try {
			return (BaseTile) tiles.get(identifier).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean contains(String identifier){
		return tiles.containsKey(identifier);
	}
	
}
