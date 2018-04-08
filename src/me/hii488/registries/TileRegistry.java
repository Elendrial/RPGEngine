package me.hii488.registries;

import java.util.HashMap;

import me.hii488.objects.tiles.BaseTile;
import me.hii488.objects.tiles.BlankTile;

public class TileRegistry{
	
	protected static HashMap<String, Class<? extends BaseTile>> tiles = new HashMap<String,  Class<? extends BaseTile>>();
	
	public static void registerTile(BaseTile b){
		if(!tiles.containsKey(b.identifier)) tiles.put(b.identifier, b.getClass());
	}
	
	public static void registerEntity(String s, Class<? extends BaseTile> b) {
		if(!tiles.containsKey(s)) tiles.put(s, b);
	}
	
	public static BlankTile getBlankTile(){
		try {
			return (BlankTile) tiles.get("blankTile").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BaseTile getTile(String identifier){
		try {
			return (BaseTile) tiles.get(identifier).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return getBlankTile();
	}
	
	public static boolean contains(String identifier){
		return tiles.containsKey(identifier);
	}
	
}
