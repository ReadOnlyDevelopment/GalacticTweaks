package net.romvoid95.galactic.feature.client;

import static net.romvoid95.api.docs.Stability.*;

import micdoodle8.mods.galacticraft.api.world.*;
import net.minecraft.client.*;
import net.minecraft.client.audio.*;
import net.minecraftforge.fml.client.*;
import net.minecraftforge.fml.relauncher.*;
import net.romvoid95.api.*;
import net.romvoid95.api.docs.*;
import net.romvoid95.galactic.core.utils.*;
import net.romvoid95.galactic.feature.*;

@Doc(
		value = "No More Space Music",
		comment = "A Client-Side Only feature that will stop all custom music from Galacticraft from playing on other planets.",
		stability = STABLE
		)
public class NoSpaceMusic extends Feature  {

	public NoSpaceMusic() {
		super(NoSpaceMusic::new, EnumSide.CLIENT);
	}

	@Override
	public String category() {
		return "NoSpaceMusic";
	}

	@Override
	public String comment() {
		return "Stops all custom music on Planets";
	}

	@Override
	public boolean sidedProxy() {
		return true;
	}

	@Override
	public void proxyPostInit() {
		MusicTickerFake musicTicker = new MusicTickerFake(Minecraft.getMinecraft());
		Reflected.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), musicTicker, "mcMusicTicker", "field_147126_aw");
	}

	@SideOnly(value = Side.CLIENT)
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

	@Override
	public boolean isEnabled() {
		return FeatureConfigs.NO_SPACE_MUSIC;
	}
}
