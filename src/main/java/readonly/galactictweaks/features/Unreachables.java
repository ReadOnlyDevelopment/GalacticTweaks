package readonly.galactictweaks.features;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import readonly.api.feature.Feature;
import readonly.galactictweaks.core.GCTLog;
import readonly.galactictweaks.core.client.gui.ALonelyCelestialScreen;
import readonly.galactictweaks.core.gc.CelestialReference;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModuleConfig;

public class Unreachables extends Feature
{

    private final String AS_SCREEN = "asmodeuscore.core.astronomy.gui.screen.NewGuiCelestialSelection";

    private final String EP_SCREEN = "com.mjr.extraplanets.client.gui.screen.CustomCelestialSelection";

    private final String PP_SCREEN = "com.mjr.planetprogression.client.gui.screen.CustomGuiCelestialSelection";

    public Unreachables()
    {
        this.category = "Unreachables";
        this.categoryComment = "Plants or moons defined here by DImensionID or name will become unreachable to players";
    }

    @Override
    public boolean usesEvents()
    {
        return true;
    }

    @Override
    public void postInit()
    {
        int changedCount = 0;
        List<String> data = GalacticraftModuleConfig.unreachables.getAsList();
        if (data.size() > 0) {
            List<CelestialReference> references = new ArrayList<>();
            data.forEach(d -> references.add(CelestialReference.of(d)));

            for (CelestialReference r : references) {
                if (r.getBody().isPresent()) {
                    CelestialBody body = r.getBody().get();
                    body.setUnreachable();

                    if (body.isReachable()) {
                        GCTLog.error("Error setting CelestialBody [" + body.getName() + "] as unreachable");
                    } else {
                        GCTLog.info("Successfully set CelestialBody [" + body.getName() + "] as unreachable");
                        changedCount++;
                    }
                } else {
                    GCTLog.error("Cannot find any Planets/Moons with Identifier " + r.getIdentifier()
                        + " provided in the configs 'idList'");
                }
            }
        }

        if (changedCount > 0) {
            GalaxyRegistry.refreshGalaxies();
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SideOnly(Side.CLIENT)
    public void onGuiOpenEvent(GuiOpenEvent event)
    {
        GuiScreen screen = event.getGui();
        if (((event.getGui() instanceof GuiCelestialSelection))) {
            if (!guiScreenEquals(screen, AS_SCREEN)) {
                if (!guiScreenEquals(screen, EP_SCREEN)) {
                    if (!guiScreenEquals(screen, PP_SCREEN)) {
                        if (GameSettings.isKeyDown(micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient.galaxyMap)) {
                            event.setGui(new ALonelyCelestialScreen(true,
                                ((GuiCelestialSelection) event.getGui()).possibleBodies,
                                ((GuiCelestialSelection) event.getGui()).canCreateStations));
                        } else {
                            event.setGui(new ALonelyCelestialScreen(false,
                                ((GuiCelestialSelection) event.getGui()).possibleBodies,
                                ((GuiCelestialSelection) event.getGui()).canCreateStations));
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private boolean guiScreenEquals(GuiScreen gui, String checkfor)
    {
        return gui.getClass().getName().equalsIgnoreCase(checkfor);
    }

    @Override
    public boolean isEnabled()
    {
        return GalacticraftModuleConfig.MAKE_UNREACHABLE;
    }

}
