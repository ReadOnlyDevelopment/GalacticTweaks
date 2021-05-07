package net.romvoid95.api.config;

public class Range<V> {

	private V min;
	private V max;

	private Range(V min, V max) {
		super();
		this.min = min;
		this.max = max;
	}
	
	public static <T> Range<T> of(T min, T max) {
		return new Range<>(min, max);
	}

	public V min() {
		return min;
	}

	public V max() {
		return max;
	}

	public String commentAddl(V defaultValue) {
		return " [range: " + min + " ~ " + max + ", default: " + defaultValue + "]";
	}
}
