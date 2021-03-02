package net.romvoid95.galactic.proxy;

import javax.annotation.*;

import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.event.*;
import net.romvoid95.api.registry.*;

public interface IProxy {
    void preInit(GCTRegistry registry, FMLPreInitializationEvent event);

    void init(GCTRegistry registry, FMLInitializationEvent event);

    void postInit(GCTRegistry registry, FMLPostInitializationEvent event);

    @Nullable EntityPlayer getClientPlayer();
}
