package me.hii488.graphics.gui.style;

import java.util.HashMap;

public class GUIStyleGroup {
	
	public HashMap<String, GUIStyle> styles = new HashMap<String, GUIStyle>();
	public String defaultStyle;
	
	public void addStyle(String identifier, GUIStyle style) {
		if(defaultStyle == null) defaultStyle = identifier;
		styles.put(identifier, style);
	}
	
	public GUIStyle getDefault() {
		return styles.get(defaultStyle);
	}
	
	public void setDefault(String identifier) {
		defaultStyle = identifier;
	}
	
		
	
}
