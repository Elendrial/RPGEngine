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
		this(cornerA.getX(), cornerA.getY(), cornerB.getX(), cornerB.getY());
	}
	
	public VectorBox(Vector upperRight, double width, double height) {
		this(upperRight, upperRight.getLocation().translate(width, height));
	}
	
	public VectorBox(double x1, double y1, double x2, double y2) {
		double nx1, nx2, ny1, ny2;
		if(x1 < x2) {
			nx1 = x1;
			nx2 = x2;
		}
		else {
			nx1 = x2;
			nx2 = x1;
		}
		
		// Higher Y is lower on screen
		if(y1 > y2) {
			ny1 = y1;
			ny2 = y2;
		}
		else {
			ny1 = y2;
			ny2 = y1;
		}
		
		this.cornerA = new Vector(nx1, ny1);
		this.cornerB = new Vector(nx2, ny2);
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
