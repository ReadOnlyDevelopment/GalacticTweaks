package net.romvoid95.galactic.modules.galacticraft;

import java.io.File;

import net.romvoid95.api.GalacticraftAddon;
import net.romvoid95.api.config.annotation.GCTFeature;
import net.romvoid95.api.config.def.Category;
import net.romvoid95.api.config.def.Comment;
import net.romvoid95.api.config.def.Key;
import net.romvoid95.api.config.def.ValidDimIDs;
import net.romvoid95.api.config.def.ValidValues;
import net.romvoid95.api.config.values.OptArrayInteger;
import net.romvoid95.api.config.values.OptArrayString;
import net.romvoid95.api.config.values.OptBoolean;
import net.romvoid95.api.config.values.OptString;
import net.romvoid95.galactic.GalacticTweaks;
import net.romvoid95.galactic.Info;
import net.romvoid95.galactic.core.ReadOnlyConfig;
import net.romvoid95.galactic.modules.galacticraft.features.BreatheableDimensions;
import net.romvoid95.galactic.modules.galacticraft.features.CompressorFixes;
import net.romvoid95.galactic.modules.galacticraft.features.DimensionalComets;
import net.romvoid95.galactic.modules.galacticraft.features.FixAsmodeusMapIcons;
import net.romvoid95.galactic.modules.galacticraft.features.MobsBreatheInSpace;
import net.romvoid95.galactic.modules.galacticraft.features.NoSpaceMusic;
import net.romvoid95.galactic.modules.galacticraft.features.OxygenSpawnGear;
import net.romvoid95.galactic.modules.galacticraft.features.SeperateAddonPlanets;
import net.romvoid95.galactic.modules.galacticraft.features.SpaceRaceFeature;
import net.romvoid95.galactic.modules.galacticraft.features.SpawnDimension;
import net.romvoid95.galactic.modules.galacticraft.features.UnlockSchematics;
import net.romvoid95.galactic.modules.galacticraft.features.admintools.DirectTeleporter;

public class GalacticraftModuleConfig extends ReadOnlyConfig {

	// ALL FEATURE ENABLERS
	@GCTFeature(featureClass = UnlockSchematics.class)
	public static boolean	UNLOCK_SCHEMATICS;
	@GCTFeature(featureClass = OxygenSpawnGear.class)
	public static boolean	OXYGEN_SPAWN_GEAR;
	@GCTFeature(featureClass = SpawnDimension.class)
	public static boolean	SPAWN_DIMENSION;
	@GCTFeature(featureClass = SpaceRaceFeature.class)
	public static boolean	SPACE_RACE_FEATURE;
	@GCTFeature(featureClass = SeperateAddonPlanets.class)
	public static boolean	SEPERATE_ADDONPLANETS;
	@GCTFeature(featureClass = BreatheableDimensions.class)
	public static boolean	BREATHEABLE_DIMENSIONS;
	@GCTFeature(featureClass = MobsBreatheInSpace.class)
	public static boolean	MOBS_BREATHE_IN_SPACE;
	@GCTFeature(featureClass = DimensionalComets.class)
	public static boolean	DIMENSIONAL_COMETS;
	@GCTFeature(featureClass = CompressorFixes.class)
	public static boolean	COMPRESSOR_FIXES;
	@GCTFeature(featureClass = NoSpaceMusic.class)
	public static boolean	NO_SPACE_MUSIC;
	@GCTFeature(featureClass = FixAsmodeusMapIcons.class)
	public static boolean	FIX_MAP_ICONS;
	@GCTFeature(featureClass = DirectTeleporter.class)
	public static boolean	DIRECT_TELEPORTER;

	// UNLOCK SCHEMATICS
	public static OptArrayInteger schematicIDs;

	// SPAWN DIM
	public static OptBoolean	useCoord;
	public static OptBoolean	firstJoinOnly;
	public static OptBoolean	everyDeath;
	public static OptString		SpawnDim;
	public static ValidDimIDs	validSPawnDims;
	public static OptString		spawnPos;

