package readonly.galactictweaks.core.gc;

public class RingColor {

	public float red, green, blue;

	public RingColor() {
	}

	public RingColor(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public static class Builder {
		private float red;
		private float green;
		private float blue;

		public Builder red(float red) {
			this.red = red;
			return this;
		}

		public Builder green(float green) {
			this.green = green;
			return this;
		}

		public Builder blue(float blue) {
			this.blue = blue;
			return this;
		}

		public RingColor build() {
			RingColor ringColor = new RingColor();
			ringColor.red = red;
			ringColor.green = green;
			ringColor.blue = blue;
			return ringColor;
		}
	}
}
