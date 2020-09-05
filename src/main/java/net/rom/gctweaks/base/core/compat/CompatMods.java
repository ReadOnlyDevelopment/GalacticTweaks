package net.rom.gctweaks.base.core.compat;

import javax.annotation.ParametersAreNonnullByDefault;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.Loader;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public enum CompatMods implements IStringSerializable {
	
	SPONGE("sponge"),
	EXTRAPLANETS("extraplanets"),
	GALAXYSPACE("galaxyspace"),
	PLANETPROGRESSION("planetprogression"),
	ASMODEUSCORE("asmodeuscore"),
	ZOLLERN("zollerngalaxy"),
	MOREPLANETS("moreplanets");

	private final String modid;
	private final boolean loaded;

	CompatMods(String modId) {
		this.modid = modId;
		boolean isLoaded = Loader.instance().getModList().stream()
				.anyMatch(modContainer -> modContainer.getModId().equals(this.modid));
		this.loaded = isLoaded;
	}

	@Override
	public final String getName() {
		return this.modid;
	}

	public boolean isLoaded() {
		return this.loaded;
	}

}
