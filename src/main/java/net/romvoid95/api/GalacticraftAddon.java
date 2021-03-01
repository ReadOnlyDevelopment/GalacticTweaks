package net.romvoid95.api;

import java.util.stream.*;

import net.minecraftforge.fml.common.*;

public enum GalacticraftAddon {

	BLANKPLANET("blankplanet", "Blank Planet", RequiredLib.MJRLEGENDSLIB),
	EXTRAPLANETS("extraplanets", "Extra Planets", RequiredLib.MJRLEGENDSLIB),
	GALAXYSPACE("galaxyspace", "Galaxy Space", RequiredLib.ASMODEUSCORE),
	MOREPLANETSEXTRA("moreplanetsextras", "More Extra Planets", RequiredLib.MOREPLANETS),
	MOREPLANETS("moreplanets", "More Planets", RequiredLib.STEVEKUNGSLIB),
	PLANETPROGRESSION("planetprogression", "Planet Progression", RequiredLib.MJRLEGENDSLIB),
	ZOLLERNGALAXY("zollerngalaxy", "Zollern Galaxy", RequiredLib.NONE);
	
	private final String modId;
	private final String displayName;
	private final RequiredLib requiredLib;
	private final boolean isLoaded;
	private final Stream<ModContainer> stream = Loader.instance().getModList().stream();
	
	GalacticraftAddon(String modId, String displayName,  RequiredLib requiredLib) {
		this.modId = modId;
		this.displayName = displayName;
		this.requiredLib = requiredLib;
		this.isLoaded = stream.anyMatch(modContainer -> modContainer.getModId().equals(this.modId));
	}

	public String modId() {
		return modId;
	}
	
	public String displayName() {
		return displayName;
	}
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	public RequiredLib getLibMod() {
		return requiredLib;
	}
	
	public String getLibModId() {
		return requiredLib.modId();
	}
	
	public boolean isLibModLoaded() {
		return requiredLib.equals(RequiredLib.NONE) ? false : requiredLib.isLoaded();
	}
	
	private enum RequiredLib {
		
		NONE("none"),
		ASMODEUSCORE("asmodeuscore"),
		MJRLEGENDSLIB("mjrlegendslib"),
		STEVEKUNGSLIB("stevekung's_lib"),
		// Only used for MorePlanetsExtraMod
		MOREPLANETS("moreplanets");

		private final String modId;
		private final boolean isLoaded;
		private final Stream<ModContainer> stream = Loader.instance().getModList().stream();

		RequiredLib(String modId) {
			this.modId = modId;
			this.isLoaded = stream.anyMatch(modContainer -> modContainer.getModId().equals(this.modId));
		}

		public String modId() {
			return modId;
		}
		
		public boolean isLoaded() {
			return isLoaded;
		}
	}
}
