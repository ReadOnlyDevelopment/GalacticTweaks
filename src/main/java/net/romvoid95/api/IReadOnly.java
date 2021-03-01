package net.romvoid95.api;

import net.minecraft.launchwrapper.*;
import net.romvoid95.galactic.*;

public interface IReadOnly {
    String getModId();

    String getModName();

    String getVersion();

    int getBuildNum();

    default boolean isDevBuild() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    default GCTLogger getLog() {
        return GCTLogger.getRegisteredLogger(getModName()).orElse(new GCTLogger(getModName(), getBuildNum()));
    }
}
