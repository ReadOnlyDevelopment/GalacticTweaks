package readonly.galactictweaks.core.world.struct;

import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.gen.structure.template.PlacementSettings;

public interface IStructure {
	
	public static final PlacementSettings noRotation = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock(false).setMirror(Mirror.NONE).setRotation(Rotation.NONE);
	public static final PlacementSettings clockwise_90 = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock (false).setMirror(Mirror.NONE).setRotation(Rotation.CLOCKWISE_90);
	public static final PlacementSettings clockwise_180 = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock (false).setMirror(Mirror.NONE).setRotation(Rotation.CLOCKWISE_180);
	public static final PlacementSettings counterClockwise_90 = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock (false).setMirror(Mirror.NONE).setRotation(Rotation.COUNTERCLOCKWISE_90);
}
