package net.romvoid95.galactic.feature.common;

import static net.romvoid95.galactic.Info.*;

import java.util.*;

import com.google.common.collect.*;

import static net.romvoid95.api.docs.Stability.*;

import micdoodle8.mods.galacticraft.core.*;
import micdoodle8.mods.galacticraft.core.entities.player.*;
import micdoodle8.mods.galacticraft.core.inventory.*;
import micdoodle8.mods.galacticraft.planets.asteroids.items.*;
import micdoodle8.mods.galacticraft.planets.venus.*;
import net.minecraft.command.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.server.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.*;
import net.romvoid95.api.*;
import net.romvoid95.api.config.*;
import net.romvoid95.api.docs.*;
import net.romvoid95.galactic.core.utils.*;
import net.romvoid95.galactic.feature.*;

@Doc(
		value = "Spawn With Oxygen Gear",
		comment = "Feature that allows modpack makers to have players spawn with the configured Oxygen gear when \n"
				+ "they join a world for the first time. Every slot available in the Galacticraft tab can be \n"
				+ "configured.  This feature also adds an Admin/OP-Only command that allows you to reset their \n"
				+ "playerdata if needed. So the player can receive the gear again on next login \n"
				+ "Note: This fearure registers a \"true/false\" value in the players data file. When they receive \n"
				+ "the gear the value is set to true so the mod knows to not give that player gear again the next \n"
				+ "time they login. If for any reason the player needs to have gear again on next login. \n"
				+ "Use the command this feature adds to reset their data value to \"false\"",
				stability = STABLE
		)
public class OxygenSpawnGear extends Feature implements IOrdered {

	public OxygenSpawnGear() {
		super(OxygenSpawnGear::new, EnumSide.COMMON);
	}

	@Override
	public String category() {
		return "SpawnItems";
	}

	@Override
	public String comment() {
		return "Allows Players to Spawn With Oxygen Items Equipped";
	}

	@Override
	public void addProp() {
		this.propOrder.add(FeatureConfigs.tanksValue.key());
		this.propOrder.add(FeatureConfigs.thermalArmor.key());
		this.propOrder.add(FeatureConfigs.includeThermals.key());
		this.propOrder.add(FeatureConfigs.includeParachute.key());
		this.propOrder.add(FeatureConfigs.includeFreqModule.key());
		this.propOrder.add(FeatureConfigs.includeShieldController.key());
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void playerLogin(PlayerLoggedInEvent event) {
		boolean para = FeatureConfigs.includeParachute.get();
		boolean shield = FeatureConfigs.includeShieldController.get();
		boolean freq = FeatureConfigs.includeFreqModule.get();
		boolean therm = FeatureConfigs.includeThermals.get();

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
		String val = FeatureConfigs.tanksValue.get();
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
		String val = FeatureConfigs.thermalArmor.get();
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
		return FeatureConfigs.OXYGEN_SPAWN_GEAR;
	}

	public class CommandOxygenReset extends CommandBase {

		@Override
		public String getName() {
			return "oxygenreset";
		}

		@Override
		public List<String> getAliases() {
			return ImmutableList.of("oxyreset", "oxyr", "oreset");
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
			return 3;
		}

		@Override
		public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
			return true;
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