	// SEPERATE PLANETS
	public static OptString modid;

	// BREATHEABLE DIMS
	public static OptArrayInteger	breatheableDims;
	public static OptArrayInteger	nonBreatheableDims;

	// DIMENSIONAL COMETS
	public static OptArrayString dimID_spawnrate;

	// SPAWN GEAR
	public static OptString		tanksValue;
	public static OptString		thermalArmor;
	public static OptBoolean	includeThermals;
	public static OptBoolean	includeParachute;
	public static OptBoolean	includeFreqModule;
	public static OptBoolean	includeShieldController;

	public GalacticraftModuleConfig(ConfigVersion configVersion) {
		super(GalacticraftModuleConfig.class);
		this.setConfigversion(configVersion);
		
		schematicIDs = new OptArrayInteger(Keys.schematicIDs, Categories.UNLOCK_SCHEMATICS, Comments.SCHEMIDS, new int[] {});
		addProperty(schematicIDs);

		useCoord = new OptBoolean(Keys.useCoord, Categories.SPAWN_DIMENSION, Comments.USECOORD, false);
		addProperty(useCoord);

		firstJoinOnly = new OptBoolean(Keys.firstJoinOnly, Categories.SPAWN_DIMENSION, Comments.FIRSTJOIN, true);
		addProperty(firstJoinOnly);

		everyDeath = new OptBoolean(Keys.everyDeath, Categories.SPAWN_DIMENSION, Comments.EVERYRESPAWN, false);
		addProperty(everyDeath);

		spawnPos = new OptString(Keys.spawnPos, Categories.SPAWN_DIMENSION, Comments.SPAWNPOS, "128.64.128");
		addProperty(spawnPos);

		modid = new OptString(Keys.modid, Categories.SEPERATE_ADDONPLANETS, Comments.MODID, Validators.VALIDMODIDS);
		addProperty(modid);

		breatheableDims = new OptArrayInteger(Keys.breatheableDims, Categories.OXYGEN_TOGGLE, Comments.BREATHEDIMS, new int[] {});
		addProperty(breatheableDims);

		nonBreatheableDims = new OptArrayInteger(Keys.nonBreatheableDims, Categories.OXYGEN_TOGGLE, Comments.NOBREATHEDIMS, new int[] {});
		addProperty(nonBreatheableDims);

		dimID_spawnrate = new OptArrayString(Keys.dimID_spawnrate, Categories.DIMENSIONAL_COMETS, Comments.DIMSPAWNRATE, "1:2.0");
		addProperty(dimID_spawnrate);

		tanksValue = new OptString(Keys.tanksValue, Categories.OXYGEN_SPAWN_GEAR, Comments.TANKVALUE, Validators.TANKVALUES);
		addProperty(tanksValue);

		thermalArmor = new OptString(Keys.thermalArmor, Categories.OXYGEN_SPAWN_GEAR, Comments.THERMALVALUE, Validators.THERMALVALUES);
		addProperty(thermalArmor);

		includeThermals = new OptBoolean(Keys.includeThermals, Categories.OXYGEN_SPAWN_GEAR, Comments.OXYSPAWNOPTS, false);
		addProperty(includeThermals);

		includeParachute = new OptBoolean(Keys.includeParachute, Categories.OXYGEN_SPAWN_GEAR, Comments.OXYSPAWNOPTS, false);
		addProperty(includeParachute);

		includeFreqModule = new OptBoolean(Keys.includeFreqModule, Categories.OXYGEN_SPAWN_GEAR, Comments.OXYSPAWNOPTS, false);
		addProperty(includeFreqModule);

		includeShieldController = new OptBoolean(Keys.includeShieldController, Categories.OXYGEN_SPAWN_GEAR, Comments.OXYSPAWNOPTS, false);
		addProperty(includeShieldController);
	}

	public void addValidDims() {
		SpawnDim = new OptString(Keys.spawnDimId, Categories.SPAWN_DIMENSION, Comments.SPAWNDIM, validSPawnDims);
		addProperty(SpawnDim);
	}
	
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
