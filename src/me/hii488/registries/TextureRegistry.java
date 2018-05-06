package me.hii488.registries;

import java.awt.Image;
import java.util.HashMap;

import javax.imageio.ImageIO;

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
				e.getValue().image = null;
				loadedTextures.remove(e.getKey());
			}
		});
	}
	
	public static Image getTexture(String key, int state) {
		if(loadedTextures.containsKey(key)) {
			ImageData i = loadedTextures.get(key);
			i.framesSinceLastRequested = 0;
			return i.image[state];
		}
		
		if(!allTextures.containsKey(key)) {
			Logger.getDefault().print(LogSeverity.ERROR, "Tried to load unknown textured \"" + key + "\"");
			return errorImage();
		}
		
		ImageData i = allTextures.get(key);
		
		i.image = loadTexture(i);
		i.framesSinceLastRequested = 0;
		
		loadedTextures.put(key, i);
		
		return i.image[state];
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
			i.image = loadTexture(i);
			i.framesSinceLastRequested = 0;
		}
		else {
			Logger.getDefault().print(LogSeverity.ERROR, "Tried to force-load unknown textured \"" + key + "\"");
		}
	}
	
	private static Image[] loadTexture(ImageData i) {
		Image images[] = new Image[i.states];
		
		if(images.length > 1) { // Maybe move this logic to ImageData? This would ensure it's only run once.
			String[] location = i.imageLocation.split("\\.");
			String end = location[location.length-1];
			String beginning = "";
			
			if(location.length == 2) beginning = location[0];
			else for(int n = 0; n < location.length - 1; n++) beginning += location[n];
			
			for(int n = 0; n < images.length; n++) {
				images[n] = loadImage(beginning + "_" + n + end);
			}
		}
		else {
			images[0] = loadImage(i.imageLocation);
		}
		
		return images;
	}
	
	private static Image loadImage(String location) {
		Image image = null;
		
		try {
			image = ImageIO.read(TextureRegistry.class.getClassLoader().getResourceAsStream(location));
		}
		catch(Exception e) {
			Logger.getDefault().print(LogSeverity.WARNING, "Failed to load image at \"" + location + "\", using standard error image.");
			image = errorImage();
		}
		
		return image;
	}
	
	private static Image errorImage() {
		// TODO
		return null;
	}
	
	private static class ImageData{
		public Image[] image;
		public String imageLocation;
		
		public int states; // number of states, works like array length or collection size(), (ie is 1 higher than the highest you can request)
		public int framesSinceLastRequested;
		
		public ImageData(String location, int states) {
			this.states = states;
			imageLocation = location;
		}
	}
	
}
