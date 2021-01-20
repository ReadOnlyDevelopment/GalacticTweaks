package net.romvoid95.gctweaks.internal.mixins;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.world.World;

@Mixin(World.class)
public class MixinWorld {

    public CrashReportCategory addWorldInfoToCrashReport(CrashReport report)
    {
        CrashReportCategory crashreportcategory3 = report.makeCategoryDepth("Affected level", 1);
        crashreportcategory3.addDetail("Player Detail", () -> "[REDACTED]");
        return crashreportcategory3;
    }
}
