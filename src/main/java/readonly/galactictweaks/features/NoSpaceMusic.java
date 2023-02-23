package readonly.galactictweaks.features;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import readonly.api.feature.Feature;
import readonly.galactictweaks.core.utils.Reflected;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModuleConfig;

public class NoSpaceMusic extends Feature  {

	public NoSpaceMusic() {
		this.category = "NoSpaceMusic";
		this.categoryComment =  "Stops all custom music on Planets";
	}

	@Override
	public void proxyPostInit() {
		MusicTickerFake musicTicker = new MusicTickerFake(Minecraft.getMinecraft());
		Reflected.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), musicTicker, "mcMusicTicker", "field_147126_aw");
	}

	@Override
	public boolean sidedProxy() {
		return false;
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
		return GalacticraftModuleConfig.NO_SPACE_MUSIC;
	}
}
