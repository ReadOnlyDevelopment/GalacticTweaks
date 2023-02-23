package readonly.api.world.data;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public interface ISchematic {

	IBlockState getBlockState(BlockPos pos);

	boolean setBlockState(BlockPos pos, IBlockState blockState);

	TileEntity getTileEntity(BlockPos pos);

	List<TileEntity> getTileEntities();

	void setTileEntity(BlockPos pos, TileEntity tileEntity);

	void removeTileEntity(BlockPos pos);

	List<Entity> getEntities();

	void addEntity(Entity entity);

	void removeEntity(Entity entity);

	ItemStack getIcon();

	void setIcon(ItemStack icon);

	int getWidth();

	int getLength();

	int getHeight();

	@Nonnull
	String getAuthor();

	void setAuthor(@Nonnull String author);
}
