package readonly.api;

import java.util.List;
import java.util.Optional;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import readonly.api.versioning.Version;

public enum GalacticraftAddon {

	BLANKPLANET("blankplanet", "Blank Planet", RequiredLib.MJRLEGENDSLIB),
	EXTRAPLANETS("extraplanets", "Extra Planets", RequiredLib.MJRLEGENDSLIB),
	GALAXYSPACE("galaxyspace", "Galaxy Space", RequiredLib.ASMODEUSCORE),
	MOREPLANETSEXTRA("moreplanetsextras", "More Extra Planets", RequiredLib.MOREPLANETS),
	MOREPLANETS("moreplanets", "More Planets", RequiredLib.STEVEKUNGSLIB),
	PLANETPROGRESSION("planetprogression", "Planet Progression", RequiredLib.MJRLEGENDSLIB),
	ZOLLERNGALAXY("zollerngalaxy", "Zollern Galaxy", RequiredLib.NONE);

	private final String					modId;
	private final String					displayName;
	private final RequiredLib				requiredLib;
	private final boolean					isLoaded;
	private final List<ModContainer>		list	= Loader.instance().getModList();
	private final Optional<ModContainer>	modContainer;

	GalacticraftAddon(String modId, String displayName, RequiredLib requiredLib) {
		this.modId = modId;
		this.displayName = displayName;
		this.requiredLib = requiredLib;
		this.isLoaded = list.stream().anyMatch(modContainer -> modContainer.getModId().equals(this.modId));
		this.modContainer = list.stream().filter(m -> m.getModId().equals(this.modId)).findFirst();
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

	public Version getVersion() {
		if(modContainer.isPresent()) {
			return Version.of(modContainer.get().getVersion());
		}
		return null;
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
	
	public Version getLibModVersion() {
		if(requiredLib.getLibMod().isPresent()) {
			return Version.of(requiredLib.getLibMod().get().getVersion());
		}
		return null;
	}

	public static enum RequiredLib {

		NONE("none"),
		ASMODEUSCORE("asmodeuscore"),
		MJRLEGENDSLIB("mjrlegendslib"),
		STEVEKUNGSLIB("stevekung's_lib"),
		// Only used for MorePlanetsExtraMod
		MOREPLANETS("moreplanets");

		private final String				modId;
		private final boolean				isLoaded;
		private final List<ModContainer>		list	= Loader.instance().getModList();
		private final Optional<ModContainer>	modContainer;

		RequiredLib(String modId) {
			this.modId = modId;
			this.isLoaded = list.stream().anyMatch(modContainer -> modContainer.getModId().equals(this.modId));
			this.modContainer = list.stream().filter(m -> m.getModId().equals(this.modId)).findFirst();
		}

		public String modId() {
			return modId;
		}

		public boolean isLoaded() {
			return isLoaded;
		}
		
		public Optional<ModContainer> getLibMod() {
			return modContainer;
		}
	}
}
