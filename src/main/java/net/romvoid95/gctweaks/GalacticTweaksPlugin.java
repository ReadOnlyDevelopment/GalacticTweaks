package net.romvoid95.gctweaks;

import java.util.Map;

import javax.annotation.Nullable;

import org.spongepowered.asm.launch.MixinBootstrap;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.SortingIndex(Integer.MIN_VALUE)
public class GalacticTweaksPlugin implements IFMLLoadingPlugin {
	
	static {
		MixinBootstrap.init();
	}

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[0];
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Override
    @Nullable
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

}
