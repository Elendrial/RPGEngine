package me.hii488.registries;

import java.util.ArrayList;
import java.util.HashMap;

import me.hii488.dataTypes.KeyBind;

public class KeyBindRegistry {
	
	protected static HashMap<KeyBind, ArrayList<Integer>> keybinds = new HashMap<KeyBind, ArrayList<Integer>>();
	
	public static void setKeyBind(KeyBind key, Integer... i) {
		if(keybinds.values().stream().anyMatch(v -> {for(int j : i) if(v.contains(j)) return true; return false;})) {
			keybinds.put(key, getArrayList(i));
		}
	}
	
	public static ArrayList<Integer> getKeyBindValue(KeyBind key){
		return keybinds.get(key);
	}
	
	public static ArrayList<Integer> getKeyBindValue(String name) {
		// Search through the key binds, if one with the name exists return the mapping, if not return null key's mapping.
		return keybinds.get(keybinds.keySet().stream().filter(k -> k.getName() == name).findFirst().orElse(KeyBind.NULL_KEY));
	}
	
	public static KeyBind getKeyBindedTo(Integer i) {
		// Search through the key binds, if one with the value exists return it, if not return the null key.
		return keybinds.keySet().stream().filter(k -> keybinds.get(k).contains(i)).findFirst().orElse(KeyBind.NULL_KEY);
	}
	
	public static KeyBind getKeyBind(String name) {
		// Search through the key binds, if one with the name exists return it, if not return the null key.
		return keybinds.keySet().stream().filter(k -> k.getName() == name).findFirst().orElse(KeyBind.NULL_KEY);
	}
	
	
	public static void setupDefault() {
		
	}
	
	
	private static ArrayList<Integer> getArrayList(Integer... i){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int is : i) 
			list.add(is);
		
		return list;
	}
	
}
