package me.hii488.registries;

import java.awt.Image;
import java.util.HashMap;

import me.hii488.dataTypes.ImageData;
import me.hii488.logging.LogSeverity;
import me.hii488.logging.Logger;

public class TextureRegistry {
	// TODO: Clean up usage of texture and image.
	private static HashMap<String, ImageData> allTextures = new HashMap<String, ImageData>();
	private static HashMap<String, ImageData> loadedTextures = new HashMap<String, ImageData>(); // If any states are loaded, all will be.
	private static int textureUnloadPoint = 120; // 4 seconds at 30fps, since fps == tps we can use fps as a time reference, bc it makes more sense in this context than tps.
	
	public static void afterRender() {
		loadedTextures.entrySet().forEach(e ->{
			e.getValue().framesSinceLastRequested++;
			if(e.getValue().framesSinceLastRequested > textureUnloadPoint) {
				e.getValue().unload();
				loadedTextures.remove(e.getKey());
			}
		});
	}
	
	public static Image getTexture(String key, int state) {
		if(loadedTextures.containsKey(key)) {
			ImageData i = loadedTextures.get(key);
			i.framesSinceLastRequested = 0;
			return i.getImage(state);
		}
		
		if(!allTextures.containsKey(key)) {
			Logger.getDefault().print(LogSeverity.ERROR, "Tried to load unknown textured \"" + key + "\"");
			return errorImage().getImage(0);
		}
		
		ImageData i = allTextures.get(key);
		
		i.load();
		i.framesSinceLastRequested = 0;
		
		loadedTextures.put(key, i);
		
		return i.getImage(state);
	}
	
	public static ImageData getTextureStats(String key) {
		if(allTextures.containsKey(key)) return allTextures.get(key);
		return errorImage();
	}
	
	// No real reason to not use the location as a key, maybe I should? Not sure, maybe you want image loading in a completely different place to what uses it.
	public static void addTexture(String location, String key, int maxStates) {
		// Just telling the registry that there's a texture somewhere does not automatically load it, it just stores the info of where it is.
		if(allTextures.containsKey(key)) {
			Logger.getDefault().print(LogSeverity.WARNING, "A texture with key \"" + key + "\" has already been registered. Ignoring overwrite. Use overwriteTexture if this is your intention.");
			return;
		}
		allTextures.put(key, new ImageData(location, maxStates));
	}
	
	public static void overwriteTexture(String location, String key, int maxStates) {
		if(!allTextures.containsKey(key)) Logger.getDefault().print(LogSeverity.DETAIL, "No texture with key \"" + key + "\" has already been registered but trying to overwrite. Will still register texture.");
		allTextures.put(key, new ImageData(location, maxStates));
	}
	
	// Assumed use would be in loading screens or similar.
	public static void forceLoadTexture(String key) {
		if(loadedTextures.containsKey(key)) loadedTextures.get(key).framesSinceLastRequested = 0;
		else if(allTextures.containsKey(key)) {
			ImageData i = allTextures.get(key);
			i.load();
			i.framesSinceLastRequested = 0;
		}
		else {
			Logger.getDefault().print(LogSeverity.ERROR, "Tried to force-load unknown textured \"" + key + "\"");
		}
	}
	
	public static ImageData errorImage() {
		// TODO
		return null;
	}
	
}
