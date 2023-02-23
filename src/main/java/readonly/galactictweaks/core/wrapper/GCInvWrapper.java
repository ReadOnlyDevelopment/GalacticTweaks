package readonly.galactictweaks.core.wrapper;

import java.util.Arrays;
import java.util.List;

import micdoodle8.mods.galacticraft.core.entities.player.GCCapabilities;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.inventory.InventoryExtended;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GCInvWrapper {
	
	private final static InventoryExtended inventory = new InventoryExtended();
	
	@SuppressWarnings("unused")
	private final GCPlayerStats gcstats;
	
	public GCInvWrapper(EntityPlayer player) {
		this.gcstats = player.getCapability(GCCapabilities.GC_STATS_CAPABILITY, null);
	}
	
	public static enum SlotThermal {
		HEAD(6),
		CHEST(7),
		PANTS(8),
		BOOTS(9);
		
		private int idx; 

		SlotThermal(int slotNum) {
			this.idx = slotNum;
		}
		
		public Integer idx() {
			return idx;
		}
	}
	
	public static enum SlotUse {
		MASK(Arrays.asList(new Integer[]{0})),
		TUBE(Arrays.asList(new Integer[]{1})),
		TANK(Arrays.asList(new Integer[]{2,3})),
		PARA(Arrays.asList(new Integer[]{4})),
		SHIELD(Arrays.asList(new Integer[]{10})),
		FREQUENCY(Arrays.asList(new Integer[]{5}));
		
		private List<Integer> idx;

		SlotUse(List<Integer> idx) {
			this.idx = idx;
		}
		
		public Integer idx() {
			if(idx.size() == 2) {
				if(inventory.getStackInSlot(2).equals(ItemStack.EMPTY)) {
					return 2;
				} else {
					return 3;
				}
			} else {
				return idx.get(0);
			}
		}
	}
}
