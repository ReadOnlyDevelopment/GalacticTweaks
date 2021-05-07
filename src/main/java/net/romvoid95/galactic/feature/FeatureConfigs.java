package net.romvoid95.galactic.feature;

import java.lang.reflect.*;

import net.romvoid95.api.*;
import net.romvoid95.api.config.annotation.*;
import net.romvoid95.api.config.def.*;
import net.romvoid95.api.config.values.*;
import net.romvoid95.galactic.feature.Values.*;
import net.romvoid95.galactic.feature.client.*;
import net.romvoid95.galactic.feature.common.*;

public class FeatureConfigs extends ReadOnlyConfig {
	
	public static FeatureConfigs INSTANCE;

	// ALL FEATURE ENABLERS
	@GTFeature(featureClass = UnlockSchematics.class)
	public static boolean UNLOCK_SCHEMATICS;
	@GTFeature(featureClass = OxygenSpawnGear.class)
	public static boolean OXYGEN_SPAWN_GEAR;
	@GTFeature(featureClass = SpawnDimension.class)
	public static boolean SPAWN_DIMENSION;
	@GTFeature(featureClass = SpaceRaceFeature.class)
	public static boolean SPACE_RACE_FEATURE;
	@GTFeature(featureClass = SeperateAddonPlanets.class)
	public static boolean SEPERATE_ADDONPLANETS;
	@GTFeature(featureClass = OxygenToggle.class)
	public static boolean OXYGEN_TOGGLE;
	@GTFeature(featureClass = MobsBreatheInSpace.class)
	public static boolean MOBS_BREATHE_IN_SPACE;
	@GTFeature(featureClass = DimensionalComets.class)
	public static boolean DIMENSIONAL_COMETS;
	@GTFeature(featureClass = CompressorFixes.class)
	public static boolean COMPRESSOR_FIXES;
	@GTFeature(featureClass = NoSpaceMusic.class)
	public static boolean NO_SPACE_MUSIC;
	@GTFeature(featureClass = FixAsmodeusMapIcons.class)
	public static boolean FIX_MAP_ICONS;
//	@GTFeature(featureClass = HubFeature.class)
//	public static boolean SERVER_HUB;
	
	// SERVER HUB
//	public static OptBoolean inOwnSystem;
//	public static OptString celestialType;
//	public static OptDouble distance;
//	public static OptDouble size;
//	public static OptArrayString atmosphereGases;
//	public static OptDouble gravity;
//	public static OptInteger daylength;
//	public static OptString skyColor;
//	public static OptString fogColor;
//	public static OptBoolean hasSunset;
	
	// UNLOCK SCHEMATICS
	public static OptArrayInteger schematicIDs;
	
	//SPAWN DIM
	public static OptBoolean useCoord;
	public static OptBoolean firstJoinOnly;
	public static OptBoolean everyDeath;
	public static OptString SpawnDim;
	public static ValidDimIDs validSPawnDims;
	public static OptString spawnPos;
	
	// SEPERATE PLANETS
	public static OptString modid;
	
	// BREATHEABLE DIMS
	public static OptArrayInteger breatheableDims;
	public static OptArrayInteger nonBreatheableDims;
	
	// DIMENSIONAL COMETS
	public static OptArrayString dimID_spawnrate;
	
	//SPAWN GEAR
	public static OptString tanksValue;
	public static OptString thermalArmor;
	public static OptBoolean includeThermals;
	public static OptBoolean includeParachute;
	public static OptBoolean includeFreqModule;
	public static OptBoolean includeShieldController;

	public FeatureConfigs(Class<?> clazz, ConfigVersion configVersion) {
		super(clazz);
		INSTANCE = this;
		this.setConfigversion(configVersion);

		for(Field field : FeatureConfigs.class.getDeclaredFields()) {
			if(field.isAnnotationPresent(GTFeature.class)) {
				this.addFeatureOpt(field);
			}
		}

		schematicIDs = new OptArrayInteger(Keys.schematicIDs, Categories.UNLOCK_SCHEMATICS, Comments.SCHEMIDS, 98, 99);
		this.addProperty(schematicIDs);
		
		useCoord = new OptBoolean(Keys.useCoord, Categories.SPAWN_DIMENSION, Comments.USECOORD, false);
		this.addProperty(useCoord);
		
		firstJoinOnly = new OptBoolean(Keys.firstJoinOnly, Categories.SPAWN_DIMENSION, Comments.FIRSTJOIN, true);
		this.addProperty(firstJoinOnly);
		
		everyDeath = new OptBoolean(Keys.everyDeath, Categories.SPAWN_DIMENSION, Comments.EVERYRESPAWN, false);
		this.addProperty(everyDeath);

		spawnPos = new OptString(Keys.spawnPos, Categories.SPAWN_DIMENSION, Comments.SPAWNPOS, "128.64.128");
		this.addProperty(spawnPos);
		
		modid = new OptString(Keys.modid, Categories.SEPERATE_ADDONPLANETS, Comments.MODID, Validators.VALIDMODIDS);
		this.addProperty(modid);
		
		breatheableDims = new OptArrayInteger(Keys.breatheableDims, Categories.OXYGEN_TOGGLE, Comments.BREATHEDIMS, 98, 99);
		this.addProperty(breatheableDims);
		
		nonBreatheableDims = new OptArrayInteger(Keys.nonBreatheableDims, Categories.OXYGEN_TOGGLE, Comments.NOBREATHEDIMS, 98, 99);
		this.addProperty(nonBreatheableDims);
		
		dimID_spawnrate = new OptArrayString(Keys.dimID_spawnrate, Categories.DIMENSIONAL_COMETS, Comments.DIMSPAWNRATE, "0:1.0", "1:2.0");
		this.addProperty(dimID_spawnrate);
		
		tanksValue = new OptString(Keys.tanksValue, Categories.OXYGEN_SPAWN_GEAR, Comments.TANKVALUE, Validators.TANKVALUES);
		this.addProperty(tanksValue);
		
		thermalArmor = new OptString(Keys.thermalArmor, Categories.OXYGEN_SPAWN_GEAR, Comments.THERMALVALUE, Validators.THERMALVALUES);
		this.addProperty(thermalArmor);
		
		includeThermals = new OptBoolean(Keys.includeThermals, Categories.OXYGEN_SPAWN_GEAR, Comments.OXYSPAWNOPTS, false);
		this.addProperty(includeThermals);
		
		includeParachute = new OptBoolean(Keys.includeParachute, Categories.OXYGEN_SPAWN_GEAR, Comments.OXYSPAWNOPTS, false);
		this.addProperty(includeParachute);
		
		includeFreqModule = new OptBoolean(Keys.includeFreqModule, Categories.OXYGEN_SPAWN_GEAR, Comments.OXYSPAWNOPTS, false);
		this.addProperty(includeFreqModule);
		
		includeShieldController = new OptBoolean(Keys.includeShieldController, Categories.OXYGEN_SPAWN_GEAR, Comments.OXYSPAWNOPTS, false);
		this.addProperty(includeShieldController);
	}
	
	public void addValidDims() {
		SpawnDim = new OptString(Keys.spawnDimId, Categories.SPAWN_DIMENSION, Comments.SPAWNDIM, validSPawnDims);
		this.addProperty(SpawnDim);
	}
}
