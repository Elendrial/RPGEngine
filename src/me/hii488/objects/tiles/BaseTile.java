package me.hii488.objects.tiles;

import java.awt.Color;
import java.awt.Graphics;

import me.hii488.controllers.GameController;
import me.hii488.graphics.Camera;
import me.hii488.interfaces.ITickable;
import me.hii488.misc.Settings;
import me.hii488.misc.Vector;
import me.hii488.objects.TexturedObject;

public abstract class BaseTile extends TexturedObject implements ITickable{
	public Vector gridPosition = new Vector(0,0);
	public boolean isCollidable;
	
	public BaseTile(){
		super();
	}
	
	protected BaseTile(BaseTile t){
		super(t);
		this.isCollidable = t.isCollidable;
		this.gridPosition = t.gridPosition;
		this.gridPosition = t.gridPosition.getLocation();
	}
	
	
	public void onDestroy(){}
	public abstract BaseTile clone();
	
	protected Vector renderPosA = new Vector(); // Upper left corner
	protected Vector renderPosB = new Vector(); // Lower right corner
	
	public void updateRenderPosition() {
		renderPosA.setX(gridPosition.getX() * Camera.scale * Settings.Texture.tileSize - Camera.getPosition().getX());
		renderPosA.setY(gridPosition.getY() * Camera.scale * Settings.Texture.tileSize - Camera.getPosition().getY());
		renderPosB.setX(renderPosA.getX() + (Settings.Texture.tileSize * Camera.scale));
		renderPosB.setY(renderPosA.getY() + (Settings.Texture.tileSize * Camera.scale));
	}
	
	public boolean shouldRender() {
		updateRenderPosition();
		return (renderPosA.getX() < GameController.getWindow().width && renderPosB.getX() > 0) && (renderPosA.getY() < GameController.getWindow().height && renderPosB.getY() > 0);
	}
	
	public void render(Graphics g) {
		super.render(g);
		
		g.drawImage(getTexture(), renderPosA.getIX(), renderPosA.getIY(), null);
				
		if(Settings.Logging.debug && isCollidable){
			g.setColor(Color.red);
			g.drawRect(renderPosA.getIX(), renderPosA.getIY(), (int)(Settings.Texture.tileSize * Camera.scale), (int)(Settings.Texture.tileSize * Camera.scale));
		}		
	}
	
	@Override
	public void setupTextures() {
		this.setupTextures("tile");
	}
}
