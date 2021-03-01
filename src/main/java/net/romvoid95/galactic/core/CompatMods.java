package net.romvoid95.galactic.core;

import java.util.stream.*;

import javax.annotation.*;

import mcp.*;
import net.minecraftforge.fml.common.*;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public enum CompatMods {
	
	SPONGE("sponge"),
	EXTRAPLANETS("extraplanets"),
	GALAXYSPACE("galaxyspace"),
	PLANETPROGRESSION("planetprogression"),
	ASMODEUSCORE("asmodeuscore"),
	ZOLLERN("zollerngalaxy"),
	MOREPLANETS("moreplanets");

	private final String modid;
	private final boolean isLoaded;
	private final Stream<ModContainer> stream = Loader.instance().getModList().stream();

	CompatMods(String modId) {
		this.modid = modId;
		this.isLoaded = stream.anyMatch(modContainer -> modContainer.getModId().equals(this.modid));
	}

	public final String getModID() {
		return this.modid;
	}

	public boolean isLoaded() {
		return this.isLoaded;
	}

}
