package net.romvoid95.gctweaks.gc.features.buggy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.romvoid95.gctweaks.base.Feature;
import net.romvoid95.gctweaks.gc.features.buggy.client.RenderBuggy;
import net.romvoid95.gctweaks.gc.features.buggy.entity.EntityTeamBuggy;

public class TwoPersonBuggy extends Feature {
	
	private boolean enableBuggy;
	public static double maxSpeed;
	public static double accel;
	public static double turnFactor;

	@Override
	public String[] category() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String comment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void syncConfig(Configuration config, String[] category) {
		
		enableBuggy = config
				.get(category[0], "00-Enable-TeamBuggy", false, "Set to true to enable the Team Buggy Vehicle")
				.getBoolean();
		maxSpeed = config
				.get(category[0], "01-Max Speed", 0.5, "Set the max speed the buggy can go")
				.getDouble();
		accel           = config
				.get(category[0], "02-Acceleration-Rate", 0.2, "Set the acceleration rate\n"
						+ "## Sensitive to major changes in rate")
				.getDouble();
		turnFactor           = config
				.get(category[0], "03-Turn-Factor", 3.0, "Set the turn factor")
				.getDouble();
		
	}

	
	@Override
	public void proxyPostInit () {
		if(this.enableBuggy) {
			RenderingRegistry.registerEntityRenderingHandler(EntityTeamBuggy.class, RenderBuggy::new);
		}
		
	}
}
