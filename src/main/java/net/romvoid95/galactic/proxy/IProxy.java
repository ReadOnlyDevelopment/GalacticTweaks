package net.romvoid95.galactic.proxy;

import javax.annotation.*;

import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.event.*;

public interface IProxy {
    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    @Nullable EntityPlayer getClientPlayer();
}
