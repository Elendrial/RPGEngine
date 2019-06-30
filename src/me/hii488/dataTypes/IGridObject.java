package me.hii488.dataTypes;

public interface IGridObject {
	
	public default void onPlace() {}
	public default void onRemove() {}
	public default void onReplace() {onRemove();}
	public default void onMove() {}
	
	public Grid<? extends IGridObject> getParentGrid();
	public void setParentGrid(Grid<? extends IGridObject> g);
	
}
