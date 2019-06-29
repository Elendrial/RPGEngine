package me.hii488.dataTypes;

public interface IGridObject {
	
	public default void onRemove() {}
	public default void onReplace() {}
	public default void onMove() {}
	
}
