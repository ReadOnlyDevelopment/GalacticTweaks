///**
// * Copyright (C) 2020 Interstellar:  Exoplanets
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//
//package net.romvoid95.galactic.feature.client.compat;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.Arrays;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.lwjgl.opengl.GL11;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.Gui;
//import net.minecraft.client.gui.GuiButton;
//import net.minecraft.client.gui.GuiConfirmOpenLink;
//import net.minecraft.client.gui.GuiMainMenu;
//import net.minecraft.client.gui.GuiScreen;
//import net.minecraft.client.renderer.BufferBuilder;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
//import net.minecraft.client.resources.I18n;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import net.romvoid95.ExoInfo;
//import net.romvoid95.ExoplanetsMod;
//import net.romvoid95.client.Assets;
//import net.romvoid95.client.RGB;
//import net.romvoid95.client.gui.screen.components.GuiDiscordButton;
//import net.romvoid95.common.utility.java.ReflectionHelper;
//
//@SideOnly(Side.CLIENT)
//public class StartupUncompatibleWarning extends GuiScreen {
//
//	private static final Logger log = LogManager.getLogger();
//	private final GuiMainMenu   guiMainMenu;
//
//	public StartupUncompatibleWarning(GuiMainMenu guiMainMenu) {
//		this.guiMainMenu = guiMainMenu;
//	}
//
//	@Override
//	public void initGui () {
//		super.initGui();
//		this.buttonList.clear();
//		GuiButton        toMenu  = new GuiButton(0, this.width / 2 + 160, this.height / 4 + 180, I18n
//				.format("gui.done"));
//		toMenu.setWidth(50);
//
//		this.buttonList.addAll(Arrays.asList(toMenu));
//	}
//
//	@Override
//	public void drawScreen (int mouseX, int mouseY, float partialTicks) {
//
//		mc.renderEngine.bindTexture(Assets.getTexture("GuiBetaBackground"));
//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//		Tessellator   tessellator  = Tessellator.getInstance();
//		BufferBuilder vertexbuffer = tessellator.getBuffer();
//		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
//		vertexbuffer.pos(0 + 0, 0 + height, zLevel).tex(0, 1).endVertex();
//		vertexbuffer.pos(0 + width, 0 + height, zLevel).tex(1, 1).endVertex();
//		vertexbuffer.pos(0 + width, 0 + 0, zLevel).tex(1, 0).endVertex();
//		vertexbuffer.pos(0 + 0, 0 + 0, zLevel).tex(0, 0).endVertex();
//		tessellator.draw();
//
//		int x = this.width / 2;
//		int y = this.height / 4;
//		Gui.drawRect(x - 250, y - 20, x + 260, y + 220, new RGB(68, 68, 68, 200).getColorWithA());
//
//		for (int i = 0; i < 6; i++) {
//			String s = I18n.format("information." + ExoInfo.MODID + ":beta." + (i + 1));
//			this.drawCenteredString(this.fontRenderer, s, x, y, 0xFFFFFF);
//			y += 12;
//		}
//		this.drawCenteredString(this.fontRenderer, "Running Version: " + ExoInfo.FULL_VERSION, x, y, 0xFFFFFF);
//
//		if (ExoplanetsMod.isDevBuild) {
//			for (int i = 0; i < 3; i++) {
//				int    yy = y + 24;
//				String s  = I18n.format("information.dev." + ExoInfo.MODID + ":beta." + (i + 1));
//				this.drawCenteredString(this.fontRenderer, s, x, yy, 0xFFFFFF);
//				y += 12;
//			}
//		}
//
//		super.drawScreen(mouseX, mouseY, partialTicks);
//	}
//
//	@Override
//	protected void actionPerformed (GuiButton button) {
//
//		switch (button.id) {
//		case 0:
//			Minecraft.getMinecraft().displayGuiScreen(this.guiMainMenu);
//			break;
//		case 1:
//			try {
//				String discordUrl = "https://discord.gg/fscJ2gG";
//				ReflectionHelper.findField(GuiScreen.class, "clickedLinkURI", "field_175286_t")
//						.set(this, new URI(discordUrl));
//				mc.displayGuiScreen(new GuiConfirmOpenLink(this, discordUrl, 31102009, false));
//			}
//			catch (IllegalArgumentException | IllegalAccessException | URISyntaxException e) {
//				log.error("Exception when discord link menu button clicked:", e);
//				button.displayString = I18n.format("exoplanets.gui.failed");
//				button.enabled       = false;
//				e.printStackTrace();
//			}
//		default:
//			break;
//
//		}
//	}
//}
