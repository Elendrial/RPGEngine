package me.hii488.dataTypes;

import java.awt.Image;

import javax.imageio.ImageIO;

import me.hii488.logging.LogSeverity;
import me.hii488.logging.Logger;
import me.hii488.registries.TextureRegistry;

// This class allows the storing of an image's data (location, dimensions etc) without having to keep the image loaded the entire time.
public class ImageData{
	private Image[] image;
	private String[] imageLocation;
	
	private int states; // number of states, works like array length or collection size(), (ie is 1 higher than the highest you can request)
	public int framesSinceLastRequested;
	
	private int width = -1, height = -1;
	
	public ImageData(String location, int states) {
		if(states <= 0) {
			states = 1;
			Logger.getDefault().print(LogSeverity.WARNING, "Variable \"states\" passed as <= 0 for texture at \"" + location + "\". This should be set as a .size() or .length(), ie: start counting at 1.");
		}
		this.states = states;
		
		if(states > 1) {
			String[] l = location.split("\\.");
			String end = "." + l[l.length-1];
			String beginning = "";
			
			if(l.length == 2) beginning = l[0];
			else {
				beginning += l[0];
				for(int n = 1; n < l.length - 1; n++) beginning += "." + l[n];
			}
			
			imageLocation = new String[] {beginning, end};
		}
		else {
			imageLocation = new String[] {location};
		}
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
	
	public void load() {
		setImages(loadTexture(this));
	}
	
	public void unload() {
		image = null;
	}
	
	private Image[] loadTexture(ImageData i) {
		Image images[] = new Image[i.getNumberOfStates()];
		
		if(images.length > 1) {
			for(int n = 0; n < images.length; n++) {
				images[n] = loadImage(imageLocation[0] + "_" + n + imageLocation[1]);
			}
		}
		else {
			images[0] = loadImage(imageLocation[0]);
		}
		
		return images;
	}
	
	private Image loadImage(String location) {
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
	
	public boolean equals(Object o) {
		if(!(o instanceof ImageData)) return false;
		
		if(((ImageData) o).states != states) return false;
		
		if(((ImageData) o).imageLocation.length != imageLocation.length) return false;
		
		for(int i = 0; i < imageLocation.length; i++) {
			if(((ImageData) o).imageLocation[i].equals(imageLocation[i])) return false;
		}
		
		return true;
	}
	
	public int hashCode() {
		String total = "";
		for(String s : imageLocation)
			total +=s;
		
		return total.hashCode();
	}
	
}