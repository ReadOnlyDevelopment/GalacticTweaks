package net.romvoid95.gctweaks.internal.versioning;

public class ThreadVersionChecker extends Thread {

	public ThreadVersionChecker () {
		setName("Version Checker Thread");
		setDaemon(true);
		start();
	}

	@Override
	public void run() {
		try {
			VersionChecker.onlineVersion = Request.getLatestVersion();

		} catch (Exception e) {
			e.printStackTrace();
		}
		VersionChecker.doneChecking = true;
	}
}
