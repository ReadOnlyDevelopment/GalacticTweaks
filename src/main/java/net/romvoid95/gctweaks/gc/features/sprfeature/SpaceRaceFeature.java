package net.romvoid95.gctweaks.gc.features.sprfeature;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.romvoid95.gctweaks.base.Feature;
import net.romvoid95.gctweaks.gc.features.sprfeature.command.CommandLeaveSpaceRace;

public class SpaceRaceFeature extends Feature {

	private static boolean spacerace;

	@Override
	public String[] category () {
		return new String[] { "space-race" };
	}

	@Override
	public String comment () {
		return "Additional Features related to Galacticraft SpaceRace Teams";
	}

	@Override
	public void syncConfig (Configuration config, String[] category) {
		spacerace = config
				.get(category[0], "Enable SpaceRace Feature", false, "Set to true if you want to enable features for Galacticraft SpaceRace")
				.getBoolean();
	}

	@Override
	public void ServerStartingEvent (FMLServerStartingEvent event) {
		if (spacerace)
			event.registerServerCommand(new CommandLeaveSpaceRace());
	}
}
