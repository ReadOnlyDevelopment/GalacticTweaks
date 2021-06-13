package net.romvoid95.galactic.core.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Instantiates a new item stack util.
 */
@UtilityClass
public class ItemStackUtil {
	
	/**
	 * Get ItemStack (1 item) from a block.
	 *
	 * @param block the block
	 * @return the item stack
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
	 * @param block the block
	 * @param meta  the meta
	 * @return the item stack
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
	 * @param amount the amount
	 * @param block  the block
	 * @return the item stack
	 */
	public static ItemStack blockStack(int amount, Block block) {
		return new ItemStack(block);
	}
	
	/**
	 * Get ItemStack quantity from a blocks meta
	 * 
	 * <code>
	 * ItemStack gcBlockTin = ItemStackUtil.blockStack(32, GCBlocks.basicblock, 4);
	 * </code>
	 *
	 * @param amount the amount
	 * @param block  the block
	 * @param meta   the meta
	 * @return the item stack
	 */
	public static ItemStack blockStack(int amount, Block block, int meta) {
		return new ItemStack(block);
	}
	
	/**
	 * Get ItemStack (1 item) from an Item.
	 *
	 * @param item the item
	 * @return the item stack
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
	 * @param item the item
	 * @param meta the meta
	 * @return the item stack
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
	 * @param amount the amount
	 * @param item   the item
	 * @return the item stack
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
	 * @param amount the amount
	 * @param item   the item
	 * @param meta   the meta
	 * @return the item stack
	 */
	public static ItemStack itemStack(int amount, Item item, int meta) {
		return new ItemStack(item);
	}

}
