package readonly.galactictweaks.modules.galacticraft;

import readonly.api.GalacticraftAddon;
import readonly.api.config.annotation.GCTFeature;
import readonly.api.config.def.Category;
import readonly.api.config.def.Comment;
import readonly.api.config.def.Key;
import readonly.api.config.def.ValidDimIDs;
import readonly.api.config.def.ValidValues;
import readonly.api.config.values.OptArrayInteger;
import readonly.api.config.values.OptArrayString;
import readonly.api.config.values.OptBoolean;
import readonly.api.config.values.OptString;
import readonly.galactictweaks.core.ReadOnlyConfig;
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
	@GCTFeature(featureClass = DirectTeleporter.class)
	public static boolean	DIRECT_TELEPORTER;
	@GCTFeature(featureClass = Unreachables.class)
	public static boolean   MAKE_UNREACHABLE;

	// UNREACHABLES
	public static OptArrayString unreachables;
	
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

	public GalacticraftModuleConfig() {
		super(GalacticraftModuleConfig.class);
		
		unreachables = new OptArrayString(Keys.idList, Categories.UNREACHABLES, Comments.UNREACHABLES, new String[] {});
		addProperty(unreachables);
		
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

		dimID_spawnrate = new OptArrayString(Keys.dimID_spawnrate, Categories.DIMENSIONAL_COMETS, Comments.DIMSPAWNRATE, new String[] {});
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

	    public static final Comment UNREACHABLES    = Comment.of("List of planet/moon DimensionID's or name to set as Unreachable to players");
		public static final Comment	SCHEMIDS		= Comment.of("Check galacticraft/addon config for schematic IDs");
		public static final Comment	USECOORD		= Comment.of("If true custom spawn will use the coordinates set in spawnCoords below");
		public static final Comment	FIRSTJOIN		= Comment.of("If true players are only sent to the spawn dimension on their first time joining. If false players are sent every join");
		public static final Comment	EVERYRESPAWN	= Comment.of("If true players are sent to the spawn dimension every death respawn");
		public static final Comment	SPAWNDIM		= Comment.of("Set the Dimension ID of the planet/moon you want players join on see file under `config\\GalacticTweaks\\Celestial_Lists.md` for valid dimension ID's");
		public static final Comment	SPAWNPOS		= Comment.of("Set the spawn position for players to spawn at");
		public static final Comment	MODID			= Comment.of("Set the 'modid' for which addon to move duplicate planets for");
		public static final Comment	BREATHEDIMS		= Comment.of("Data consisting of which Dimensions Players can breahte in");
		public static final Comment	NOBREATHEDIMS	= Comment.of("Data consisting of which Dimensions Players can NOT breahte in");
		public static final Comment	DIMSPAWNRATE	= Comment.of("Data consisting of which Dimensions meteors will spawn in and the spawn-rate modification."
		    + "\nSpec: <[dimID/name]:rate>\n(Example: -29:3.5)\n(Example: moon:1.5)");
		public static final Comment	OXYSPAWNOPTS	= Comment.of("Always FALSE if feature is disabled");
		public static final Comment	TANKVALUE		= Comment.of("The tank players will spawn equipped with");
		public static final Comment	THERMALVALUE	= Comment.of("The thermal armor players will spawn equipped with");
	}

	public final static class Keys {
	    public static final Key idList                  = Key.of("idList");
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

		public static final Category	UNREACHABLES		= Category.of(FEATURE_OPTS, "Unreachables", UnlockSchematics.comment).setRequiredRestarts(false, true);
		public static final Category	UNLOCK_SCHEMATICS		= Category.of(FEATURE_OPTS, "UnlockSchematics", UnlockSchematics.comment).setRequiredRestarts(false, true);
		public static final Category	OXYGEN_SPAWN_GEAR		= Category.of(FEATURE_OPTS, "OxygenSpawnGear", OxygenSpawnGear.comment).setRequiredRestarts(false, true);
		public static final Category	SPAWN_DIMENSION			= Category.of(FEATURE_OPTS, "SpawnDimension", SpawnDimension.comment).setRequiredRestarts(false, true);
		public static final Category	SEPERATE_ADDONPLANETS	= Category.of(FEATURE_OPTS, "SeperateAddonPlanets", SeperateAddonPlanets.comment).setRequiredRestarts(false, true);
		public static final Category	OXYGEN_TOGGLE			= Category.of(FEATURE_OPTS, "OxygenToggle", BreatheableDimensions.comment).setRequiredRestarts(false, true);
		public static final Category	DIMENSIONAL_COMETS		= Category.of(FEATURE_OPTS, "DimensionalComets", DimensionalComets.comment).setRequiredRestarts(false, true);
	}

	public final static class Validators {

		public static final ValidValues	VALIDMODIDS		= ValidValues.of("none", GalacticraftAddon.EXTRAPLANETS.modId(), GalacticraftAddon.GALAXYSPACE.modId()).display("None", GalacticraftAddon.EXTRAPLANETS.displayName(), GalacticraftAddon.GALAXYSPACE.displayName());
		public static final ValidValues	TANKVALUES		= ValidValues.of("light", "medium", "heavy").display("Light Tanks", "Medium Tanks", "Heavy Tanks");
		public static final ValidValues	THERMALVALUES	= ValidValues.of("thermal", "isothermal").display("Thermal Armor", "Isothermal Armor");
	}
}
