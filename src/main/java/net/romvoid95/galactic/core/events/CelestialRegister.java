package net.romvoid95.galactic.core.events;

import java.util.*;

import micdoodle8.mods.galacticraft.api.galaxies.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.romvoid95.galactic.*;

//@EventBusSubscriber(modid = Info.ID)
public class CelestialRegister {

	private static String[] knwonAddons = { "zollerngalaxy", "extraplanets", "galaxyspace", "moreplanets",
			"galacticraftcore", "galacticraftplanets" };
	public static Map<String, CelestialBody> specialMap = new HashMap<>();
	private static Planet planet;

	@SubscribeEvent
	public static void onPlanetRegister(GalaxyRegistry.PlanetRegisterEvent event) {
		// GalacticTweaks.LOG.info("Event Planet Name: {}", event.planetName);
		GalaxyRegistry.getRegisteredPlanets().values().forEach(p -> {
			if (p.getName() == event.planetName) {
				planet = p;
				if (planet.getReachable()) {
					
					
					GalacticTweaks.LOG.info(planet.getWorldProvider().getName());
//					String[] packg = planet.getWorldProvider().getPackage().getName().split(".");
//					if (!ArrayUtils.contains(packg, "minecraft")) {
//						for (String addonId : knwonAddons) {
//							if (ArrayUtils.contains(packg, addonId)) {
//								GalacticTweaks.LOG.info(addonId);
//								GalacticTweaks.LOG.info("| Reachable Planet Registered");
//								formatReachable(planet.getName(), planet.getDimensionID(), addonId);
//							}
//						}
//					}
				}
				if (!planet.getReachable()) {
					GalacticTweaks.LOG.info("| Unreachable Planet Registered");
					formatUnreachable(planet.getName(), Integer.MAX_VALUE);
				}
			}
		});
	}

	private static void formatReachable(String name, int id, String addon) {
		GalacticTweaks.LOG.info(String.format("|  Name: %10s", name));
		GalacticTweaks.LOG.info(String.format("|    ID: %10s", String.valueOf(id)));
		GalacticTweaks.LOG.info(String.format("| Addon: %10s", addon));
	}

	private static void formatUnreachable(String name, int id) {
		GalacticTweaks.LOG.info(String.format("|  Name: %10s", name));
		GalacticTweaks.LOG.info(String.format("|    ID: %10s", String.valueOf(id)));
	}
}
