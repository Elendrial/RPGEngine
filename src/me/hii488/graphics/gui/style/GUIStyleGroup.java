package me.hii488.graphics.gui.style;

import java.util.HashMap;

import me.hii488.logging.LogSeverity;
import me.hii488.logging.Logger;

public class GUIStyleGroup {
	
	private HashMap<String, GUIStyle> styles = new HashMap<String, GUIStyle>();
	public String defaultStyle;
	
	public void addStyle(String identifier, GUIStyle style) {
		if(defaultStyle == null) defaultStyle = identifier;
		styles.put(identifier, style);
	}
	
	public GUIStyle getStyle(String key) {
		if(styles.containsKey(key)) return styles.get(key);
		
		Logger.getDefault().print(LogSeverity.WARNING, "Could not find style \"" + key + "\", resorting to default.");
		return getDefault();
	}
	
	public GUIStyle getDefault() {
		return styles.get(defaultStyle);
	}
	
	public void setDefault(String identifier) {
		defaultStyle = identifier;
	}
	
		
	
}
