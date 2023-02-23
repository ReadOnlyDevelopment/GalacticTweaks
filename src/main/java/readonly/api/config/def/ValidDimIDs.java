package readonly.api.config.def;

import java.util.stream.Collectors;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.util.list.CelestialList;

public class ValidDimIDs {
	
	private String[] values;
	private String defaultVal;
	private String[] displayValues;

	public ValidDimIDs(CelestialList<CelestialBody> all) {
		for(CelestialBody body : all) {
			if(body.getName().equalsIgnoreCase("overworld")) {
				this.defaultVal = body.getName().replace(" ", "_");
			}
		}
		this.values = all.stream().map(CelestialBody::getName).map(s -> s.replace(" ", "_")).collect(Collectors.toList()).toArray(new String[all.size()]);
		this.displayValues = values; 
	}

	public String[] get() {
		return this.values;
	}
	
	public String getDefault() {
		return this.defaultVal;
	}
	
	public String[] getDisplayValues() {
		return this.displayValues;
	}
}
