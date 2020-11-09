package net.romvoid95.gctweaks.gc.features.spawnitems;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.player.GCCapabilities;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.inventory.InventoryExtended;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.romvoid95.gctweaks.Ref;
import net.romvoid95.gctweaks.base.Feature;
import net.romvoid95.gctweaks.gc.features.spawnitems.command.CommandOxygenReset;

public class SpawnWithOxygenEquip extends Feature {
	
	private boolean         spawnWithOxygenEquip;
	private boolean         includeParachute;
	private boolean         includeFreqModule;
	private boolean         includeShieldController;
	private boolean         includeThermals;
	private static String   tanksValue;
	private static String   thermalArmor;
	private static String[] validValues   = { "light", "medium", "heavy" };
	private static String[] validThermals = { "thermal", "isothermal" };

	@Override
	public String category () {
		return  "spawnItems" ;
	}

	@Override
	public String comment () {
		return "Allows Players to Spawn With Oxygen Items Equipped";
	}
	
	@Override
	public void syncConfig (String category) {
		
		spawnWithOxygenEquip    = set(category, "enableFeature", true);
		tanksValue              = setFormated(category, "tankProp", "light", validValues);
		thermalArmor            = setFormated(category, "thermalProp", "thermal", validThermals);
		includeThermals			= set(category, "enableThermals",  "Always FALSE if \"enableFeature\" = FALSE", false);
		includeParachute        = set(category, "enableParachute", "Always FALSE if \"enableFeature\" = FALSE", false);
		includeFreqModule       = set(category, "enableFreqModule", "Always FALSE if \"enableFeature\" = FALSE", false);
		includeShieldController = set(category, "enableShieldItem", "Always FALSE if \"enableFeature\" = FALSE", false);

		if (spawnWithOxygenEquip == false) {
			includeThermals			= false;
			includeParachute        = false;
			includeFreqModule       = false;
			includeShieldController = false;
		}

	}

	@Override
	public boolean usesEvents () {
		return true;
	}

	@SubscribeEvent
	public void playerLogin (PlayerLoggedInEvent event) {
		final EntityPlayer player = event.player;
		final NBTTagCompound entityData    = player.getEntityData();
		final NBTTagCompound persistedData = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, persistedData);
		final String key = Ref.MOD_ID + ":" + "ReceivedOxygen";

		if (!persistedData.getBoolean(key)) {
			if (spawnWithOxygenEquip) {
				GCPlayerStats     gcstats = event.player.getCapability(GCCapabilities.GC_STATS_CAPABILITY, null);
				InventoryExtended inv     = new InventoryExtended();
				inv.setInventorySlotContents(0, getStack(GCItems.oxMask));
				inv.setInventorySlotContents(1, getStack(GCItems.oxygenGear));
				inv.setInventorySlotContents(2, setTanks());
				inv.setInventorySlotContents(3, setTanks());
				if (includeParachute) {
					inv.setInventorySlotContents(4, getStack(GCItems.parachute));
				}
				if (includeShieldController) {
					inv.setInventorySlotContents(10, getStack(VenusItems.basicItem, 0));
				}
				if (includeFreqModule) {
					inv.setInventorySlotContents(5, getStack(GCItems.basicItem, 19));
				}
				if(includeThermals) {
					inv.setInventorySlotContents(6, setThermals(0));
					inv.setInventorySlotContents(7, setThermals(1));
					inv.setInventorySlotContents(8, setThermals(2));
					inv.setInventorySlotContents(9, setThermals(3));
				}
				gcstats.setExtendedInventory(inv);
				persistedData.setBoolean(key, true);
			}
		}
		else {
			// Do Nothing
		}
	}

	private static ItemStack setTanks () {
		ItemStack tank = null;
		switch (tanksValue) {
		case "heavy":
			tank = getStack(GCItems.oxTankHeavy);
			break;
		case "medium":
			tank = getStack(GCItems.oxTankMedium);
			break;
		case "light":
			tank = getStack(GCItems.oxTankLight);
		default:
			break;
		}
		return tank;
	}

	private static ItemStack setThermals (int meta) {
		ItemStack thermal = null;
		switch (thermalArmor) {
		case "isothermal":
			thermal = getStack(VenusItems.thermalPaddingTier2, meta);
			break;
		case "thermal":
			thermal = getStack(AsteroidsItems.thermalPadding, meta);
			break;
		default:
			break;
		}
		return thermal;
	}

	public static ItemStack getStack (Item item, int meta) {
		return new ItemStack(item, 1, meta);
	}

	public static ItemStack getStack (Item item) {
		return new ItemStack(item, 1);
	}

	@Override
	public void ServerStartingEvent (FMLServerStartingEvent event) {
		if (spawnWithOxygenEquip)
			event.registerServerCommand(new CommandOxygenReset());
	}

}
