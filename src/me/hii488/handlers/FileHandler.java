package me.hii488.handlers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.hii488.logging.LogSeverity;
import me.hii488.logging.Logger;
import me.hii488.registries.TextureRegistry;

public class FileHandler {

	public static BufferedImage loadImage(String location) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(FileHandler.class.getClassLoader().getResourceAsStream(location));
		}
		catch(Exception e) {
			Logger.getDefault().print(LogSeverity.WARNING, "Failed to load image at \"" + location + "\", using standard error image.");
			image = (BufferedImage) TextureRegistry.errorImage().getImage(0);
		}
		
		return image;
	}
	
	public static String getFileContents(String location) {
		String content = "";
		try {
			File file = new File(location);
		    FileReader reader = new FileReader(file);
		    char[] chars = new char[(int) file.length()];
		    reader.read(chars);
		    content = new String(chars);
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		return content;
	}
	
}
