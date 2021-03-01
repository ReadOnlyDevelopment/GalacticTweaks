/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.romvoid95.galactic.core;

import static net.romvoid95.galactic.Info.ID;

import java.util.*;

import micdoodle8.mods.galacticraft.core.*;
import net.minecraft.util.*;

public class Asset {

	public static Map<String, ResourceLocation> textures = new HashMap<>();

	public static void add(String name, String domain) {
		name = name.toLowerCase();
		textures.put(name.toLowerCase(),
				new ResourceLocation(domain, "textures/gui/celestialbodies/" + name.toLowerCase() + ".png"));
	}

	public static ResourceLocation get(String name) {
		name = name.toLowerCase();
		if (!textures.containsKey(name))
			return null;
		return textures.get(name);
	}
	
	public static void registerPredefined() {
		Asset.add("earth", Constants.MOD_ID_CORE);
		Asset.add("asteroids", Constants.MOD_ID_CORE);
		Asset.add("sun", Constants.MOD_ID_CORE);
		Asset.add("wormhole", ID);
	}
}
