package net.romvoid95.gctweaks.base.core.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStackUtil {
	
	/**
	 * Get ItemStack (1 item) from a block
	 * 
	 * @param block
	 * @return
	 */
	public static ItemStack blockStack(Block block) {
		return new ItemStack(block);
	}
	/**
	 * Get ItemStack (1 item) from a blocks meta
	 * 
	 * Example: galacticraftcore:basic_block_core:4
	 * 
	 * <code>
	 * ItemStack gcBlockTin = ItemStackUtil.blockStack(GCBlocks.basicblock, 4);
	 * </code>
	 * 
	 * @param block
	 * @return
	 */
	public static ItemStack blockStack(Block block, int meta) {
		return new ItemStack(block);
	}
	/**
	 * Get ItemStack quantity from a block
	 * 
	 * <code>
	 * ItemStack diamondBlock = ItemStackUtil.blockStack(32, Blocks.DIAMOND_BLOCK);
	 * </code>
	 * 
	 * @param block
	 * @return
	 */
	public static ItemStack blockStack( int amount, Block block) {
		return new ItemStack(block);
	}
	/**
	 * Get ItemStack quantity from a blocks meta
	 * 
	 * <code>
	 * ItemStack gcBlockTin = ItemStackUtil.blockStack(32, GCBlocks.basicblock, 4);
	 * </code>
	 * 
	 * @param block
	 * @return
	 */
	public static ItemStack blockStack(int amount, Block block, int meta) {
		return new ItemStack(block);
	}
	
	/**
	 * Get ItemStack (1 item) from an Item
	 * 
	 * @param block
	 * @return
	 */
	public static ItemStack itemStack(Item item) {
		return new ItemStack(item);
	}
	/**
	 * Get ItemStack (1 item) from a Items meta
	 * 
	 * Example: galacticraftcore:basic_item:4
	 * 
	 * <code>
	 * ItemStack gcCopperIngot = ItemStackUtil.blockStack(GCBlocks.basicItem, 4);
	 * </code>
	 * 
	 * @param block
	 * @return
	 */
	public static ItemStack itemStack(Item item, int meta) {
		return new ItemStack(item);
	}
	/**
	 * Get ItemStack quantity from a Item
	 * 
	 * <code>
	 * ItemStack diamondBlock = ItemStackUtil.blockStack(32, Blocks.DIAMOND_BLOCK);
	 * </code>
	 * 
	 * @param block
	 * @return
	 */
	public static ItemStack itemStack( int amount, Item item) {
		return new ItemStack(item);
	}
	/**
	 * Get ItemStack quantity from a Items meta
	 * 
	 * <code>
	 * ItemStack gcCopperIngot = ItemStackUtil.blockStack(32, GCItems.basicItem, 4);
	 * </code>
	 * 
	 * @param block
	 * @return
	 */
	public static ItemStack itemStack(int amount, Item item, int meta) {
		return new ItemStack(item);
	}

}
