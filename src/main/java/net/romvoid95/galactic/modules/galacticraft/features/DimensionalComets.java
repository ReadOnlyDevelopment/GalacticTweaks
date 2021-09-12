package net.romvoid95.galactic.modules.galacticraft.features;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.entities.EntityMeteor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.api.feature.Feature;
import net.romvoid95.galactic.core.GCTLog;
import net.romvoid95.galactic.modules.galacticraft.GalacticraftModuleConfig;

public class DimensionalComets extends Feature {

	public DimensionalComets() {
		this.category = "DimensionalComets";
		this.categoryComment = ">> INFO: Comets will not be allowed to spawn in the Nether <<\n"
				+ "specify where and how often asteroids will drop in set dimensions\n"
				+ "see file under `config\\GalacticTweaks\\ValidDimensions.txt` for valid dimension ID's\n";
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	private List<DimCometData> dataEntires = new ArrayList<>();
	private int logged = 0;

	@Override
	public void postInit() {
		for (String data : GalacticraftModuleConfig.dimID_spawnrate.get()) {
			int dimId = Integer.parseInt(data.split(":")[0]);
			double spawnRate = Double.parseDouble(data.split(":")[1]);
			dataEntires.add(new DimCometData(dimId, spawnRate));
			GCTLog.debug("Dimensional Comets: Dim ["+dimId+"], Freq: ["+spawnRate+"]");
		}
	}

	@SubscribeEvent
	public void entityLivingEvent(LivingEvent.LivingUpdateEvent event) {
		final EntityLivingBase entityLiving = event.getEntityLiving();
		if (entityLiving instanceof EntityPlayerMP) {
			this.onPlayerUpdate((EntityPlayerMP) entityLiving);
		}
	}

	private void onPlayerUpdate(EntityPlayerMP player) {
		for (DimCometData data : this.dataEntires) {
			this.meteors(player, data.getDimid(), data.getSpawnrate());
		}
	}

	protected void meteors(EntityPlayerMP player, int dimensionid, double spawnRate) {
		World world = player.world;
		WorldProvider provider = world.provider;
		int dimId = provider.getDimensionType().getId();
		int f;
		if (dimId == -1 || dimId == 1) {
			return;
		}
		if (dimId == dimensionid) {
			if (provider instanceof IGalacticraftWorldProvider) {
				IGalacticraftWorldProvider gcprovider = (IGalacticraftWorldProvider) provider;
				double x = gcprovider.getMeteorFrequency();
				double spRate = 0;
				if (x > 0 && logged < 3) {
					GCTLog.debug("Dimensional Comets: Dim "+dimensionid+" has preset MeteorFrequency of: " + x);
					logged++;
					if (spawnRate > x) {
						GCTLog.debug(
								"Dimensional Comets: Adjusting specified SpawnRate to subtract preset MeteorFrequency");
						logged++;
						spRate = spawnRate - x;
						GCTLog.debug("Dimensional Comets: Adjusted SpawnRate to " + spRate);
						logged++;
					} else {
						GCTLog.debug(
								"Dimensional Comets: Adjusting specified SpawnRate to add preset MeteorFrequency");
						logged++;
						spRate = spawnRate + x;
						GCTLog.debug("Dimensional Comets: Adjusted SpawnRate to " + spRate);
						logged++;
					}
				} else {
					if (spawnRate > x) {
						spRate = spawnRate - x;
					} else {
						spRate = spawnRate + x;
					}
				}
				f = (int) (750D * (1.0 / spRate));
			} else {
				f = (int) (750D * (1.0 / spawnRate));
			}
			this.run(world, f, player);
		}
	}

	private void run(World world, int f, EntityPlayerMP player) {
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

				final EntityMeteor meteor = new EntityMeteor(world, player.posX + x, 355D, player.posZ + z, motX, 0,
						motZ, 1);

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

				final EntityMeteor meteor = new EntityMeteor(world, player.posX + x, 355D, player.posZ + z, motX, 0,
						motZ, 6);

				if (!world.isRemote) {
					world.spawnEntity(meteor);
				}
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return GalacticraftModuleConfig.DIMENSIONAL_COMETS;
	}

	public class DimCometData {
		private int dimid;
		private double spawnrate;

		DimCometData(int id, double rate) {
			this.dimid = id;
			this.spawnrate = rate;
		}

		public int getDimid() {
			return dimid;
		}

		public double getSpawnrate() {
			return spawnrate;
		}
	}
}
