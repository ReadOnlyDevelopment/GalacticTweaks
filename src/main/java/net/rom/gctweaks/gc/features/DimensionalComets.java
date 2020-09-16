package net.rom.gctweaks.gc.features;

import micdoodle8.mods.galacticraft.core.entities.EntityMeteor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.rom.gctweaks.base.Feature;

public class DimensionalComets extends Feature {

    private static boolean cometModification;
    private static int[] dimensionID;
    private static double cometSpawnRate;


    @Override
    public String[] category()  {
        return new String[] {"dimensional-comets"};
    }

    @Override
    public String comment()  {
        return "You can specify where asteroids will drop via dimension IDs\nCheck GC dimension ID's here https://wiki.micdoodle8.com/wiki/Dimensions";
    }

    @Override
    public void syncConfig(Configuration config, String[] category) {
        cometModification = config
                .get(category[0],"comet-modification", false, "Set to true to modify comets on other dimensions").getBoolean();
        cometSpawnRate = config.get(category[0], "cometSpawnRate", 1.0D, "Specify the global asteroid spawn rate").getDouble();
        dimensionID = config.get(category[0], "dimension-id", new int[] {-1, 0, 1}, "dimension IDs for asteroids").getIntList();
    }

    @Override
    public boolean usesEvents() { return true; }

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
        for (int id : dimensionID) {
            this.meteors(player, id);

        }
    }

    protected void meteors(EntityPlayerMP player, int dimensionid) {
        World world = player.world;
        if (world.provider.getDimensionType().getId() == dimensionid ) {
            final int f = (int) ((int) 5D * 750D * (1.0 / cometSpawnRate));
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
