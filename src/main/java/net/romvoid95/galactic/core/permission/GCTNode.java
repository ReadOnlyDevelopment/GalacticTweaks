package net.romvoid95.galactic.core.permission;

import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.romvoid95.galactic.Info;

public class GCTNode {
	
	private final String SPACE = ".";
	private final String PARENT = Info.ID;

	private final String NODE;
	private final DefaultPermissionLevel LEVEL;
	private final String DESC;
	private final int permLevel;
	
	private GCTNode(int permLevel, String node, DefaultPermissionLevel level, String desc) {
		this.NODE = PARENT + SPACE + node;
		this.LEVEL = level;
		this.DESC = desc;
		this.permLevel = permLevel;
	}
	
	public static GCTNode registerAdminNode(String node, String desc) {
		return new GCTNode(3, "serveradmin." + node, DefaultPermissionLevel.OP, desc);
	}
	
	public static GCTNode registerPublicNode(String node, String desc) {
		return new GCTNode(1, "public." + node, DefaultPermissionLevel.ALL, desc);
	}
	
	public String node() {
		return NODE;
	}

	public DefaultPermissionLevel level() {
		return LEVEL;
	}

	public String description() {
		return DESC;
	}
	
	public int permissionLevel() {
		return permLevel;
	}
}
