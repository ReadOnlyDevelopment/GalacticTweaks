package readonly.api.event;

import net.minecraftforge.fml.common.event.*;

public interface IEventProcessor {
	default void preInit(FMLPreInitializationEvent event) {
	}

	default void init(FMLInitializationEvent event) {
	}

	default void postInit(FMLPostInitializationEvent event) {
	}
}
