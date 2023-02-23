package readonly.galactictweaks.core.gc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.core.util.list.CelestialList;
import readonly.api.config.def.ValidDimIDs;
import readonly.galactictweaks.GCTweaks;
import readonly.galactictweaks.core.utils.TextTable;
import readonly.galactictweaks.core.utils.TextTable.Column;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModuleConfig;

public class IOWriter
{
    private final List<Planet> planets = new ArrayList<>();
    private final List<Moon> moons = new ArrayList<>();
    
    private final Column name = new TextTable.Column("Name");
    private final Column dimid = new TextTable.Column("DimID");
    private final Column owner = new TextTable.Column("Owner");
    
    private TextTable table = new TextTable(name, dimid, owner);
    private PrintWriter writer;
    
    public IOWriter()
    {
        final CelestialList<CelestialBody> allReachable = CelestialList.create();
        
        GalaxyRegistry.getAllReachableBodies().forEach(c ->{
            if(c instanceof Planet) {
                planets.add((Planet) c);
            }
            if(c instanceof Moon) {
                moons.add((Moon) c);
            }
            allReachable.add(c);
        });

        GalacticraftModuleConfig.validSPawnDims = new ValidDimIDs(allReachable);
    }

    public void handleFile(String name)
    {
        File dims = new File(GCTweaks.modFolder, name);
        try {
            if (dims.exists()) {
                dims.delete();
            }
            dims.createNewFile();
            writer = new PrintWriter(new FileWriter(dims));
        } catch (IOException localIOException) {
        }
    }

    public void write(String string)
    {
        writer.println(string);
    }

    public void nl()
    {
        writer.println("");
    }

    public void spacer()
    {
        writer.println("|--------------------------------");
    }

    public void NOTICE()
    {
        writer.println("### NOTICE ###");
    }

    public void title(String title)
    {
        nl();
        nl();
        writer.println("| ### " + title + " ###");
        writer.println("|");
    }

    public void writePlanets()
    {
        Collections.reverse(planets);
        table.clear();
        for(Planet planet : planets)
        {
            table.add(planet.getName(), planet.getDimensionID(), planet.getOwnerId());
        }
        table.add("                ", "       ", "                        ");
        writer.println(table.build());
    }

    public void writeMoons()
    {
        Collections.reverse(moons);
        table.clear();
        for(Moon moon : moons)
        {
            table.add(moon.getName(), moon.getDimensionID(), moon.getOwnerId());
        }
        table.add("                ", "       ", "                        ");
        writer.println(table.build());
    }

    public void flushAndClose()
    {
        writer.flush();
        writer.close();
    }
}
