package me.hii488;

import java.awt.Color;
import java.util.HashMap;

import me.hii488.dataTypes.KeyBind;
import me.hii488.dataTypes.Vector;

public class EngineSettings {

	public static class World {
		public static int TargetTPS = 30;
		public static float currentSpeed = 1;	
	}
	
	public static class Camera {

		public static boolean movable = true;

		public static float minZoom = 0.01f;
		public static float maxZoom = 2f;
		
		public static Vector currentPosition = new Vector(0,0);

	}

	public static class Logging {
		// TODO: This.
		public static boolean debug = false;
		public static boolean tpsPrinter = true;
	}

	public static class Texture {
		public static final String defaultTileTextureLocation = "textures/errorTextures/tdefault.png";
		public static final String defaultEntityTextureLocation = "textures/errorTextures/edefault.png";
		public static int tileSize = 16;
		public static Color background = Color.BLACK;
	}
	
	public static class KeyBinds { // TODO: Maybe change from String to a custom type?
		public static HashMap<KeyBind, Integer> keyBinds = new HashMap<KeyBind, Integer>();
		
		public static void setupDefaultKeyBinds() {
			
		}
	}
}
