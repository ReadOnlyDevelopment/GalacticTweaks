package net.romvoid95.galactic.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.experimental.UtilityClass;
import net.romvoid95.galactic.Info;
import net.romvoid95.galactic.core.config.CoreConfig;

@UtilityClass
public class GCTLog {

	private final Logger logger = LogManager.getLogger(Info.NAME);

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void info(String msg, Object... params) {
		logger.info(msg, params);
	}

	public static void warn(String msg) {
		logger.warn(msg);
	}

	public static void warn(String msg, Object... params) {
		logger.warn(msg, params);
	}

	public static void warn(Throwable t, String msg, Object... params) {
		logger.warn(msg, params);
	}

	public static void error(String msg, Object... params) {
		logger.error(msg, params);
	}

	public static void fatal(String msg, Object... params) {
		logger.fatal(msg, params);
	}

	public static void trace(String msg, Object... params) {
		logger.trace(msg, params);
	}

	public static void catching(Throwable t) {
		logger.catching(t);
	}

	public static void debug(String msg) {
		if (CoreConfig.enableDebugLog.get()) {
			info("*******************************[GCT DEBUG]**************************************");
			for (final String subline : wrapString(msg, 78, false, new ArrayList<>())) {
				info("* " + subline);
			}
			info("********************************************************************************");
		}
	}

	public static void noticableWarning(boolean trace, List<String> lines) {
		error("********************************************************************************");

		for (final String line : lines) {
			for (final String subline : wrapString(line, 78, false, new ArrayList<>())) {
				error("* " + subline);
			}
		}

		if (trace) {
			final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			for (int i = 2; i < 8 && i < stackTrace.length; i++) {
				warn("*  at {}{}", stackTrace[i].toString(), i == 7 ? "..." : "");
			}
		}

		error("********************************************************************************");
	}

	public static void noticableWarning(boolean trace, String... lines) {
		error("********************************************************************************");

		for (final String line : lines) {
			for (final String subline : wrapString(line, 78, false, new ArrayList<>())) {
				error("* " + subline);
			}
		}

		if (trace) {
			final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			for (int i = 2; i < 8 && i < stackTrace.length; i++) {
				warn("*  at {}{}", stackTrace[i].toString(), i == 7 ? "..." : "");
			}
		}

		error("********************************************************************************");
	}

	private static List<String> wrapString(String string, int lnLength, boolean wrapLongWords, List<String> list) {
		final String lines[] = WordUtils.wrap(string, lnLength, null, wrapLongWords).split(SystemUtils.LINE_SEPARATOR);
		Collections.addAll(list, lines);
		return list;
	}
}
