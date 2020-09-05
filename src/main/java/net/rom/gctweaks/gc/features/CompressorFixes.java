/**
 * GalacticTweaks
 * Copyright (C) 2020
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.rom.gctweaks.gc.features;

import micdoodle8.mods.galacticraft.api.recipe.CompressorRecipes;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import net.rom.gctweaks.base.Feature;

public class CompressorFixes extends Feature {

	private static boolean compressorFix;

	@Override
	public String[] category() {
		return new String[] {"compressor-enhancement"};
	}

	@Override
	public String comment() {
		return "Adds Oredict ingots to compressor recipe table";
	}

	@Override
	public void syncConfig(Configuration config, String[] category) {
		compressorFix = config.get(category[0], "compressorFix", false, "Set to true if you want to register Compressor Fixes").getBoolean();
	}

	@Override
	public void init() {
		if (compressorFix) {
			ItemStack tplate   = new ItemStack(AsteroidsItems.basicItem, 1, 6);
			ItemStack cplate   = new ItemStack(GCItems.basicItem, 1, 6);
			ItemStack tinplate = new ItemStack(GCItems.basicItem, 1, 8);
			ItemStack splate   = new ItemStack(GCItems.basicItem, 1, 9);
			ItemStack bplate   = new ItemStack(GCItems.basicItem, 1, 10);
			ItemStack iplate   = new ItemStack(GCItems.basicItem, 1, 11);
			for (ItemStack t : OreDictionary.getOres("ingotTitanium")) {
				CompressorRecipes.addShapelessRecipe(tplate, t, t);
			}
			for (ItemStack c : OreDictionary.getOres("ingotCopper")) {
				CompressorRecipes.addShapelessRecipe(cplate, c, c);
			}
			for (ItemStack tin : OreDictionary.getOres("ingotTin")) {
				CompressorRecipes.addShapelessRecipe(tinplate, tin, tin);
			}
			for (ItemStack s : OreDictionary.getOres("ingotSteel")) {
				CompressorRecipes.addShapelessRecipe(splate, s, s);
			}
			for (ItemStack b : OreDictionary.getOres("ingotBronze")) {
				CompressorRecipes.addShapelessRecipe(bplate, b, b);
			}
			for (ItemStack i : OreDictionary.getOres("ingotIron")) {
				CompressorRecipes.addShapelessRecipe(iplate, i, i);
			}

		}
	}
}
