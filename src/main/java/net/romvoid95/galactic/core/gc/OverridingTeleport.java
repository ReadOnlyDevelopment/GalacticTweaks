package net.romvoid95.galactic.core.gc;

import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;

public abstract class OverridingTeleport implements ITeleportType {

	protected final ITeleportType type;
	protected GCPlayerStats playerStats;

	public OverridingTeleport(ITeleportType teleportType) {
		this.type = teleportType;
	}
}
