package net.romvoid95.galactic.core.gc;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import micdoodle8.mods.galacticraft.api.galaxies.*;
import net.romvoid95.api.config.def.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.feature.*;

public class IOWriter {

	private final List<CelestialBody> planets;
	private final List<CelestialBody> moons;
	
	public static List<CelestialBody> allBodies = new ArrayList<>();

	private PrintWriter writer;
	
	public IOWriter() {
		planets = GalaxyRegistry.getRegisteredPlanets().values()
					.stream()
					.filter(CelestialBody::getReachable)
					.sorted()
					.collect(Collectors.toList());
		
		
		moons = GalaxyRegistry.getRegisteredMoons().values()
					.stream()
					.filter(CelestialBody::getReachable)
					.sorted()
					.collect(Collectors.toList());
		
		List<String> all = new ArrayList<>();
		planets.forEach(p -> {
			allBodies.add(p);
			all.add(p.getName().replace(" ", "_"));
		});
		moons.forEach(m -> {
			allBodies.add(m);
			all.add(m.getName().replace(" ", "_"));
		});

		FeatureConfigs.validSPawnDims = new ValidDimIDs(all);
	}

	public void handleFile(String name) {
		File dims = new File(GalacticTweaks.modFolder, name);
		try {
			if (dims.exists()) { dims.delete(); } 
			dims.createNewFile();
			writer = new PrintWriter(new FileWriter(dims));
		} catch (IOException localIOException) {}
	}
	
	public void write(String string) {
		writer.println(string);
	}

	public void nl() {
		writer.println("");
	}
	
	public void spacer() {
		writer.println("|------------------");
	}
	
	public void NOTICE() {
		writer.println("### NOTICE ###");
	}

	public void title(String title) {
		nl();
		nl();
		writer.println("| ### "+title+" ###");
		writer.println("|------------------");
	}

	public void writePlanets() {
		Collections.sort(planets);
		planets.forEach(planet -> writer.println(formatted(planet)));
	}
	
	public void writeMoons() {
		Collections.sort(moons);
		moons.forEach(moon -> writer.println(formatted(moon)));
	}
	
	@Override
	public void finalize() {
		writer.flush();
		writer.close();
	}
	
	private String formatted(CelestialBody body) {
		return String.format("| %-15s %15s", body.getName(), body.getDimensionID());
	}
}
