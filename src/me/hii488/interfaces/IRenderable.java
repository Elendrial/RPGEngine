package me.hii488.interfaces;

import java.awt.Graphics;

import me.hii488.dataTypes.Vector;

public interface IRenderable {

	public void render(Graphics g);
	public default void render(Graphics g, Vector position) {
		render(g);
	}
	
}
