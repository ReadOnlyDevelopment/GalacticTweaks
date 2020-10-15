package net.romvoid95.gctweaks.gc.features;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.entities.EntityMeteor;

import net.romvoid95.gctweaks.base.Feature;

public class DimensionalComets extends Feature {

	private static boolean	cometModification;
	private static String[]	dimensionID_spawnrate;

	@Override
	public String[] category() {
		return new String[] { "dimensional-comets" };
	}

	@Override
	public String comment() {
		return "You can specify where asteroids will drop via dimension IDs\nCheck GC dimension ID's here https://wiki.micdoodle8.com/wiki/Dimensions";
	}

	@Override
	public void syncConfig(Configuration config, String[] category) {
		cometModification = config.get(category[0], "comet-modification", false,
				"Set to true to specify what new dimensions asteroids drop").getBoolean();
		dimensionID_spawnrate = config.get(category[0], "dimension-id", new String[] { "0:1.0", "1:2.0" },
				"Data consisting of which Dimensions meteors will spawn in and the spawn-rate modification value.\nSpec: <dimID:rate> (Ex: -1:3.5)").getStringList();
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void entityLivingEvent(LivingEvent.LivingUpdateEvent event) {
		if (cometModification) {
			final EntityLivingBase entityLiving = event.getEntityLiving();
			if (entityLiving instanceof EntityPlayerMP) {
				this.onPlayerUpdate((EntityPlayerMP) entityLiving);
			}
		}
	}

	private void onPlayerUpdate(EntityPlayerMP player) {
		for (String data : dimensionID_spawnrate) {
			int dimId = Integer.parseInt(data.split(":")[0]);
			double spawnRate = Double.parseDouble(data.split(":")[1]);
			this.meteors(player, dimId, spawnRate);
		}
	}

	protected void meteors(EntityPlayerMP player, int dimensionid, double spawnRate) {
		World world = player.world;
		int f;
		if (world.provider.getDimensionType().getId() == dimensionid && !world.isRemote) {
			if (world.provider instanceof IGalacticraftWorldProvider) {
				f = (int) (((IGalacticraftWorldProvider) world.provider).getMeteorFrequency() * 750D * (1.0 / spawnRate));
			} else {
				f = (int) (750D * (1.0 / spawnRate));
			}
			if (world.rand.nextInt(f) == 0) {
				final EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

				if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId()) {
					int r = world.getMinecraftServer().getPlayerList().getViewDistance();
					int x, z;
					double motX, motZ;
					x = world.rand.nextInt(20) + 160;
					z = world.rand.nextInt(20) - 10;
					motX = world.rand.nextDouble() * 2 - 2.5D;
					motZ = world.rand.nextDouble() * 5 - 2.5D;
					int px = MathHelper.floor(player.posX);
					if ((x + px >> 4) - (px >> 4) >= r) {
						x = ((px >> 4) + r << 4) - 1 - px;
					}

					final EntityMeteor meteor = new EntityMeteor(world, player.posX + x, 355D, player.posZ
							+ z, motX, 0, motZ, 1);

					if (!world.isRemote) {
						world.spawnEntity(meteor);
					}
				}
			}

			if (world.rand.nextInt(f * 3) == 0) {
				final EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

				if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId()) {
					int r = world.getMinecraftServer().getPlayerList().getViewDistance();
					int x, z;
					double motX, motZ;
					x = world.rand.nextInt(20) + 160;
					z = world.rand.nextInt(20) - 10;
					motX = world.rand.nextDouble() * 2 - 2.5D;
					motZ = world.rand.nextDouble() * 5 - 2.5D;
					int px = MathHelper.floor(player.posX);
					if ((x + px >> 4) - (px >> 4) >= r) {
						x = ((px >> 4) + r << 4) - 1 - px;
					}

					final EntityMeteor meteor = new EntityMeteor(world, player.posX + x, 355D, player.posZ
							+ z, motX, 0, motZ, 6);

					if (!world.isRemote) {
						world.spawnEntity(meteor);
					}
				}
			}
		}
	}
}
