package net.rom.gctweaks.base.core.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;

public final class GameUtil {
	private GameUtil() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * Check if this is the client side.
	 *
	 * @return True if and only if we are on the client side
	 */
	public static boolean isClient() {
		return FMLCommonHandler.instance().getSide().isClient();
	}

	/**
	 * Check if this is the server side.
	 *
	 * @return True if and only if we are on the server side
	 */
	public static boolean isServer() {
		return FMLCommonHandler.instance().getSide().isServer();
	}

	/**
	 * Check if this is a deobfuscated (development) environment.
	 *
	 * @return True if and only if we are running in a deobfuscated environment
	 */
	public static boolean isDeobfuscated() {
		return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}

	public static Minecraft getMinecraft() {
		return FMLClientHandler.instance().getClient();
	}

	/**
	 * Determine if tooltips should be calculated, call in
	 * {@link net.minecraftforge.event.entity.player.ItemTooltipEvent} handlers.
	 * This can prevent tooltip events from being processed at unnecessary times
	 * (world loading/closing), while still allowing JEI to build its cache. JEI
	 * tooltip caches are done in {@link LoaderState#AVAILABLE}, in-game is
	 * {@link LoaderState#SERVER_STARTED}.
	 *
	 */
	public static boolean shouldCalculateTooltip() {
		LoaderState state = Loader.instance().getLoaderState();
		// These states have no reason to go through tooltips that I can tell, but they
		// do.
		return state != LoaderState.INITIALIZATION && state != LoaderState.SERVER_ABOUT_TO_START
				&& state != LoaderState.SERVER_STOPPING;
	}
}
