package me.hii488.dataTypes;

import java.awt.Rectangle;

public class VectorBox {
	// Maybe change this to a single vector and two doubles? (lower left corner, height, width)
	// May remove this and replace all instances with "java.awt.Rectangle", will see.
	// (Reason why not starting off is due to issues with negative width Rectangles in other projects)
	
	private Vector cornerA;
	private Vector cornerB;
	
	public VectorBox() {
		cornerA = new Vector();
		cornerB = new Vector();
	}
	
	public VectorBox(Vector cornerA, Vector cornerB) {
		double x1, x2, y1, y2;
		if(cornerA.getX() < cornerB.getX()) {
			x1 = cornerA.getX();
			x2 = cornerB.getX();
		}
		else {
			x1 = cornerB.getX();
			x2 = cornerA.getX();
		}
		
		// Higher Y is lower on screen
		if(cornerA.getY() > cornerB.getY()) {
			y1 = cornerA.getY();
			y2 = cornerB.getY();
		}
		else {
			y1 = cornerB.getY();
			y2 = cornerA.getY();
		}
		
		this.cornerA = new Vector(x1, y1);
		this.cornerB = new Vector(x2, y2);
	}
	
	public VectorBox(Vector upperRight, double width, double height) {
		this(upperRight, upperRight.getLocation().translate(width, height));
	}
	
	// Lower/Upper on screen, not on coordinate.
	public Vector getLowerLeftCorner() {
		return cornerA.getLocation();
	}
	
	public Vector getUpperLeftCorner() {
		return new Vector(cornerA.getX(), cornerB.getY());
	}
	
	public Vector getLowerRightCorner() {
		return new Vector(cornerB.getX(), cornerA.getY());
	}
	
	public Vector getUpperRightCorner() {
		return cornerB;
	}
	
	public Rectangle asRectangle() {
		return new Rectangle(cornerA.getIX(), cornerB.getIY(), cornerB.getIX()-cornerA.getIX(), cornerA.getIY()-cornerB.getIY());
	}
	
	public double getWidth() {
		return cornerB.getX() - cornerA.getX();
	}
	
	public double getHeight() {
		return cornerA.getY() - cornerB.getY();
	}
	
	
	// TODO
	public boolean intersectsArea(Rectangle r) {
		return false;
	}
	
	public boolean intersectsArea(VectorBox v) {
		return false;
	}
	
	public boolean intersectsArea(Vector a, Vector b) {
		return false;
	}
	
	public boolean intersectsArea(int x1, int y1, int x2, int y2) {
		return false;
	}
	
	public boolean intersectsArea(Vector a, int width, int height) {
		return false;
	}
	
}
