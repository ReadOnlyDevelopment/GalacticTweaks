package readonly.galactictweaks.core.client.gui.element;

import java.util.*;

import com.google.common.collect.*;

import net.minecraft.client.renderer.*;
import net.minecraft.client.resources.*;

@SuppressWarnings("unused")
public class LabelElement extends GuiElement {
	private final List<String>	labels;
	private final int			textColor;
	private boolean				centered;
	private int					backColor;
	private int					ulColor;
	private int					brColor;
	private int					border;

	/**
	 * Creates a new Label
	 *
	 * @param x     X position
	 * @param y     Y position
	 * @param color Custom text color
	 */
	public LabelElement(int x, int y, int color) {
		super(x, y, 0, 0);
		this.labels = Lists.newArrayList();
		this.textColor = color;
		this.setX(x);
		this.setY(y);
		this.visible = true;
	}

	/**
	 * Creates a new Label
	 *
	 * @param x X position
	 * @param y Y position
	 */
	public LabelElement(int x, int y) {
		this(x, y, -1);
	}

	/**
	 * Creates a new Label
	 * 
	 * @param x    X position
	 * @param y    Y position
	 * @param text Label text (1. line)
	 */
	public LabelElement(String text, int x, int y) {
		this(x, y);
		this.addLine(text);
	}

	@Override
	public void draw(int mouseX, int mouseY, float partial) {
		if (this.visible) {
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			int i = this.getY() + this.height / 2 + this.border / 2;
			int j = i - this.labels.size() * 10 / 2;

			for (int k = 0; k < this.labels.size(); ++k) {
				if (this.centered) {
					this.drawCenteredString(this.fontRenderer, this.labels.get(k), this.getX(), j + k * 10, this.textColor);
				} else {
					this.drawString(this.fontRenderer, this.labels.get(k), this.getX(), j + k * 10, this.textColor);
				}
			}
		}
	}

	/**
	 * Adds an line of text
	 * 
	 * @param p_175202_1_ Text
	 */
	public void addLine(String translateKey) {
		this.labels.add(I18n.format(translateKey));
	}

	/**
	 * Sets the label text Use \n for an new line
	 * 
	 * @param text Text
	 */
	public void setText(String text) {
		this.labels.clear();
		labels.addAll(Arrays.asList(text.split("\n")));
	}

	/**
	 * Sets the Label to be centered
	 */
	public void setCentered() {
		this.centered = true;
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButton) {

	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {

	}

	@Override
	public void keyTyped(char typedChar, int keyCode) {

	}

	@Override
	public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {

	}
}
