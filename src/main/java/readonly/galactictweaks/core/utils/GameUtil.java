package readonly.galactictweaks.core.utils;

import lombok.experimental.UtilityClass;
import net.minecraftforge.fml.common.FMLCommonHandler;

@UtilityClass
public final class GameUtil
{
    /**
     * Check if this is the client side.
     *
     * @return True if and only if we are on the client side
     */
    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getSide().isClient();
    }

    /**
     * Check if this is the server side.
     *
     * @return True if and only if we are on the server side
     */
    public static boolean isServer()
    {
        return FMLCommonHandler.instance().getSide().isServer();
    }
}
