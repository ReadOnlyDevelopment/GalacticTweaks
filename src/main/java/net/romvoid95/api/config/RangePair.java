package net.romvoid95.api.config;

public class RangePair<V> {

	private V min;
	private V max;

	private RangePair(V min, V max) {
		super();
		this.min = min;
		this.max = max;
	}
	
	public static <T> RangePair<T> of(T min, T max) {
		return new RangePair<>(min, max);
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
