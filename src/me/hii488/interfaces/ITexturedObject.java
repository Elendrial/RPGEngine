package me.hii488.interfaces;

import java.awt.Image;

public interface ITexturedObject extends IRenderable {
	
	public Image getTexture();
	public void loadTexture();
	public void isTextureLoaded();
	
}
