package net.romvoid95.api;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

import net.minecraftforge.common.*;
import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.client.config.*;
import net.minecraftforge.fml.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.romvoid95.api.config.annotation.*;
import net.romvoid95.api.config.def.*;
import net.romvoid95.api.config.values.*;
import net.romvoid95.api.versioning.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.feature.*;
import net.romvoid95.galactic.feature.Values.*;

public abstract class ReadOnlyConfig {

	private File configFile;
	private ConfigVersion Configversion;
	private final Class<?> clazz;
	protected List<OptValue> properties = new ArrayList<>();
	protected List<Field> features = new ArrayList<>();
	protected List<String> propOrder = new ArrayList<>();
	protected List<Category> configCats = new ArrayList<>();

	private Configuration config;

	protected ReadOnlyConfig(Class<?> clazz) {
		this.clazz = clazz;
		MinecraftForge.EVENT_BUS.register(this);

		List<Field> subClazzFields = setFeatureFields(clazz.getDeclaredFields());
		subClazzFields.forEach(f -> addFeatOpt(f));
	}

	protected File getConfigFile() {
		return configFile;
	}

	protected ConfigVersion getConfigversion() {
		return Configversion;
	}

	protected void setConfigFile(File configFile) {
		this.configFile = configFile;
	}

	protected void setConfigversion(ConfigVersion configversion) {
		Configversion = configversion;
	}

	protected void addConfigCat(Category configCat) {
		this.configCats.add(configCat);
	}

	protected boolean hasProperty(OptValue prop) {
		return this.properties.stream().anyMatch(property -> property.category().contentEquals(prop.category())
				&& property.key().contentEquals(prop.key()));
	}

	protected final OptBoolean addProperty(OptBoolean property) {
		this.addPropToList(property.key());
		this.addProp(property);
		return property;
	}

	protected final OptInteger addProperty(OptInteger property) {
		this.addPropToList(property.key());
		this.addProp(property);
		return property;
	}

	protected final OptString addProperty(OptString property) {
		this.addPropToList(property.key());
		this.addProp(property);
		return property;
	}

	protected final OptArrayInteger addProperty(OptArrayInteger property) {
		this.addPropToList(property.key());
		this.addProp(property);
		return property;
	}

	protected final OptArrayDouble addProperty(OptArrayDouble property) {
		this.addPropToList(property.key());
		this.addProp(property);
		return property;
	}

	protected final OptArrayString addProperty(OptArrayString property) {
		this.addPropToList(property.key());
		this.addProp(property);
		return property;
	}

	public void loadConfig() {

		config = initConfig();

		try {
			config.load();

			for (Field field : this.features) {
				GTFeature currFeature = field.getAnnotation(GTFeature.class);
				String key = currFeature.featureClass().getSimpleName().toLowerCase();
				try {
					field.setBoolean(this.clazz,
							config.get(Categories.FEATURES.get(), key, false, "Set 'true' to enable").getBoolean());
				} catch (IllegalAccessException ex) {
					GalacticTweaks.LOG.error("Field \"{}\" is not accessible?", field.getName());
					GalacticTweaks.LOG.catching(ex);
				}
			}

			for (OptValue prop : this.properties) {

				try {
					switch (prop.getType()) {
						case INTEGER:
							OptInteger propInt = (OptInteger) prop;
							int intVal;
							if(propInt.hasRange()) {
								intVal = config.get(
										propInt.category(),
										propInt.key(),
										propInt.get(),
										propInt.comment(),
										propInt.min(),
										propInt.max()
										).setLanguageKey(propInt.langKey()).getInt();
							}
							else {
								intVal = config.get(
										propInt.category(),
										propInt.key(),
										propInt.get(),
										propInt.comment()
										).setLanguageKey(propInt.langKey()).getInt();
							}
							propInt.set(intVal);
							break;

						case INTEGER_ARRAY:
							OptArrayInteger propIntArray = (OptArrayInteger) prop;
							int[] intArray;
							if(propIntArray.hasRange()) {
								intArray = config.get(
										propIntArray.category(),
										propIntArray.key(),
										propIntArray.get(),
										propIntArray.comment(),
										propIntArray.min(),
										propIntArray.max()
										).setLanguageKey(propIntArray.langKey()).getIntList();
							}
							else {
								intArray = config.get(
										propIntArray.category(),
										propIntArray.key(),
										propIntArray.get(),
										propIntArray.comment()
										).setLanguageKey(propIntArray.langKey()).getIntList();
							}
							propIntArray.set(intArray);
							break;

						case DOUBLE:
							OptDouble propDouble = (OptDouble) prop;
							double doubleVal;
							if(propDouble.hasRange()) {
								doubleVal = config.get(
										propDouble.category(),
										propDouble.key(),
										propDouble.get(),
										propDouble.comment(),
										propDouble.min(),
										propDouble.max()
										).setLanguageKey(propDouble.langKey()).getDouble();
							}
							else {
								doubleVal = config.get(
										propDouble.category(),
										propDouble.key(),
										propDouble.get(),
										propDouble.comment()
										).setLanguageKey(propDouble.langKey()).getDouble();
							}
							propDouble.set(doubleVal);
							break;

						case DOUBLE_ARRAY:
							OptArrayDouble propDoubleArray = (OptArrayDouble) prop;
							double[] doubleArrayVal;
							if(propDoubleArray.hasRange()) {
								doubleArrayVal = config.get(
										propDoubleArray.category(),
										propDoubleArray.key(),
										propDoubleArray.get(),
										propDoubleArray.comment(),
										propDoubleArray.min(),
										propDoubleArray.max()
										).setLanguageKey(propDoubleArray.langKey()).getDoubleList();
							}
							else {
								doubleArrayVal = config.get(
										propDoubleArray.category(),
										propDoubleArray.key(),
										propDoubleArray.get(),
										propDoubleArray.comment()
										).setLanguageKey(propDoubleArray.langKey()).getDoubleList();
							}
							propDoubleArray.set(doubleArrayVal);
							break;

						case BOOLEAN:
							OptBoolean propBool = (OptBoolean) prop;
							propBool.set(
									config.getBoolean(
											propBool.key(),
											propBool.category(),
											propBool.get(),
											propBool.comment(),
											propBool.langKey()
											));
							break;

						case STRING:
							OptString propString = (OptString) prop;
							String Stringvalue;
							if (propString.needsValidation()) {
								Stringvalue =
										config.getString(
												propString.key(),
												propString.category(),
												propString.get(),
												propString.comment(),
												propString.getValidValues(),
												propString.getValidValuesDisplay(),
												propString.langKey()
												);
							}
							else {
								Stringvalue =
										config.getString(
												propString.key(),
												propString.category(),
												propString.get(),
												propString.comment(),
												propString.langKey()
												);
							}
							propString.set(Stringvalue);
							break;
						case STRING_ARRAY:
							OptArrayString propStringArray = (OptArrayString) prop;
							propStringArray.set(
									config.get(
											propStringArray.category(),
											propStringArray.key(),
											propStringArray.get(),
											propStringArray.comment()
											).setLanguageKey(propStringArray.langKey()).getStringList());
							break;
					}
				} catch (Exception e) {
					GalacticTweaks.LOG.error("Issue with Prop: {} of type {}", prop.key(), prop.getType().name());
					GalacticTweaks.LOG.catching(e);
				} finally {
					config.setCategoryPropertyOrder(prop.category(), this.propOrder);
					config.setCategoryLanguageKey(prop.category(), prop.getConfigCat().getLangKey());
					config.setCategoryRequiresMcRestart(prop.category(), prop.getConfigCat().requiresMCRestart);
					config.setCategoryRequiresWorldRestart(prop.category(), prop.getConfigCat().requiresWorldRestart);
				}
			}
		} catch (Exception ignored) {
			GalacticTweaks.LOG.error("Uh, we had a problem loading config: {}", config.getConfigFile());
		}
		saveConfig();
	}

