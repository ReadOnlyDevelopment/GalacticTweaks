package readonly.api;

import net.minecraft.launchwrapper.Launch;

public interface IReadOnly {
    String getModId();

    String getModName();

    String getVersion();

    default boolean isDevBuild() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}
