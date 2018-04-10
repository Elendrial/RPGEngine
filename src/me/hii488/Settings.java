package me.hii488;

import java.awt.Color;
import java.util.HashMap;

import me.hii488.misc.Vector;

public class Settings {

	public static class WorldSettings {
		public static int TargetTPS = 30;
		public static float currentSpeed = 1;	
	}

	// Currently unused
	public static class CameraSettings {

		public boolean movable = true;

		public static float minZoom = 0.01f;
		public static float maxZoom = 2f;
		
		public static Vector currentPosition = new Vector(0,0);

	}

	public static class Logging{
		// TODO: This.
		public static boolean debug = false;
		public static boolean tpsPrinter = true;
	}

	public static class Texture{
		public static final String defaultTileTextureLocation = "textures/errorTextures/tdefault.png";
		public static final String defaultEntityTextureLocation = "textures/errorTextures/edefault.png";
		public static int tileSize = 16;
		public static Color background = Color.BLACK;
	}
	
	public static class KeyBinds{ // TODO: Maybe change from String to a custom type?
		public static HashMap<String, Integer> keyBinds = new HashMap<String, Integer>();
		
		public static void setupDefaultKeyBinds() {
			
		}
	}
	
}
