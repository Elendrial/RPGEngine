package me.hii488.dataTypes;

import java.awt.Rectangle;

public class VectorBox {
	// Maybe change this to a single vector and two doubles? (lower left corner, height, width)
	// May remove this and replace all instances with "java.awt.Rectangle", will see.
	// (Reason why not starting off is due to issues with negative width Rectangles in other projects)
	
	// cornerA is lower left corner (x1,y2) and cornerB is upper right corner (x2,y1)
	private Vector cornerA;
	private Vector cornerB;
	
	public VectorBox() {
		cornerA = new Vector();
		cornerB = new Vector();
	}
	
	public VectorBox(Vector cornerA, Vector cornerB) {
		this(cornerA.getX(), cornerA.getY(), cornerB.getX(), cornerB.getY());
	}
	
	public VectorBox(Vector upperleft, double width, double height) {
		this(upperleft, upperleft.getCopy().translate(width, height));
	}
	
	// These are two opposite corners, NOT x,y,width,height
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
	
	public VectorBox(VectorBox v) {
		this.cornerA = v.cornerA.getCopy();
		this.cornerB = v.cornerB.getCopy();
	}
	
	// Lower/Upper on screen, not on coordinate.
	public Vector getLowerLeftCorner() {
		return cornerA.getCopy();
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
	
	
	// Maybe I should change some of these to actual implementations rather that changing the data and using that?
	// Would be messier but potentially faster and more memory efficient.
	public boolean intersectsArea(Rectangle r) {
		return intersectsArea(new VectorBox(new Vector(r.getX(), r.getY()), r.getWidth(), r.getHeight()));
	}
	
	public boolean intersectsArea(VectorBox v) {
		if(v.cornerA.getX() > this.cornerB.getX() || this.cornerA.getX() > v.cornerB.getX()) return false;
		if(v.cornerA.getY() > this.cornerB.getY() || this.cornerA.getY() > v.cornerB.getY()) return false;
		
		return true;
	}
	
	public boolean intersectsArea(Vector a, Vector b) {
		return intersectsArea(new VectorBox(a,b));
	}
	
	public boolean intersectsArea(int x1, int y1, int x2, int y2) {
		return intersectsArea(new VectorBox(x1, y1, x2, y2));
	}
	
	public boolean intersectsArea(Vector a, int width, int height) {
		return intersectsArea(new VectorBox(a, width, height));
	}
	
	public boolean containsPoint(Vector v) {
		return v.getX() > cornerA.getX() && v.getX() < cornerB.getX() && v.getY() < cornerA.getY() && v.getY() > cornerB.getY();
	}
	
	public boolean containsPoint(int x, int y) {
		return x > cornerA.getX() && x < cornerB.getX() && y < cornerA.getY() && y > cornerB.getY();
	}
	
	public void translate(Vector v){
		translate(v.getX(), v.getY());
	}
	
	public void translate(double x, double y) {
		cornerA.translate(x,y);
		cornerB.translate(x,y);
	}
	
	// Scales everything, including the position from origin (top left corner)
	public void scale(double scale) {
		cornerA.scale(scale);
		cornerB.scale(scale);
	}
	
	public String toString(){
		return cornerA + ", " + cornerB;
	}
	
}
