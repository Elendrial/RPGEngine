package me.hii488.interfaces;

import java.awt.Image;

import me.hii488.registries.TextureRegistry;

public interface ITexturedObject extends IRenderable {
	
	public default Image getTexture() {
		return TextureRegistry.getTexture(getTextureKey(), getTextureState());
	}
	
	// Highest state should be the highest state you request (starting from 0)
	public default void initTexture(int highestState) {
		TextureRegistry.addTexture(getTextureLocation(), getTextureKey(), highestState+1);
	}
	
	public String getTextureKey();
	public String getTextureLocation();
	public int getTextureState();
	
}
