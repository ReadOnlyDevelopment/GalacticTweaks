package readonly.galactictweaks.features.oxygengear;

import static readonly.galactictweaks.Info.ID;

import java.util.List;

import com.google.common.collect.ImmutableList;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.player.GCCapabilities;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.inventory.InventoryExtended;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import readonly.api.config.IOrdered;
import readonly.api.feature.Feature;
import readonly.galactictweaks.core.utils.ItemStackUtil;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModuleConfig;

public class OxygenSpawnGear extends Feature implements IOrdered {

    public static String comment = "Allows Players to Spawn With Oxygen Items Equipped";
    
	public OxygenSpawnGear() {
		this.category = "OxygenSpawnGear";
	}

	@Override
	public void addProp() {
		this.propOrder.add(GalacticraftModuleConfig.tanksValue.key());
		this.propOrder.add(GalacticraftModuleConfig.thermalArmor.key());
		this.propOrder.add(GalacticraftModuleConfig.includeThermals.key());
		this.propOrder.add(GalacticraftModuleConfig.includeParachute.key());
		this.propOrder.add(GalacticraftModuleConfig.includeFreqModule.key());
		this.propOrder.add(GalacticraftModuleConfig.includeShieldController.key());
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void playerLogin(PlayerLoggedInEvent event) {

		boolean para = GalacticraftModuleConfig.includeParachute.get();
		boolean shield = GalacticraftModuleConfig.includeShieldController.get();
		boolean freq = GalacticraftModuleConfig.includeFreqModule.get();
		boolean therm = GalacticraftModuleConfig.includeThermals.get();

		final EntityPlayer player = event.player;
		final NBTTagCompound entityData = player.getEntityData();
		final NBTTagCompound persistedData = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, persistedData);
		final String key = ID + ":" + "ReceivedOxygen";

		if (!persistedData.getBoolean(key)) {
			GCPlayerStats gcstats = event.player.getCapability(GCCapabilities.GC_STATS_CAPABILITY, null);
			InventoryExtended inv = new InventoryExtended();
			inv.setInventorySlotContents(0, ItemStackUtil.itemStack(GCItems.oxMask));
			inv.setInventorySlotContents(1, ItemStackUtil.itemStack(GCItems.oxygenGear));
			inv.setInventorySlotContents(2, setTanks());
			inv.setInventorySlotContents(3, setTanks());
			if (para) {
				inv.setInventorySlotContents(4, ItemStackUtil.itemStack(GCItems.parachute));
			}
			if (shield) {
				inv.setInventorySlotContents(10, ItemStackUtil.itemStack(VenusItems.basicItem, 0));
			}
			if (freq) {
				inv.setInventorySlotContents(5, ItemStackUtil.itemStack(GCItems.basicItem, 19));
			}
			if (therm) {
				inv.setInventorySlotContents(6, setThermals(0));
				inv.setInventorySlotContents(7, setThermals(1));
				inv.setInventorySlotContents(8, setThermals(2));
				inv.setInventorySlotContents(9, setThermals(3));
			}
			gcstats.setExtendedInventory(inv);
			persistedData.setBoolean(key, true);
		} else {
			// Do Nothing
		}
	}

	private static ItemStack setTanks() {
		String val = GalacticraftModuleConfig.tanksValue.get();
		ItemStack tank = null;
		switch (val) {
			case "heavy":
				tank = ItemStackUtil.itemStack(GCItems.oxTankHeavy);
				break;
			case "medium":
				tank = ItemStackUtil.itemStack(GCItems.oxTankMedium);
				break;
			case "light":
				tank = ItemStackUtil.itemStack(GCItems.oxTankLight);
			default:
				break;
		}
		return tank;
	}

	private static ItemStack setThermals(int meta) {
		String val = GalacticraftModuleConfig.thermalArmor.get();
		ItemStack thermal = null;
		switch (val) {
			case "isothermal":
				thermal = ItemStackUtil.itemStack(VenusItems.thermalPaddingTier2, meta);
				break;
			case "thermal":
				thermal = ItemStackUtil.itemStack(AsteroidsItems.thermalPadding, meta);
				break;
			default:
				break;
		}
		return thermal;
	}

	@Override
	public void ServerStartingEvent(FMLServerStartingEvent event) {
		if (isEnabled()) {
			event.registerServerCommand(new OxygenSpawnGear.CommandOxygenReset());
		}
	}

	@Override
	public boolean isEnabled() {
		return GalacticraftModuleConfig.OXYGEN_SPAWN_GEAR;
	}

	public class CommandOxygenReset extends CommandBase {

		@Override
		public String getName() {
			return "oxygenreset";
		}

		@Override
		public List<String> getAliases() {
			return ImmutableList.of("oxyreset");
		}

		@Override
		public String getUsage(ICommandSender sender) {
			StringBuilder aliases = new StringBuilder();
			aliases.append(String.join(" | ", getAliases()));

			return String.format("%sUsage: %s/%s <playername>\n%sAliases: %s%s", TextFormatting.RED,
					TextFormatting.AQUA, getName(), TextFormatting.RED, TextFormatting.GREEN, aliases.toString());
		}

		@Override
		public int getRequiredPermissionLevel() {
			return 4;
		}
		
		@Override
		public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
				BlockPos pos) {
			if (args.length == 1) {
				return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
			}
			return null;
		}

		@Override
		public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
			if (args.length > 0 && args.length < 2) {
				final EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[0]);

				if (player != null) {
					final NBTTagCompound entityData = player.getEntityData();
					final NBTTagCompound persistedData = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
					entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, persistedData);
					final String key = ID + ":" + "ReceivedOxygen";
					persistedData.setBoolean(key, false);
					String line = String.format("Sucessfully reset %s's OxygenGear-On-Join setting", player.getName());
					sender.sendMessage(new TextComponentString(line));
				}
			} else {
				sender.sendMessage(new TextComponentString(getUsage(sender)));
			}
		}
	}
}
