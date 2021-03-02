package net.romvoid95.api;

import net.minecraft.launchwrapper.*;

public interface IReadOnly {
    String getModId();

    String getModName();

    String getVersion();

    default boolean isDevBuild() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}
