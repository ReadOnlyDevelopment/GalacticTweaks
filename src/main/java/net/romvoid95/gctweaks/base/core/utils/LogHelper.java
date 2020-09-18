/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.romvoid95.gctweaks.base.core.utils;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.launchwrapper.Launch;
import net.romvoid95.gctweaks.Ref;

// TODO: Auto-generated Javadoc
/**
 * The Class LogHelper.
 */
public class LogHelper {
	/** The log. */
	private static Logger LOG = LogManager.getFormatterLogger(Ref.MOD_NAME);

	/** The Constant obf. */
	public final static boolean obf;

	static {
		boolean flag = true;
		try {
			flag = (Launch.classLoader.getClassBytes("net.minecraft.world.World") == null);
		}
		catch (IOException iOException) {}
		obf = flag;
	}

	private static void log (Level logLevel, Object object) {
		LOG.log(logLevel, String.valueOf(object));
	}

	private static void log (Level logLevel, Object object, Throwable throwable) {
		LOG.log(logLevel, String.valueOf(object), throwable);
	}

	/**
	 * Dev.
	 *
	 * @param object the object
	 */
	public void dev (Object object) {
		if (!obf)
			log(Level.INFO, "[DEV]: " + object);
	}

	/**
	 * Catching.
	 *
	 * @param throwable the throwable
	 */
	public void catching (Throwable throwable) {
		LOG.catching(throwable);
	}

	/**
	 * All.
	 *
	 * @param object the object
	 */
	public void all (Object object) {

		log(Level.ALL, object);
	}

	/**
	 * Debug.
	 *
	 * @param object the object
	 */
	public void debug (Object object) {
		log(Level.DEBUG, object);
	}

	/**
	 * Error.
	 *
	 * @param object the object
	 */
	public void error (Object object) {
		log(Level.ERROR, object);
	}

	/**
	 * Fatal.
	 *
	 * @param object the object
	 */
	public void fatal (Object object) {
		log(Level.FATAL, object);
	}

	/**
	 * Info.
	 *
	 * @param object the object
	 */
	public void info (Object object) {
		log(Level.INFO, object);
	}

	/**
	 * Trace.
	 *
	 * @param object the object
	 */
	public void trace (Object object) {
		log(Level.TRACE, object);
	}

	/**
	 * Warn.
	 *
	 * @param object the object
	 */
	public void warn (Object object) {
		log(Level.WARN, object);
	}

	/**
	 * Formatted all.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void all (String format, Object... object) {
		log(Level.ALL, String.format(format, object));
	}

	/**
	 * Formatted debug.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void debug (String format, Object... object) {
		log(Level.DEBUG, String.format(format, object));
	}

	/**
	 * Formatted error.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void error (String format, Object... object) {
		log(Level.ERROR, String.format(format, object));
	}

	/**
	 * Formatted fatal.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void fatal (String format, Object... object) {
		log(Level.FATAL, String.format(format, object));
	}

	/**
	 * Formatted info.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void info (String format, Object... object) {
		log(Level.INFO, String.format(format, object));
	}

	/**
	 * Formatted trace.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void trace (String format, Object... object) {
		log(Level.TRACE, String.format(format, object));
	}

	/**
	 * Formatted warn.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void warn (String format, Object... object) {
		log(Level.WARN, String.format(format, object));
	}

	/**
	 * Throwable all.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public void throwable_All (String object, Throwable throwable) {
		log(Level.ALL, object, throwable);
	}

	/**
	 * Throwable debug.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public void throwable_Debug (String object, Throwable throwable) {
		log(Level.DEBUG, object, throwable);
	}

	/**
	 * Throwable error.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public void throwable_Error (String object, Throwable throwable) {
		log(Level.ERROR, object, throwable);
	}

	/**
	 * Throwable fatal.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public void throwable_Fatal (String object, Throwable throwable) {
		log(Level.FATAL, object, throwable);
	}

	/**
	 * Throwable info.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public void throwable_Info (String object, Throwable throwable) {
		log(Level.INFO, object, throwable);
	}

	/**
	 * Throwable trace.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public void throwable_Trace (String object, Throwable throwable) {
		log(Level.TRACE, object, throwable);
	}

	/**
	 * Throwable warn.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public void Throwable_Warn (String object, Throwable throwable) {
		log(Level.WARN, object, throwable);
	}

	/**
	 * Big all.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public void bigAll (String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		all("****************************************");
		all("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			all("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		all("****************************************");
	}

	/**
	 * Big debug.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public void bigDebug (String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		debug("****************************************");
		debug("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			debug("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		debug("****************************************");
	}

	/**
	 * Big error.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public void bigError (String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		error("****************************************");
		error("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			error("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		error("****************************************");
	}

	/**
	 * Big fatal.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public void bigFatal (String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		fatal("****************************************");
		fatal("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			fatal("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		fatal("****************************************");
	}

	/**
	 * Big info.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public void bigInfo (String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		info("****************************************");
		info("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			info("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		info("****************************************");
	}

	/**
	 * Big trace.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public void bigTrace (String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		trace("****************************************");
		trace("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			trace("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		trace("****************************************");
	}

	/**
	 * Big warn.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public void bigWarn (String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		warn("****************************************");
		warn("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			warn("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		warn("****************************************");
	}

}
