package net.romvoid95.galactic.feature;

import net.romvoid95.api.*;
import net.romvoid95.api.config.def.*;

public final class Values {

	public final static class Comments {

		public static final Comment	SCHEMIDS		= Comment.of("Check galacticraft/addon config for schematic IDs");
		public static final Comment	USECOORD		= Comment.of("If true custom spawn will use the coordinates set in spawnCoords below");
		public static final Comment	FIRSTJOIN		= Comment.of("If true players are only sent to the spawn dimension on their first time joining. If false players are sent every join");
		public static final Comment	EVERYRESPAWN	= Comment.of("If true players are sent to the spawn dimension every death respawn");
		public static final Comment	SPAWNDIM		= Comment.of("Set the Dimension ID of the planet/moon you want players join on see file under `config\\GalacticTweaks\\ValidDimensions.txt` for valid dimension ID's");
		public static final Comment	SPAWNPOS		= Comment.of("Set the spawn position for players to spawn at");
		public static final Comment	MODID			= Comment.of("Set the 'modid' for which addon to move duplicate planets for");
		public static final Comment	BREATHEDIMS		= Comment.of("Data consisting of which Dimensions Players can breahte in");
		public static final Comment	NOBREATHEDIMS	= Comment.of("Data consisting of which Dimensions Players can NOT breahte in");
		public static final Comment	DIMSPAWNRATE	= Comment.of("Data consisting of which Dimensions meteors will spawn in and the spawn-rate modification defaultValue.\nSpec: <dimID:rate> (Ex: -29:3.5)");
		public static final Comment	OXYSPAWNOPTS	= Comment.of("Always FALSE if \"enableFeature\"");
		public static final Comment	TANKVALUE		= Comment.of("The tank players will spawn equipped with");
		public static final Comment	THERMALVALUE	= Comment.of("The thermal armor players will spawn equipped with");
	}

	public final static class Keys {

		public static final Key	schematicIDs			= Key.of("schematicIDs");
		public static final Key	useCoord				= Key.of("useCoord");
		public static final Key	firstJoinOnly			= Key.of("firstJoinOnly");
		public static final Key	everyDeath				= Key.of("everyDeath");
		public static final Key	spawnDimId				= Key.of("spawnDimId");
		public static final Key	spawnPos				= Key.of("spawnPos");
		public static final Key	modid					= Key.of("modid");
		public static final Key	breatheableDims			= Key.of("breatheableDims");
		public static final Key	nonBreatheableDims		= Key.of("nonBreatheableDims");
		public static final Key	dimID_spawnrate			= Key.of("dimID_spawnrate");
		public static final Key	tanksValue				= Key.of("tanksValue");
		public static final Key	thermalArmor			= Key.of("thermalArmor");
		public static final Key	includeThermals			= Key.of("includeThermals");
		public static final Key	includeParachute		= Key.of("includeParachute");
		public static final Key	includeFreqModule		= Key.of("includeFreqModule");
		public static final Key	includeShieldController	= Key.of("includeShieldController");
	}

	public final static class Categories {

		public static final Category	FEATURES				= Category.of("allfeatures").setRequiredRestarts(false, true);
		public static final Category	FEATURE_OPTS			= Category.of("featureopts");
		public static final Category	UNLOCK_SCHEMATICS		= Category.of(FEATURE_OPTS, "UnlockSchematics").setRequiredRestarts(false, true);
		public static final Category	OXYGEN_SPAWN_GEAR		= Category.of(FEATURE_OPTS, "OxygenSpawnGear").setRequiredRestarts(false, true);
		public static final Category	SPAWN_DIMENSION			= Category.of(FEATURE_OPTS, "SpawnDimension").setRequiredRestarts(false, true);
		public static final Category	SEPERATE_ADDONPLANETS	= Category.of(FEATURE_OPTS, "SeperateAddonPlanets").setRequiredRestarts(false, true);
		public static final Category	OXYGEN_TOGGLE			= Category.of(FEATURE_OPTS, "OxygenToggle").setRequiredRestarts(false, true);
		public static final Category	DIMENSIONAL_COMETS		= Category.of(FEATURE_OPTS, "DimensionalComets").setRequiredRestarts(false, true);
	}

	public final static class Validators {

		public static final ValidValues	VALIDMODIDS		= ValidValues.of("none", GalacticraftAddon.EXTRAPLANETS.modId(), GalacticraftAddon.GALAXYSPACE.modId()).display("None", GalacticraftAddon.EXTRAPLANETS.displayName(), GalacticraftAddon.GALAXYSPACE.displayName());
		public static final ValidValues	TANKVALUES		= ValidValues.of("light", "medium", "heavy").display("Light Tanks", "Medium Tanks", "Heavy Tanks");
		public static final ValidValues	THERMALVALUES	= ValidValues.of("thermal", "isothermal").display("Thermal Armor", "Isothermal Armor");
	}
}
