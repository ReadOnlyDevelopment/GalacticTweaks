package net.romvoid95.plugin;

import java.util.*;

import net.minecraftforge.fml.relauncher.*;

@IFMLLoadingPlugin.SortingIndex(1001)
public class GalacticTweaksPlugin implements IFMLLoadingPlugin
{
	public static boolean InDevEnv = false;

	@Override
	public String[] getASMTransformerClass()
	{
		return new String[] { Transformer.class.getName() };
	}

	@Override
	public String getModContainerClass()
	{
		return null;
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
		InDevEnv = !(Boolean) data.get("runtimeDeobfuscationEnabled");
	}

	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}
