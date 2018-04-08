package me.hii488.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.hii488.handlers.TextureHandler;

public abstract class TexturedObject {
	
	public String textureName = "";
	protected String sanitizedName = "";
	
	public String identifier;
	public int states = 0;
	public int currentState = 0;
	
	public TexturedObject(){
		initVars();
		setupTextures();
	}
	
	public TexturedObject(TexturedObject t){
		this.states = t.states;
		this.currentState = t.currentState;
		this.textureName = t.textureName;
		this.sanitizedName = t.sanitizedName;
		this.identifier = t.identifier;
	}
	
	public abstract void setupTextures();
	
	public void setupTextures(String customFile){
		sanitizedName = textureName.split("\\.")[0];
		
		if(states > 1){
			for(int i = 0; i < states; i++) TextureHandler.loadTexture("textures/" + customFile + "/", sanitizedName + "_" + i + "." + textureName.split("\\.")[1], this, sanitizedName + "_" + i);
		}
		else{
			TextureHandler.loadTexture("textures/" + customFile + "/", textureName, this, sanitizedName + "_0");
		}
		
		textureName = sanitizedName + "_" + currentState;
	}
	
	public void render(Graphics g) {
		textureName = sanitizedName + "_" + currentState;
	}
	
	public BufferedImage getTexture() {
		return TextureHandler.getTexture(textureName);
	}
	
	public abstract void initVars();
	public abstract void onLoad();
	public abstract void onDestroy();
	public abstract TexturedObject clone();
}
