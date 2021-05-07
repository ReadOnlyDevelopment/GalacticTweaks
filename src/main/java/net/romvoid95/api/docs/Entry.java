package net.romvoid95.api.docs;

import com.google.common.base.*;

public abstract class Entry<T> implements Supplier<T> {
	
	private T t;
	
	public Entry(T t) {
		this.t = t;
	}

	@Override
	public T get() {
		return t;
	}

	public static class Name extends Entry<String> {

		private Name(String value) {
			super(value);
		}

		public static Name of(String value) {
			return new Name(value);
		}
	}
	
	public static class Comment extends Entry<String> {

		private Comment(String value) {
			super(value);
		}
		
		public static Comment of(String value) {
			return new Comment(value);
		}
	}
	
	public static class Useage extends Entry<Stability> {

		private Useage(Stability value) {
			super(value);
		}

		public static Useage of(Stability value) {
			return new Useage(value);
		}
	}
}
