package me.hii488.registries;

import java.util.HashMap;

import me.hii488.gameObjects.entities.BaseEntity;

public class EntityRegistry {
//	public static Player player;
	
	protected static HashMap<String, Class<? extends BaseEntity>> entities = new HashMap<String, Class<? extends BaseEntity>>();
	
	public static void registerEntity(BaseEntity b){
		if(!entities.containsKey(b.getEntityName())) {
			entities.put(b.getEntityName(), b.getClass());
			b.initTexture();
		}
	}
	
	public static void registerEntity(String s, Class<? extends BaseEntity> b) {
		if(!entities.containsKey(s)) {
			entities.put(s, b);
			getEntity(s).initTexture();
		}
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
