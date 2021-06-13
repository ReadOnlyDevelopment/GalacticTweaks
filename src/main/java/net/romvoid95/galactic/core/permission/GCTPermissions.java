package net.romvoid95.galactic.core.permission;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.server.permission.PermissionAPI;

public class GCTPermissions {
	
	public static GCTNode ADMIN_TELEPORT = GCTNode.registerAdminNode("teleport", "Allows server Admins to use admintp Command");
	public static GCTNode ADMIN_OXYGEN = GCTNode.registerAdminNode("oxygen", "Allows server Admins to use oxygenreset Command");
	
	public static void registerNodes() {
		PermissionAPI.registerNode(ADMIN_TELEPORT.node(), ADMIN_TELEPORT.level(), ADMIN_TELEPORT.description());
		PermissionAPI.registerNode(ADMIN_OXYGEN.node(), ADMIN_OXYGEN.level(), ADMIN_OXYGEN.description());
	}
	
	public static boolean hasPerm(EntityPlayerMP player, GCTNode node) {
		return PermissionAPI.hasPermission(player, node.node());
	}
}
