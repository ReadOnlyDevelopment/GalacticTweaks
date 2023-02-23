package readonly.galactictweaks.features.admintools;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import galaxyspace.systems.SolarSystem.SolarSystemBodies;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.server.permission.PermissionAPI;
import readonly.api.GalacticraftAddon;
import readonly.galactictweaks.GCTweaks;
import readonly.galactictweaks.core.GCTLog;

public class CommandAdminTeleport extends CommandBase
{

    @Override
    public String getName()
    {
        return "admintp";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 4;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return PermissionAPI.hasPermission((EntityPlayer) sender.getCommandSenderEntity(), GCTweaks.NODE_ADMINTP);
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return String.format("%sUsage: %s/%s <planet>", TextFormatting.RED, TextFormatting.AQUA, getName(),
            TextFormatting.RED, TextFormatting.GREEN);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
    {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, GalaxyRegistry.getAllReachableBodies().stream()
                .map(CelestialBody::getName).collect(Collectors.toList()));
        } else {
            return args.length > 1 && args.length <= 4 ? getTabCompletionCoordinate(args, 1, pos)
                : Collections.emptyList();
        }
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 1) {
            sender.sendMessage(new TextComponentString(getUsage(sender)));
        } else {
            Optional<CelestialBody> maybeNullBody = GalaxyRegistry.getAllReachableBodies().stream()
                .filter(c -> c.getName().equalsIgnoreCase(args[0])).findFirst();
            if (maybeNullBody.isPresent()) {
                final CelestialBody body = maybeNullBody.get();
                EntityPlayerMP entity = getCommandSenderAsPlayer(sender);
                MinecraftServer mcServer = entity.getServer();
                final WorldServer world = mcServer.getWorld(body.getDimensionID());
                BlockPos pos;
                if (args.length > 1) {
                    CommandBase.CoordinateArg x = parseCoordinate(entity.posX, args[1], true);
                    CommandBase.CoordinateArg y = parseCoordinate(entity.posY, args[2], -4096, 4096, false);
                    CommandBase.CoordinateArg z = parseCoordinate(entity.posZ, args[3], true);

                    GCTLog.info("Position: {}, {}, {}", x.getAmount(), y.getAmount(), z.getAmount());

                    pos = new BlockPos(x.getAmount(), y.getAmount(), z.getAmount());
                } else {
                    int y = world.getChunkFromBlockCoords(entity.getPosition()).getHeight(entity.getPosition());
                    pos = new BlockPos(entity.posX, y, entity.posZ);
                }
                if (!entity.isCreative()) {
                    sender.sendMessage(new TextComponentString(
                        TextFormatting.RED + "You must be in Creative Mode to use Admin Teleport"));
                }

                WorldUtil.teleportEntitySimple(world, body.getDimensionID(), entity, new Vector3(pos));
                int asteroidId = AsteroidsModule.planetAsteroids.getDimensionID();

                if (body.getDimensionID() == asteroidId) {
                    entity.capabilities.isFlying = true;
                    entity.sendPlayerAbilities();
                }

                if (GalacticraftAddon.GALAXYSPACE.isLoaded()) {
                    int kBeltId = SolarSystemBodies.planetKuiperBelt.getDimensionID();

                    if (body.getDimensionID() == kBeltId) {
                        entity.capabilities.isFlying = true;
                        entity.sendPlayerAbilities();
                    }
                }
            }

        }
    }
}
