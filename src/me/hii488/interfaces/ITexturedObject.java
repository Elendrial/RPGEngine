package me.hii488.interfaces;

import java.awt.Image;

import me.hii488.registries.TextureRegistry;

public interface ITexturedObject extends IRenderable {
	
	public default Image getTexture() {
		return TextureRegistry.getTexture(getTextureKey(), getTextureState());
	}
	
	public default void loadTexture() {
		
	}
	
	public String getTextureKey();
	public String getTextureLocation();
	public int getTextureState();
	public void isTextureLoaded();
	
}
