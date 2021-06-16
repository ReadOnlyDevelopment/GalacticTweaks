package net.romvoid95.galactic.modules.galacticraft.features.admintools;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.romvoid95.api.feature.Feature;
import net.romvoid95.galactic.modules.galacticraft.GalacticraftModuleConfig;

public class DirectTeleporter extends Feature {

	public DirectTeleporter() {
		this.category = "AdminTeleport";
		this.categoryComment = "Allows Admins to teleport to planets directly";
	}
	
	@Override
	public void ServerStartingEvent(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandAdminTeleport());
	}

	@Override
	public boolean isEnabled() {
		return GalacticraftModuleConfig.DIRECT_TELEPORTER;
	}
}
