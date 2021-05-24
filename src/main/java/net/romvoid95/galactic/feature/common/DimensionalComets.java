package net.romvoid95.galactic.feature.common;

import java.util.*;

import static net.romvoid95.api.docs.Stability.*;

import micdoodle8.mods.galacticraft.api.world.*;
import micdoodle8.mods.galacticraft.core.entities.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.romvoid95.api.*;
import net.romvoid95.api.docs.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.feature.*;

@Doc(
		value = "Dimensional Comets",
		comment = "Feature that allows you to have Galacticraft comets spawn and fall in other "
				+ "dimensions as well as adjusting their spawnrate\n"
				+ "Note: Comets will not be allowed to spawn in the Nether\n"
				+ "Tip: see file under `config/GalacticTweaks/ValidDimensions.txt` for valid dimension ID's",
				stability = STABLE
		)
public class DimensionalComets extends Feature {

	public DimensionalComets() {
		super(DimensionalComets::new, EnumSide.COMMON);
	}

	@Override
	public String category() {
		return "dimensionComets";
	}

	@Override
	public String comment() {
		return ">> INFO: Comets will not be allowed to spawn in the Nether <<\n"
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
		for (String data : FeatureConfigs.dimID_spawnrate.get()) {
			int dimId = Integer.parseInt(data.split(":")[0]);
			double spawnRate = Double.parseDouble(data.split(":")[1]);
			dataEntires.add(new DimCometData(dimId, spawnRate));
			GalacticTweaks.LOG.debug("Dimensional Comets: Dim [{}], Freq: [{}]", dimId, spawnRate);
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
		if (world.provider.getDimensionType().getId() == -1) {
			return;
		}
		if (dimId == dimensionid) {
			if (provider instanceof IGalacticraftWorldProvider) {
				IGalacticraftWorldProvider gcprovider = (IGalacticraftWorldProvider) provider;
				double x = gcprovider.getMeteorFrequency();
				double spRate = 0;
				if (x > 0 && logged < 3) {
					GalacticTweaks.LOG.debug("Dimensional Comets: Dim {} has preset MeteorFrequency of: {}",
							dimensionid, x);
					logged++;
					if (spawnRate > x) {
						GalacticTweaks.LOG.debug(
								"Dimensional Comets: Adjusting specified SpawnRate to subtract preset MeteorFrequency");
						logged++;
						spRate = spawnRate - x;
						GalacticTweaks.LOG.debug("Dimensional Comets: Adjusted SpawnRate to {} ", spRate);
						logged++;
					} else {
						GalacticTweaks.LOG.debug(
								"Dimensional Comets: Adjusting specified SpawnRate to add preset MeteorFrequency");
						logged++;
						spRate = spawnRate + x;
						GalacticTweaks.LOG.debug("Dimensional Comets: Adjusted SpawnRate to {} ", spRate);
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
		return FeatureConfigs.DIMENSIONAL_COMETS;
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
