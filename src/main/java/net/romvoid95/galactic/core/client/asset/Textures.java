package net.romvoid95.galactic.core.client.asset;

import net.romvoid95.api.client.*;
import net.romvoid95.galactic.*;

public class Textures extends Assets {

	public final static Textures _instance = new Textures();

	public Textures() {
		super(Info.ID);
	}

	public static void addTexture(String textureName) {
		Textures._instance.add(textureName);
	}

	public static void addTexture(String textureName, String path) {
		Textures._instance.add(textureName, path);
	}

	public static Texture getTexture(String name) {
		return Textures._instance.get(name);
	}
}