	protected Configuration initConfig() {
		return new Configuration(this.configFile, this.Configversion.toString());
	}

	protected void saveConfig() {
		if (config.hasChanged()) {
			config.save();
		}
	}

	protected Configuration getConfig() {
		return config;
	}

	protected ConfigCategory getCategory(Category category) {
		return config.getCategory(category.get());
	}

	protected List<IConfigElement> getElements() {
		List<IConfigElement> list = new ArrayList<>();
		ConfigCategory allFeatures = getCategory(Values.Categories.FEATURES).setLanguageKey("galactictweaks.config.gui.cat.allfeatures");
		ConfigCategory featureOpts = getCategory(Values.Categories.FEATURE_OPTS).setLanguageKey("galactictweaks.config.gui.cat.featureopts");
		list.add(new ConfigElement(allFeatures));
		list.add(new ConfigElement(featureOpts));
		return list;
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equalsIgnoreCase(Info.ID)) {
			saveConfig();
			loadConfig();
		}
	}

	public static class ConfigVersion extends Version {
		public static final ConfigVersion NULL_VERSION = new ConfigVersion("0.0.0");

		public ConfigVersion(String version) {
			super(version);
		}

		public ConfigVersion(Version version) {
			super(version.toString());
		}

		public ConfigVersion(int major, int minor, int patch, int build) {
			super(of(major) + "." + of(minor) + "." + of(patch) + "." + of(build));
		}

		private static String of(int val) {
			return String.valueOf(val);
		}

		public static boolean isVersionLessOrEqual(ConfigVersion comparate1, ConfigVersion comparate2) {
			Version c1 = new Version(comparate1.toString());
			Version c2 = new Version(comparate2.toString());
			return c1.isEqualTo(c2);
		}
	}

	// PRIVATE UTILTITY METHODS //

	private List<Field> setFeatureFields(Field[] fields)  {
		return Arrays.stream(fields)
				.filter((f) -> f.isAnnotationPresent(GTFeature.class))
				.sorted()
				.collect(Collectors.toList());
	}

	private void addPropToList(String propName) {
		for (String name : this.propOrder) {
			if (name.contentEquals(propName)) {
				this.propOrder.remove(name);
				break;
			}
		}
		this.propOrder.add(propName);
	}

	private void addProp(OptValue property) {
		for (OptValue prop : this.properties) {
			if (prop.key().contentEquals(property.key())) {
				this.properties.remove(prop);
				break;
			}
		}
		this.properties.add(property);
	}

	private void addFeatOpt(Field feature) {
		for (Field feat : this.features) {
			if (feat.getName().contentEquals(feature.getName())) {
				this.features.remove(feat);
				break;
			}
		}
		this.features.add(feature);
	}
}
