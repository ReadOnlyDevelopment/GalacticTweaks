package net.romvoid95.gctweaks.internal.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.math.MathHelper;


@Mixin(Entity.class)
public class MixinEntity {
	
	@Shadow private Entity entity;

    public void addEntityCrashInfo(CrashReportCategory category)
    {
        category.addDetail("Entity Type", () -> EntityList.getKey(this.entity) + " (" + entity.getClass().getCanonicalName() + ")");
        category.addCrashSection("Entity ID", Integer.valueOf(entity.getEntityId()));
        category.addDetail("Entity Name", entity::getName);
        category.addCrashSection("Entity's Exact location", String.format("%.2f, %.2f, %.2f", 0, 0, 0));
        category.addCrashSection("Entity's Block location", CrashReportCategory.getCoordinateInfo(MathHelper.floor(0), MathHelper.floor(0), MathHelper.floor(0)));
        category.addCrashSection("Entity's Momentum", String.format("%.2f, %.2f, %.2f", 0, 0, 0));
        category.addDetail("Entity's Passengers", () -> entity.getPassengers().toString());
        category.addDetail("Entity's Vehicle", () -> entity.getRidingEntity().toString());
    }
	
}
