package net.romvoid95.gctweaks.base.core.entityutils;

import java.util.function.Function;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.romvoid95.gctweaks.GalacticTweaks;
import net.romvoid95.gctweaks.Ref;

public class EntityRegistryHelper {
	private final IForgeRegistry<EntityEntry> registry;

	private int id = 0;

	EntityRegistryHelper(IForgeRegistry<EntityEntry> registry) {
		this.registry = registry;
	}

	private static String toString(ResourceLocation registryName) {
		return registryName.getResourceDomain() + "." + registryName.getResourcePath();
	}

	final <T extends Entity> EntityEntryBuilder<T> builder(ResourceLocation registryName, Class<T> entity, Function<World, T> factory) {
		return EntityEntryBuilder.<T>create().id(registryName, id++).name(toString(registryName)).entity(entity).factory(factory);
	}

	final <T extends Entity> void registerEntity(ResourceLocation registryName, Class<T> entity, Function<World, T> factory, int backgroundEggColour, int foregroundEggColour) {
		registerEntity(registryName, entity, factory, backgroundEggColour, foregroundEggColour, 80, 3, true);
	}

	final <T extends Entity> void registerEntity(ResourceLocation registryName, Class<T> entity, Function<World, T> factory, int backgroundEggColour, int foregroundEggColour, int trackingRange, int updateInterval, boolean sendVelocityUpdates) {
		registry.register(builder(registryName, entity, factory).tracker(trackingRange, updateInterval, sendVelocityUpdates).egg(backgroundEggColour, foregroundEggColour).build());
	}

	final <T extends Entity> void registerEntity(ResourceLocation registryName, Class<T> entity, Function<World, T> factory) {
		registerEntity(registryName, entity, factory, 80, 3, true);
	}

	final <T extends Entity> void registerEntity(ResourceLocation registryName, Class<T> entity, Function<World, T> factory, int trackingRange, int updateInterval, boolean sendVelocityUpdates) {
		registry.register(builder(registryName, entity, factory).tracker(trackingRange, updateInterval, sendVelocityUpdates).build());
	}
	
	public static class Register {
		
		private final String modId = Ref.MOD_ID;
		private final Object mod = GalacticTweaks.class;
		
		private int lastEntityId = -1;

		public void registerEntity (Class<? extends Entity> entityClass, String key) {
			registerEntity(entityClass, key, ++this.lastEntityId, this.mod, 64, 20, true);
		}

		public void registerEntity (Class<? extends Entity> entityClass, String key, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
			registerEntity(entityClass, key, ++this.lastEntityId, this.mod, trackingRange, updateFrequency, sendsVelocityUpdates);
		}

		public void registerEntity (Class<? extends Entity> entityClass, String key, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
			ResourceLocation resource = new ResourceLocation(this.modId, key);
			EntityRegistry
			.registerModEntity(resource, entityClass, key, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
		}

		public void registerEntity (Class<? extends Entity> entityClass, String key, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
			registerEntity(entityClass, key, ++this.lastEntityId, this.mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
		}

		public void registerEntity (Class<? extends Entity> entityClass, String key, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
			ResourceLocation resource = new ResourceLocation(this.modId, key);
			EntityRegistry
			.registerModEntity(resource, entityClass, key, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
		}
	}
}
