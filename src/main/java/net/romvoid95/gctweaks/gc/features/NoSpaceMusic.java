package net.romvoid95.gctweaks.gc.features;

import java.lang.reflect.Field;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.romvoid95.gctweaks.base.Feature;

public class NoSpaceMusic extends Feature {

	@Override
	public String[] category() {
		return new String[] {"music"};
	}

	@Override
	public String comment() {
		return "Stops all custom music on Planets";
	}

	private boolean disableSpaceMusic;

	@Override
	public void syncConfig(Configuration config, String[] category) {
		disableSpaceMusic = config.get(category[0], "disableSpaceMusic", false, "Set to true if you want to disable the music played on Galacticraft Planets").getBoolean();
	}

	@Override
	public boolean sidedProxy() {
		return true;
	}

	@Override
	public void proxyPostInit() {
		if (disableSpaceMusic) {
			try {
				Field ftc = Minecraft.getMinecraft().getClass().getDeclaredField(GCCoreUtil.isDeobfuscated() ? "mcMusicTicker" : "field_147126_aw");
				ftc.setAccessible(true);
				ftc.set(Minecraft.getMinecraft(), new MusicTickerFake(Minecraft.getMinecraft()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class MusicTickerFake extends MusicTicker {

		public MusicTickerFake(Minecraft mcIn) {
			super(mcIn);
		}

		@Override
		public void update() {
			if (FMLClientHandler.instance().getWorldClient() != null && FMLClientHandler.instance().getWorldClient().provider instanceof IGalacticraftWorldProvider) {
				if (this.currentMusic != null) {
					this.mc.getSoundHandler().stopSound(this.currentMusic);
				}
			}
		}
	}
}
