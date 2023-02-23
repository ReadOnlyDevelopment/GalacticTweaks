package readonly.galactictweaks.modules.galacticraft;

import java.io.File;

import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.configs.AsmodeusConfig;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import readonly.api.GalacticraftAddon;
import readonly.api.module.Module;
import readonly.galactictweaks.GCTweaks;
import readonly.galactictweaks.Info;
import readonly.galactictweaks.core.gc.IOWriter;
import readonly.galactictweaks.features.BreatheableDimensions;
import readonly.galactictweaks.features.CompressorFixes;
import readonly.galactictweaks.features.DimensionalComets;
import readonly.galactictweaks.features.MobsBreatheInSpace;
import readonly.galactictweaks.features.NoSpaceMusic;
import readonly.galactictweaks.features.SeperateAddonPlanets;
import readonly.galactictweaks.features.SpaceRaceFeature;
import readonly.galactictweaks.features.SpawnDimension;
import readonly.galactictweaks.features.UnlockSchematics;
import readonly.galactictweaks.features.Unreachables;
import readonly.galactictweaks.features.admintools.DirectTeleporter;
import readonly.galactictweaks.features.oxygengear.OxygenSpawnGear;

public class GalacticraftModule extends Module {

	private GalacticraftModuleConfig config = new GalacticraftModuleConfig();
	
	@Override
	public void addClientFeatures() {
		registerFeature(new NoSpaceMusic());
	}

	@Override
	public void addCommonFeatures() {
	    registerFeature(new Unreachables());
		registerFeature(new DirectTeleporter());
		registerFeature(new BreatheableDimensions());
		registerFeature(new CompressorFixes());
		registerFeature(new MobsBreatheInSpace());
		registerFeature(new OxygenSpawnGear());
		registerFeature(new SeperateAddonPlanets());
		registerFeature(new SpaceRaceFeature());
		registerFeature(new SpawnDimension());
		registerFeature(new UnlockSchematics());
		registerFeature(new DimensionalComets());
	}
	
	@Override
	public void setupConfig() {
		File file = new File(GCTweaks.modFolder, "GalacticraftModule.cfg");
		config.setConfigFile(file);
		config.loadConfig();
	}
	
	@Override
	public void postInit() {
		if(GalacticraftAddon.EXTRAPLANETS.isLoaded() && GalacticraftAddon.GALAXYSPACE.isLoaded()) {
			if(AsmodeusConfig.enableNewGalaxyMap) {
				BodiesRegistry.setMaxTier(10);
			}
		}

		IOWriter io = new IOWriter();
		io.handleFile("Celestial_Lists.md");
		if(GalacticraftAddon.EXTRAPLANETS.isLoaded()) {
			io.NOTICE();
			io.write("Planets & Moons that end with \"ep\" are added by ExtraPlanets.");
			io.write("Please keep this in mind if choosing a planet that is added by both ExtraPlanets & GalaxySpace");
			io.nl();
		}
        io.write("This file is regenerated every time minecraft is ran to handle any addons added or removed");
        io.nl();
        io.spacer();
        io.write("Columns in the tables can have the values in the rows below them used in");
        io.write("configuration options that allow them for GalacticTweaks");
        io.write("Columns are referenced as `Ref` below for simplicity.");
        io.write("Each option that allows 1 or more `Ref` will hav a Spec definition.");
        io.write("Specs are defined by `Ref`'s that are valid for that option.");
        io.write(" - Anything inside of `< >` would be a single input");
        io.write(" - Multple `Ref`'s separated by `/`: 1 of them can be used but no more than 1");
        io.write(" - `Ref` enclosed in [ ] indicate two used together and can be seen a single Ref of the two combined");
        io.write(" - The above does not apply to multiple `Ref`'s separated by `/`");
        io.write(" - When a semicolon (:) is used in specs it should be taken as a literal and used in the value");
        io.nl();
        io.write(" **Example Spec**: `<Name/DimId>`");
        io.write(" __Valid__:");
        io.write("     S:configOption=`mercury`");
        io.write("     S:configOption=`-31`");
        io.write(" __Invalid__:");
        io.write("     S:someRandomConfig=`venus/-31`");
        io.nl();
        io.write(" **Example Spec**: `<[Owner:Name]/Name/DimId>`");
        io.write(" __Valid__:" );
        io.write("     S:configOption=`galaxyspace:mercury`");
        io.write("     S:configOption=`mercury`" );
        io.write("     S:configOption=`-1005`" );
        io.write(" __Invalid__:");
        io.write("     S:configOption=`-1005:mercury`");
        io.write("     S:configOption=`galaxyspace:-1005`");
        io.nl();
        io.write("### The use of either 'galacticraftcore' or 'galacticraftplanets' as Owner Ref is not needed and is disregarded when parsing the config");
        io.spacer();
        io.title("PLANETS");
        io.writePlanets();
        io.title("MOONS");
        io.writeMoons();
        io.flushAndClose();
		
		config.addValidDims();
		config.loadConfig();
		ConfigManager.sync(Info.ID, Config.Type.INSTANCE);
		
		super.postInit();
	}
}
