package net.romvoid95.galactic.modules.galacticraft;

import java.io.File;

import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.configs.AsmodeusConfig;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.romvoid95.api.GalacticraftAddon;
import net.romvoid95.api.module.Module;
import net.romvoid95.galactic.GalacticTweaks;
import net.romvoid95.galactic.Info;
import net.romvoid95.galactic.core.ReadOnlyConfig.ConfigVersion;
import net.romvoid95.galactic.core.gc.IOWriter;
import net.romvoid95.galactic.modules.galacticraft.features.BreatheableDimensions;
import net.romvoid95.galactic.modules.galacticraft.features.CompressorFixes;
import net.romvoid95.galactic.modules.galacticraft.features.FixAsmodeusMapIcons;
import net.romvoid95.galactic.modules.galacticraft.features.MobsBreatheInSpace;
import net.romvoid95.galactic.modules.galacticraft.features.NoSpaceMusic;
import net.romvoid95.galactic.modules.galacticraft.features.OxygenSpawnGear;
import net.romvoid95.galactic.modules.galacticraft.features.SeperateAddonPlanets;
import net.romvoid95.galactic.modules.galacticraft.features.SpaceRaceFeature;
import net.romvoid95.galactic.modules.galacticraft.features.SpawnDimension;
import net.romvoid95.galactic.modules.galacticraft.features.UnlockSchematics;
import net.romvoid95.galactic.modules.galacticraft.features.admintools.DirectTeleporter;

public class GalacticraftModule extends Module {

	private GalacticraftModuleConfig config = new GalacticraftModuleConfig(Info.CONFVERSION);
	
	@Override
	public void addClientFeatures() {
		registerFeature(new FixAsmodeusMapIcons());
		registerFeature(new NoSpaceMusic());
	}

	@Override
	public void addCommonFeatures() {
		registerFeature(new DirectTeleporter());
		registerFeature(new BreatheableDimensions());
		registerFeature(new CompressorFixes());
		registerFeature(new MobsBreatheInSpace());
		registerFeature(new OxygenSpawnGear());
		registerFeature(new SeperateAddonPlanets());
		registerFeature(new SpaceRaceFeature());
		registerFeature(new SpawnDimension());
		registerFeature(new UnlockSchematics());
	}
	
	@Override
	public void setupConfig() {
		File file = new File(GalacticTweaks.modFolder, "GalacticraftModule.cfg");
		config.setConfigFile(file);
		config.loadConfig();
		features.forEach(feat -> {
			config.getCategory(feat.getCategory()).setComment(feat.getCategoryComment());
		});	
	}
	
	@Override
	public void postInit() {
		if(GalacticraftAddon.EXTRAPLANETS.isLoaded() && GalacticraftAddon.GALAXYSPACE.isLoaded()) {
			if(AsmodeusConfig.enableNewGalaxyMap) {
				BodiesRegistry.setMaxTier(10);
			}
		}

		IOWriter io = new IOWriter();
		io.handleFile("ValidDimIDs.txt");
		if(GalacticraftAddon.EXTRAPLANETS.isLoaded()) {
			io.NOTICE();
			io.write("Planets & Moons that end with \"ep\" are added by ExtraPlanets.");
			io.write("Please keep this in mind if choosing a planet that is added by both ExtraPlanets & GalaxySpace");
			io.nl();
			io.write("This file is regenerated every time minecraft is ran to handle any addons added or removed");
			io.title("PLANETS");
			io.writePlanets();
			io.title("MOONS");
			io.writeMoons();
			io.finalize();
		}
		
		config.addValidDims();
		config.loadConfig();
		ConfigManager.sync(Info.ID, Config.Type.INSTANCE);
		
		super.postInit();
	}
}
