package me.hii488.registries;

import java.util.HashMap;

import me.hii488.objects.entities.BaseEntity;
import me.hii488.objects.entities.Player;

public class EntityRegistry {
	public static Player player;
	
	protected static HashMap<String, Class<? extends BaseEntity>> entities = new HashMap<String, Class<? extends BaseEntity>>();
	
	public static void registerEntity(BaseEntity b){
		if(!entities.containsKey(b.identifier)) entities.put(b.identifier, b.getClass());
	}
	
	public static void registerEntity(String s, Class<? extends BaseEntity> b) {
		if(!entities.containsKey(s)) entities.put(s, b);
	}
	
	public static BaseEntity getEntity(String identifier){
		try {
			return (BaseEntity) entities.get(identifier).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean contains(String identifier){
		return entities.containsKey(identifier);
	}
}
