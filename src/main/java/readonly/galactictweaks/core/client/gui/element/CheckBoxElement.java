package readonly.galactictweaks.core.client.gui.element;

import net.minecraft.client.*;
import net.minecraftforge.fml.client.config.*;

@SuppressWarnings("unused")
public class CheckBoxElement extends GuiElement {
	private Runnable	callback;
	private int			boxWidth;
	private String		displayString;
	private boolean		isChecked;

	/**
	 * Creates an new CheckBoxElement
	 *
	 * @param xPos          X position
	 * @param yPos          Y position
	 * @param displayString Text to display next to the box
	 * @param isChecked     Default state
	 */
	public CheckBoxElement(int xPos, int yPos, String displayString, boolean isChecked) {
		super(xPos, yPos, 0, 0);
		this.displayString = displayString;
		this.isChecked = isChecked;
		this.boxWidth = 11;
		this.height = 11;
		this.width = this.boxWidth + 2 + Minecraft.getMinecraft().fontRenderer.getStringWidth(displayString);
	}

	/**
	 * Creates an new CheckBoxElement
	 *
	 * @param xPos          X position
	 * @param yPos          Y position
	 * @param displayString Text to display next to the box
	 */
	public CheckBoxElement(int xPos, int yPos, String displayString) {
		this(xPos, yPos, displayString, false);
	}

	/**
	 * Creates an new textless CheckBoxElement
	 * 
	 * @param xPos X position
	 * @param yPos Y position
	 */
	public CheckBoxElement(int xPos, int yPos) {
		this(xPos, yPos, "", false);
	}

	/**
	 * Creates an new CheckBoxElement
	 * 
	 * @param xPos   X position
	 * @param yPos   Y position
	 * @param height Height of the CheckBoxElement
	 * @param width  Width of the CheckBoxElement
	 */
	public CheckBoxElement(int xPos, int yPos, int width, int height) {
		this(xPos, yPos, "", false);
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(int mouseX, int mouseY, float partial) {
		if (this.visible) {
			this.hovered = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.boxWidth && mouseY < this.getY() + this.height;
			GuiUtils.drawContinuousTexturedBox(BUTTON_TEXTURES, this.getX(), this.getY(), 0, 46, this.boxWidth, this.height, 200, 20, 2, 3, 2, 2, this.zLevel);
			// this.mouseDragged(mc, mouseX, mouseY);
			int color = 14737632;

			if (packedFGColour != 0) {
				color = packedFGColour;
			} else if (!this.enabled) {
				color = 10526880;
			}

			if (this.isChecked) {
				this.drawCenteredString(mc.fontRenderer, "x", this.getX() + this.boxWidth / 2 + 1, this.getY() + 1, 14737632);
			}

			this.drawString(mc.fontRenderer, displayString, this.getX() + this.boxWidth + 2, this.getY() + 2, color);
		}
	}

	/**
	 * Checks if the CheckBoxElement was checked
	 * 
	 * @return checked?
	 */
	public boolean isChecked() {
		return this.isChecked;
	}

	/**
	 * Checks or unchecks the CheckBoxElement
	 * 
	 * @param isChecked state
	 */
	public void setIsChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	/**
	 * Gets called on check/uncheck
	 */
	public void onChange() {
		if (this.callback != null) {
			this.callback.run();
		}
	}

	/**
	 * Adds an change listener to the CheckBoxElement
	 * 
	 * @param r listener
	 */
	public final void setChangeListener(Runnable r) {
		this.callback = r;
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButton) {
		if (mousePressed(Minecraft.getMinecraft(), mouseX, mouseY)) {
			onChange();
			playPressSound();
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {

	}

	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible && mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height) {
			this.setIsChecked(!isChecked());
			return true;
		}
		return false;
	}

	@Override
	public void keyTyped(char typedChar, int keyCode) {

	}

	@Override
	public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {

	}
}
