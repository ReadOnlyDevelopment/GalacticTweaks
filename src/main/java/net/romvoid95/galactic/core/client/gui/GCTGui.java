package net.romvoid95.galactic.core.client.gui;

import java.io.*;
import java.util.*;

import com.google.common.collect.*;

import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraftforge.fml.relauncher.*;
import net.romvoid95.galactic.core.client.gui.element.*;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public abstract class GCTGui extends GuiScreen {
	private final List<GuiElement> declaredElements = new ArrayList<>();
	private int nextComponentID = 0;
	private int pages = 0;
	private int currentPage = 0;

	public GCTGui() {
		buildGui();
	}

	@Override
	public final void initGui() {
	}

	/**
	 * Gets current open page
	 *
	 * @return current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Add your elements here!
	 */
	public abstract void buildGui();

	@Override
	public final void updateScreen() {
		updateGui();
		for (GuiElement comp : declaredElements) {
			comp.updateElement();
		}

	}

	/**
	 * Gets called often to e.g. update elements postions
	 */
	public abstract void updateGui();

	@Override
	public abstract boolean doesGuiPauseGame();

	/**
	 * Should pressing ESC close the GUI?
	 */
	public abstract boolean doesEscCloseGui();

	/**
	 * Open the next page of the GUI (if available)
	 */
	public void nextPage() {
		if (currentPage < pages) {
			currentPage++;
		}
	}

	/**
	 * Open the previous page of the GUI (if available)
	 */
	public void prevPage() {
		if (currentPage > 0) {
			currentPage--;
		}
	}

	/**
	 * Sets the gui to a specific Page (if it is available!)
	 *
	 * @param page Page to set the GUI to
	 */
	public void setPage(int page) {
		if (page < pages) {
			this.currentPage = page;
		}
	}

	/**
	 * Use this to add your elements
	 *
	 * @param element The element to add
	 */
	public final void addComponent(GuiElement element) {
		this.declaredElements.add(element);
	}

	/**
	 * Use this to add multiple elements at once
	 *
	 * @param elements The elements to add
	 */
	public final void addAllComponents(GuiElement... elements) {
		for (GuiElement c : elements) {
			this.addComponent(c);
		}
	}

	/**
	 * Sets the amount of pages this GUI has
	 * @param pages Amount of pages
	 */
	public void setAmountOfPages(int pages) {
		this.pages = pages;
	}

	/**
	 * Assigns a element to the page by calling comp.assignToPage(page)
	 * @param comp The element to assign
	 * @param page The page for the element
	 */
	public void assignComponentToPage(GuiElement comp, int page) {
		comp.assignToPage(page);
	}

	@Override
	@Deprecated
	protected final <T extends GuiButton> T addButton(T buttonIn) {
		throw new UnsupportedOperationException();
	}

	@Override
	@Deprecated
	protected final void actionPerformed(GuiButton button) {
	}

	@Override
	public final void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawBackground();
		for (final GuiElement comp : declaredElements) {
			if (comp.getAssignedPage() != -1) {
				if (comp.getAssignedPage() != currentPage) {
					continue;
				}
			}
			comp.draw(mouseX, mouseY, partialTicks);
		}
		//Second for to not have elements overlap the tooltips
		for (final GuiElement comp : Lists.reverse(declaredElements)) { //Reversing to call front element
			if (comp.getAssignedPage() != -1) {
				if (comp.getAssignedPage() != currentPage) {
					continue;
				}
			}
			if (comp.canHaveTooltip() && isHovered(comp, mouseX, mouseY) && comp.isVisible()) {

				final ArrayList<String> list = new ArrayList<>();
				if (comp.getTooltips() != null) {
					list.addAll(Arrays.asList(comp.getTooltips()));
				}
				if (!list.isEmpty()) {
					drawHoveringText(list, mouseX, mouseY);
					break;
				}
			}
		}


	}

	/**
	 * Override to draw a custom background
	 */
	protected void drawBackground() {
		if (this.mc.world != null) {
			this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
		} else {
			GlStateManager.disableLighting();
			GlStateManager.disableFog();
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBuffer();
			this.mc.getTextureManager().bindTexture(OPTIONS_BACKGROUND);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
			bufferbuilder.pos(0.0D, this.height, 0.0D).tex(0.0D, this.height / 32.0F + 0).color(64, 64, 64, 255).endVertex();
			bufferbuilder.pos(this.width, this.height, 0.0D).tex(this.width / 32.0F, this.height / 32.0F + 0).color(64, 64, 64, 255).endVertex();
			bufferbuilder.pos(this.width, 0.0D, 0.0D).tex(this.width / 32.0F, 0).color(64, 64, 64, 255).endVertex();
			bufferbuilder.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 0).color(64, 64, 64, 255).endVertex();
			tessellator.draw();
		}
	}

	private boolean isHovered(GuiElement comp, int mouseX, int mouseY) {
		if (comp.getAssignedPage() != -1) {
			if (comp.getAssignedPage() != currentPage) {
				return false;
			}
		}
		final int x = comp.getX();
		final int y = comp.getY();
		final int w = comp.getWidth();
		final int h = comp.getHeight();
		return (mouseX >= x && mouseY >= y && mouseX < x + w && mouseY < y + h);
	}

	public final GuiElement getComponent(int index) {
		return declaredElements.get(index);
	}

	/**
	 * Opens an GUI
	 * @param gui GuiScreen or null
	 */
	public final void openGui(GuiScreen gui) {
		if (gui == null) {
			mc.displayGuiScreen(null);
		} else {
			mc.displayGuiScreen(gui);
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton)  {

		for (GuiElement comp : declaredElements) {
			if (comp.getAssignedPage() != -1) {
				if (comp.getAssignedPage() != currentPage) {
					continue;
				}
			}
			comp.mouseClick(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {

		for (GuiElement comp : declaredElements) {
			if (comp.getAssignedPage() != -1) {
				if (comp.getAssignedPage() != currentPage) {
					continue;
				}
			}
			comp.mouseReleased(mouseX, mouseY, state);
		}
	}

	@Override
	public void keyTyped(char typedChar, int keyCode) {
		if (keyCode == 1 && doesEscCloseGui()) {
			//noinspection RedundantCast
			this.mc.displayGuiScreen((GuiScreen) null);

			if (this.mc.currentScreen == null) {
				this.mc.setIngameFocus();
			}
		}
		for (GuiElement comp : declaredElements) {
			if (comp.getAssignedPage() != -1) {
				if (comp.getAssignedPage() != currentPage) {
					continue;
				}
			}
			comp.keyTyped(typedChar, keyCode);
		}
	}

	@Override
	public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {

		for (GuiElement comp : declaredElements) {
			if (comp.getAssignedPage() != -1) {
				if (comp.getAssignedPage() != currentPage) {
					continue;
				}
			}
			comp.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		}
	}

	@Override
	public void handleMouseInput() throws IOException {
		for (GuiElement comp : declaredElements) {
			if (comp.getAssignedPage() != -1) {
				if (comp.getAssignedPage() != currentPage) {
					continue;
				}
			}
			comp.handleMouseInput();
		}
		super.handleMouseInput();
	}

	@Override
	public void handleKeyboardInput() throws IOException {
		for (GuiElement element : declaredElements) {
			if (element.getAssignedPage() != -1) {
				if (element.getAssignedPage() != currentPage) {
					continue;
				}
			}
			element.handleKeyboardInput();
		}
		super.handleKeyboardInput();
	}
}
