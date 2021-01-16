package net.romvoid95.gctweaks.internal.versioning;

import net.romvoid95.gctweaks.GalacticTweaks;
import net.romvoid95.gctweaks.Ref;

public class ThreadVersionChecker extends Thread {

	public ThreadVersionChecker () {
		setName(Ref.MOD_NAME + " Version Check");
		setDaemon(true);
		start();
	}

	@Override
	public void run() {
		try {
			GalacticTweaks.logger.info("Starting GalacticTweaks Version Check Thread");
			
			VersionChecker.updateVersion = Request.getLatestVersion();
			
			if(VersionChecker.updateVersion.isGreaterThan(VersionChecker.currentRunningVersion)) {
				VersionChecker.notifyForUpdate = true;
			}
			GalacticTweaks.logger.info("GalacticTweaks Version Check Finished");
		} catch (Exception e) {
			GalacticTweaks.logger.getLogger().error("GalacticTweaks Version Check Failed", e);
			VersionChecker.checkThreadFailed  = true;
		}
		
		if(!VersionChecker.checkThreadFailed) {
			if(VersionChecker.notifyForUpdate) {
				GalacticTweaks.logger.info("GalacticTweaks Update Found!");
			} else {
				GalacticTweaks.logger.info("GalacticTweaks is up to date");
			}
		}
		VersionChecker.checkThreadDone = true;
	}
}
