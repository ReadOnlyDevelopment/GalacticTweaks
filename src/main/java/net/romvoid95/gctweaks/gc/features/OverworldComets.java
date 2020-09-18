package net.romvoid95.gctweaks.gc.features;

import micdoodle8.mods.galacticraft.core.entities.EntityMeteor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.gctweaks.base.Feature;

public class OverworldComets extends Feature {

	private static boolean overworldComets;
	private static double overworldCometSpawnRate;

	@Override
	public String[] category() {
		return new String[] {"overworld-comets"};
	}

	@Override
	public String comment() {
		return "have comets also drop in the overworld - extending realism even further";
	}

	@Override
	public void syncConfig(Configuration config, String[] category) {
		overworldComets = config
				.get(category[0], "overworldComets", false, "Set to true to enable comets in the Overworld").getBoolean();
		overworldCometSpawnRate = config.get(category[0], "overworldCometSpawnRate", 1.0D,
				"Set to a value between 0.0 and 0.9 to decrease meteor spawn").getDouble();
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void entityLivingEvent(LivingEvent.LivingUpdateEvent event) {
		if (overworldComets) {
			final EntityLivingBase entityLiving = event.getEntityLiving();
			if (entityLiving instanceof EntityPlayerMP) {
				this.onPlayerUpdate((EntityPlayerMP) entityLiving);
			}
		}
	}

	private void onPlayerUpdate(EntityPlayerMP player) {
		this.meteors(player);
	}

	protected void meteors(EntityPlayerMP player) {
		World world = player.world;
		if (world.provider.getDimensionType().getId() == 0) {
			final int f = (int) ((int) 5D * 750D * (1.0 / overworldCometSpawnRate));
			int e = world.rand.nextInt(f);
			if (e < 3) {
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

					final EntityMeteor meteor = new EntityMeteor(world, player.posX + x, 355D, player.posZ + z, motX, 0,
							motZ, 1);

					if (!world.isRemote) {
						world.spawnEntity(meteor);
//							String pos = meteor.getPosition().toString().replace("[BlockPos{", "[").replace("}]", "]");
//							String[] msg = { "[DEBUG] ", "Meteor has spawned at ", pos};
//							Utilz.sendColorizedMulti(closestPlayer, msg);
					}
				}
			}
		}
	}
}
