package me.hii488.dataTypes;

public class KeyBind {
	// TODO: Think about whether this is really necessary at all? Why not just pass around strings?
	public static final KeyBind NULL_KEY = new KeyBind("NULL_KEY");
	public static final KeyBind MOVE_UP = new KeyBind("MOVE_UP");
	public static final KeyBind MOVE_DOWN = new KeyBind("MOVE_DOWN");
	public static final KeyBind MOVE_RIGHT = new KeyBind("MOVE_RIGHT");
	public static final KeyBind MOVE_LEFT = new KeyBind("MOVE_LEFT");
	public static final KeyBind INTERACT = new KeyBind("INTERACT");
	
	private String name;
	
	public KeyBind(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Keybind:" + name;
	}
	
}
