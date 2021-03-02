package net.romvoid95.plugin;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.io.*;

public class ObfHelper
{
	private static Map<String, String> fields;
	private static Map<String, String> methods;

	static
	{
		if (inDevEnv())
		{
			try
			{
				Class gradleClass = Class.forName("net.minecraftforge.gradle.GradleStartCommon");
				Field dirField = gradleClass.getDeclaredField("CSV_DIR");
				dirField.setAccessible(true);
				File mappingDir = (File) dirField.get(null);

				fields = readMappings(new File(mappingDir, "fields.csv"));
				methods = readMappings(new File(mappingDir, "methods.csv"));
			} catch (Exception e)
			{
				e.printStackTrace();
				fields = readMappings(new File("./../mcp/fields.csv"));
				methods = readMappings(new File("./../mcp/methods.csv"));
			}

		} else
		{
			fields = methods = null;
		}
	}

	public static boolean inDevEnv()
	{
		return GalacticTweaksPlugin.InDevEnv;
	}

	public static String field(String srgName)
	{
		if (inDevEnv())
		{
			return fields.get(srgName);
		} else
		{
			return srgName;
		}
	}

	public static String method(String srgName)
	{
		if (inDevEnv())
		{
			return methods.get(srgName);
		} else
		{
			return srgName;
		}
	}

	private static Map<String, String> readMappings(File file)
	{
		if (!file.isFile())
		{
			throw new RuntimeException("Unable to locate MCP Mappings");
		}
		try
		{
			return Files.readLines(file, Charsets.UTF_8, new MCPParser());
		} catch (IOException e)
		{
			throw new RuntimeException("Unable to parse SRG->MCP Mappings", e);
		}
	}

	private static class MCPParser implements LineProcessor<Map<String, String>>
	{
		private static final Splitter splitter = Splitter.on(',').trimResults();
		private final Map<String, String> map = Maps.newHashMap();
		private boolean foundFirst;

		@Override
		public boolean processLine(String line) throws IOException
		{
			if (!foundFirst)
			{
				foundFirst = true;
				return true;
			}

			Iterator<String> splitted = splitter.split(line).iterator();
			try
			{
				String srg = splitted.next();
				String mcp = splitted.next();
				if (!map.containsKey(srg))
				{
					map.put(srg, mcp);
				}
			} catch (NoSuchElementException e)
			{
				throw new IOException("Error! MCP File Is Invalid", e);
			}

			return true;
		}

		@Override
		public Map<String, String> getResult()
		{
			return ImmutableMap.copyOf(map);
		}
	}
}
