package net.romvoid95.api.version;

public class VersionException extends RuntimeException {

	private static final long serialVersionUID = 3380584011358917827L;

	public VersionException(String msg) {
		super(msg);
	}

	public VersionException(String msg, Throwable t) {
		super(msg, t);
	}
}
