package net.romvoid95.galactic.core.gc;

import java.util.*;

import javax.annotation.*;

import micdoodle8.mods.galacticraft.api.recipe.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.registry.*;
import net.romvoid95.galactic.*;

public class Schematics {

	public static ArrayList<Schematics.SchematicItemWrapper> wrappers = new ArrayList<Schematics.SchematicItemWrapper>();

	public static void getAllSchematics() {
		SchematicRegistry.schematicItems.forEach(stack -> {
			SchematicItemWrapper wrapper = new SchematicItemWrapper(stack);
			Integer id = null;
			try {
				id = SchematicRegistry.getMatchingRecipeForItemStack(stack).getPageID();
			} catch (Exception e) {
				GalacticTweaks.LOG.error("Could not get PageID for ItemStack [ {} ]",
						ItemData.getFor(wrapper).toString());
			}
			if (id != null) {
				wrapper.setPageId(id);
				wrappers.add(wrapper);
			}
		});
	}

	public static void printItemInfoToConsole(@Nonnull SchematicItemWrapper wrapper) {
		List<String> lines = getFullItemInfo(wrapper);

		for (String line : lines) {
			GalacticTweaks.LOG.info(line);
		}
	}

	private static List<String> getFullItemInfo(@Nonnull SchematicItemWrapper wrapper) {
		List<String> lines = new ArrayList<>();
		lines.add(ItemData.getFor(wrapper).toString());
		return lines;
	}
	
	public static class SchematicItemWrapper {
		private ItemStack itemStack;
		@Nullable private Integer pageId;
		
		public SchematicItemWrapper() {}
		
		public SchematicItemWrapper(ItemStack itemStack) {
			super();
			this.itemStack = itemStack;
		}
		
		public SchematicItemWrapper(ItemStack itemStack, @Nullable Integer pageId) {
			super();
			this.itemStack = itemStack;
			this.pageId = pageId;
		}
		
		public void setItemStack(ItemStack itemStack) {
			this.itemStack = itemStack;
		}
		
		public ItemStack getItemStack() {
			return itemStack;
		}
		
		public void setPageId(Integer pageId) {
			this.pageId = pageId;
		}
		
		public Integer getPageId() {
			return pageId;
		}
	}

	public static class ItemData {
		private final String regName;
		private final int id;
		private final int meta;
		private final String displayName;
		private final String pageId;

		public ItemData(String displayName, String regName, int id, int meta, String pageId) {
			this.displayName = displayName;
			this.regName = regName;
			this.id = id;
			this.meta = meta;
			this.pageId = pageId;
		}

		public static ItemData getFor(SchematicItemWrapper wrapper) {
			ItemStack stack = wrapper.getItemStack();
			
			String registryName = ForgeRegistries.ITEMS.getKey(stack.getItem()).toString();
			String ID = wrapper.getPageId() == null ? "NULL" : String.valueOf(wrapper.getPageId());

			return new ItemData(stack.getDisplayName(), registryName, Item.getIdFromItem(stack.getItem()),
					stack.getMetadata(), ID);
		}

		@Override
		public String toString() {
			return String.format("%s (%s - %d:%d) %s", this.displayName, this.regName, this.id, this.meta, this.pageId);
		}
	}
}
