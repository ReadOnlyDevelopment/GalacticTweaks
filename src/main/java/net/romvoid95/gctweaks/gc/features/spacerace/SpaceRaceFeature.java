package net.romvoid95.gctweaks.gc.features.spacerace;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.romvoid95.gctweaks.base.Feature;
import net.romvoid95.gctweaks.gc.features.spacerace.command.CommandLeaveSpaceRace;

public class SpaceRaceFeature extends Feature {

	private static boolean spacerace;

	@Override
	public String category () {
		return "spaceRaceCommand";
	}

	@Override
	public String comment () {
		return "Additional Features related to Galacticraft SpaceRace Teams";
	}

	@Override
	public void syncConfig (String category) {
		spacerace = set(category, "enableFeature", false);
	}

	@Override
	public void ServerStartingEvent (FMLServerStartingEvent event) {
		if (spacerace)
			event.registerServerCommand(new CommandLeaveSpaceRace());
	}
}
