package readonly.galactictweaks.core.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.launchwrapper.Launch;

@UtilityClass
public class DeveloperUtil {

    /**
     * Check if this is a deobfuscated (development) environment.
     *
     * @return True if and only if we are running in a deobfuscated environment
     */
    public static boolean inDeveloperEnvironment() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}
