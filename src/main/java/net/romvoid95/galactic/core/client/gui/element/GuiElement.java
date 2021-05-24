package net.romvoid95.galactic.core.client.gui.element;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;

import javax.imageio.*;

import net.minecraft.client.*;
import net.minecraft.client.audio.*;
import net.minecraft.client.gui.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.romvoid95.galactic.*;

public abstract class GuiElement extends Gui {
	protected static final ResourceLocation	BUTTON_TEXTURES	= new ResourceLocation("textures/gui/widgets.png");
	protected final FontRenderer			fontRenderer;
	public int								packedFGColour;
	protected Minecraft						mc				= Minecraft.getMinecraft();
	protected int							width;
	protected int							height;
	protected int							id;
	protected boolean						hovered;
	protected boolean						visible			= true, enabled = true;
	protected static final ResourceLocation	errorIcon		= new ResourceLocation(Info.ID, "textures/gui/imgerror.png");
	private int								x;
	private int								y;
	private String[]						tooltips		= new String[0];
	private int								assignedPage	= -1;

	protected GuiElement(int x, int y, int width, int height) {
		this.fontRenderer = mc.fontRenderer;
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
	}

	/**
	 * This function resizes the image and returns the BufferedImage object that can be drawn
	 */
	final BufferedImage scaleImage(final BufferedImage img, int width, int height) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(width, height, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, width, height, 0, 0, w, h, null);
		g.dispose();
		return dimg;
	}

	final BufferedImage loadImageFromURL(String url) throws IOException {
		final HttpURLConnection httpcon = (HttpURLConnection) new URL(url).openConnection();
		httpcon.addRequestProperty("User-Agent", "Minecraft");
		final BufferedImage img = ImageIO.read(httpcon.getInputStream());
		httpcon.disconnect();
		return img;
	}

	/**
	 * @return can the element have a tooltip?
	 */
	public boolean canHaveTooltip() {
		return true;
	}

	/**
	 * Gets tooltips assigned to the element
	 *
	 * @return tooltips
	 */
	public String[] getTooltips() {
		return this.tooltips;
	}

	/**
	 * Sets the tooltip(s) of this element
	 *
	 * @param strings tooltip(s)
	 */
	public final void setTooltips(String... strings) {
		this.tooltips = strings;
	}

	/**
	 * @return Is this element visible?
	 */
	public final boolean isVisible() {
		return this.visible;
	}

	/**
	 * Sets the elements visibility state
	 * 
	 * @param visible visible?
	 */
	public final void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return is element enabled?
	 */
	public final boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * @param enable should it be enabled?
	 */
	public final void setEnabled(boolean enable) {
		this.enabled = enable;
	}

	/**
	 * @return X position
	 */
	public final int getX() {
		return x;
	}

	/**
	 * Sets the element´s X position
	 * 
	 * @param x X position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return Y position
	 */
	public final int getY() {
		return y;
	}

	/**
	 * Sets the element´s Y position
	 * 
	 * @param y Y position
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the element width
	 * 
	 * @return width
	 */
	public final int getWidth() {
		return width;
	}

	/**
	 * Sets the element width
	 * 
	 * @param width Width
	 */
	public final void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the element height
	 * 
	 * @return height
	 */
	public final int getHeight() {
		return height;
	}

	/**
	 * Sets the element height
	 * 
	 * @param height Height
	 */
	public final void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Plays a press sound
	 */
	public void playPressSound() {
		mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, this.enabled ? 1.0F : 0.5f));
	}

	/**
	 * Draws the element
	 */
	public abstract void draw(int mouseX, int mouseY, float partial);

	/**
	 * Called on mouse click
	 */
	public abstract void mouseClick(int mouseX, int mouseY, int mouseButton);

	/**
	 * Called on mouse release
	 */
	public abstract void mouseReleased(int mouseX, int mouseY, int state);

	/**
	 * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
	 */
	public abstract void keyTyped(char typedChar, int keyCode);

	/**
	 * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY, lastButtonClicked & timeSinceMouseClick.
	 */
	public abstract void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick);

	/**
	 * Updates the element on gui update
	 */
	public void updateElement() {

	}

	public void handleMouseInput() {

	}

	public void handleKeyboardInput() {

	}

	/**
	 * Disables this element
	 */
	public final void disable() {
		setEnabled(false);
	}

	/**
	 * Enables this element
	 */
	public final void enable() {
		setEnabled(true);
	}

	/**
	 * Hides this element
	 */
	public final void hide() {
		setVisible(false);
	}

	/**
	 * Shows this element
	 */
	public final void show() {
		setVisible(true);
	}

	/**
	 * Assigns this element to an page -1 means global Default: -1
	 * 
	 * @param page Page to assign to
	 */
	public final void assignToPage(int page) {
		assignedPage = page;
	}

	/**
	 * Gets the page this element is assigned to
	 * 
	 * @return Assigned page
	 */
	public final int getAssignedPage() {
		return assignedPage;
	}

	/**
	 * Sets the ID of this element (almost unused!)
	 * 
	 * @param id ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the position of this element
	 * 
	 * @param x X position
	 * @param y Y position
	 */
	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}
}
