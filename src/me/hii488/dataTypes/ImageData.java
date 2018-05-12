package me.hii488.dataTypes;

import java.awt.Image;

import javax.imageio.ImageIO;

import me.hii488.logging.LogSeverity;
import me.hii488.logging.Logger;
import me.hii488.registries.TextureRegistry;

// This class allows the storing of an image's data (location, dimensions etc) without having to keep the image loaded the entire time.
public class ImageData{
	private Image[] image;
	private String imageLocation;
	
	private int states; // number of states, works like array length or collection size(), (ie is 1 higher than the highest you can request)
	public int framesSinceLastRequested;
	
	private int width = -1, height = -1;
	
	public ImageData(String location, int states) {
		this.states = states;
		imageLocation = location;
	}
	
	public void setImages(Image[] i) {
		image = i;
		if(width == -1 || height == -1) {
			width = image[0].getWidth(null);
			height = image[0].getHeight(null);
		}
	}
	
	public Image getImage(int state) {
		return image[state];
	}
	
	public Image[] getAllImages() {
		return image;
	}
	
	public int width() {
		if(width == -1) {
			load();
			unload();
		}
		
		return width;
	}
	
	public int height() {
		if(height == -1) {
			load();
			unload();
		}
		
		return height;
	}
	
	public int getNumberOfStates() {
		return states;
	}
	
	public String getImageLocation() {
		return imageLocation;
	}
	
	public void load() {
		setImages(loadTexture(this));
	}
	
	public void unload() {
		image = null;
	}
	
	private static Image[] loadTexture(ImageData i) {
		Image images[] = new Image[i.getNumberOfStates()];
		
		if(images.length > 1) { // Maybe move this logic to ImageData? This would ensure it's only run once.
			String[] location = i.getImageLocation().split("\\.");
			String end = location[location.length-1];
			String beginning = "";
			
			if(location.length == 2) beginning = location[0];
			else for(int n = 0; n < location.length - 1; n++) beginning += location[n];
			
			for(int n = 0; n < images.length; n++) {
				images[n] = loadImage(beginning + "_" + n + end);
			}
		}
		else {
			images[0] = loadImage(i.getImageLocation());
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
			image = TextureRegistry.errorImage().getImage(0);
		}
		
		return image;
	}
	
}