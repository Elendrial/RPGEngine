package me.hii488.interfaces;

import java.awt.Image;

import me.hii488.registries.TextureRegistry;

public interface ITexturedObject extends IRenderable {
	
	public default Image getTexture() {
		return TextureRegistry.getTexture(getTextureKey(), getTextureState());
	}
	
	public default int getTextureWidth() {
		return TextureRegistry.getTextureStats(getTextureKey()).width();
	}
	
	public default int getTextureHeight() {
		return TextureRegistry.getTextureStats(getTextureKey()).height();
	}
	
	// Highest state should be the highest state you request (starting from 0)
	public default void initTexture() {
		TextureRegistry.addTexture(getTextureLocation(), getTextureKey(), getHighestState());
	}
	
	public String getTextureKey();
	public String getTextureLocation();
	public int getTextureState();
	public int getHighestState();
	
}
