package net.rom.gctweaks.core.compat;

import com.mjr.planetprogression.api.research.ResearchHooksSP;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlanetProgressionCompat {

	@SideOnly(Side.CLIENT)
	@Optional.Method(modid = "planetprogression")
	public static boolean isReasearched(EntityPlayerSP player, CelestialBody body) {
		return ResearchHooksSP.hasUnlockedCelestialBody(player, body);
	}
}
