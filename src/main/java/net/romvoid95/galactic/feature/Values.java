package net.romvoid95.galactic.feature;

import net.romvoid95.api.*;
import net.romvoid95.api.config.def.*;

public final class Values {
	
	public final static class Comments {
		
		public static final ConfigComment SCHEMIDS = ConfigComment.of("Check galacticraft/addon config for schematic IDs");
		public static final ConfigComment USECOORD = ConfigComment.of("If true custom spawn will use the coordinates set in spawnCoords below");
		public static final ConfigComment FIRSTJOIN = ConfigComment.of("If true players are only sent to the spawn dimension on their first time joining. If false players are sent every join");
		public static final ConfigComment EVERYRESPAWN = ConfigComment.of("If true players are sent to the spawn dimension every death respawn");
		public static final ConfigComment SPAWNDIM = ConfigComment.of("Set the Dimension ID of the planet/moon you want players join on see file under `config\\GalacticTweaks\\ValidDimensions.txt` for valid dimension ID's");
		public static final ConfigComment SPAWNPOS = ConfigComment.of("Set the spawn position for players to spawn at");
		public static final ConfigComment MODID = ConfigComment.of("Set the spawn position for players to spawn at");
		public static final ConfigComment BREATHEDIMS = ConfigComment.of("Data consisting of which Dimensions Players can breahte in");
		public static final ConfigComment NOBREATHEDIMS = ConfigComment.of("Data consisting of which Dimensions Players can NOT breahte in");
		public static final ConfigComment DIMSPAWNRATE = ConfigComment.of("Data consisting of which Dimensions meteors will spawn in and the spawn-rate modification defaultValue.\nSpec: <dimID:rate> (Ex: -29:3.5)");
		public static final ConfigComment OXYSPAWNOPTS = ConfigComment.of("Always FALSE if \"enableFeature\"");
		public static final ConfigComment TANKVALUE = ConfigComment.of("The tank players will spawn equipped with");
		public static final ConfigComment THERMALVALUE = ConfigComment.of("The thermal armor players will spawn equipped with");
	}
	
	public final static class Keys {
		
		public static final ConfigKey schematicIDs = ConfigKey.of("schematicIDs");
		public static final ConfigKey useCoord = ConfigKey.of("useCoord");
		public static final ConfigKey firstJoinOnly = ConfigKey.of("firstJoinOnly");
		public static final ConfigKey everyDeath = ConfigKey.of("everyDeath");
		public static final ConfigKey spawnDimId = ConfigKey.of("spawnDimId");
		public static final ConfigKey spawnPos = ConfigKey.of("spawnPos");
		public static final ConfigKey modid = ConfigKey.of("modid");
		public static final ConfigKey breatheableDims = ConfigKey.of("breatheableDims");
		public static final ConfigKey nonBreatheableDims = ConfigKey.of("nonBreatheableDims");
		public static final ConfigKey dimID_spawnrate = ConfigKey.of("dimID_spawnrate");
		public static final ConfigKey tanksValue = ConfigKey.of("tanksValue");
		public static final ConfigKey thermalArmor = ConfigKey.of("thermalArmor");
		public static final ConfigKey includeThermals = ConfigKey.of("includeThermals");
		public static final ConfigKey includeParachute = ConfigKey.of("includeParachute");
		public static final ConfigKey includeFreqModule = ConfigKey.of("includeFreqModule");
		public static final ConfigKey includeShieldController = ConfigKey.of("includeShieldController");
	}
	
	public final static class Categories {
		
		public static final ConfigCat FEATURES = ConfigCat.of("allfeatures").setRequiredRestarts(false, true);
		public static final ConfigCat FEATURE_OPTS = ConfigCat.of("featureopts");
		public static final ConfigCat UNLOCK_SCHEMATICS = ConfigCat.of(FEATURE_OPTS, "UnlockSchematics").setRequiredRestarts(false, true);
		public static final ConfigCat OXYGEN_SPAWN_GEAR = ConfigCat.of(FEATURE_OPTS, "OxygenSpawnGear").setRequiredRestarts(false, true);
		public static final ConfigCat SPAWN_DIMENSION = ConfigCat.of(FEATURE_OPTS, "SpawnDimension").setRequiredRestarts(false, true);
		public static final ConfigCat SEPERATE_ADDONPLANETS = ConfigCat.of(FEATURE_OPTS, "SeperateAddonPlanets").setRequiredRestarts(false, true);
		public static final ConfigCat OXYGEN_TOGGLE = ConfigCat.of(FEATURE_OPTS, "OxygenToggle").setRequiredRestarts(false, true);
		public static final ConfigCat DIMENSIONAL_COMETS = ConfigCat.of(FEATURE_OPTS, "DimensionalComets").setRequiredRestarts(false, true);
	}
	
	public final static class Validators {
		
		public static final ValidValues VALIDMODIDS = ValidValues.of("none", GalacticraftAddon.EXTRAPLANETS.modId(), GalacticraftAddon.GALAXYSPACE.modId())
				.display("None", GalacticraftAddon.EXTRAPLANETS.displayName(), GalacticraftAddon.GALAXYSPACE.displayName());
		public static final ValidValues TANKVALUES = ValidValues.of( "light", "medium", "heavy").display("Light Tanks", "Medium Tanks", "Heavy Tanks");
		public static final ValidValues THERMALVALUES = ValidValues.of("thermal", "isothermal").display("Thermal Armor", "Isothermal Armor");
	}
}
