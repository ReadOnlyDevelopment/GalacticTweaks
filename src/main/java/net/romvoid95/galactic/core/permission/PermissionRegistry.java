package net.romvoid95.gctweaks.internal.permission;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public class PermissionRegistry {
	
	public static final String OXYGEN_CMDS = "gctweaks.operator.oxygen";
	
	  public static void register() {
		    PermissionAPI.registerNode(OXYGEN_CMDS, DefaultPermissionLevel.OP, "Allows only Operators to use certain Commands");
		  }

	  public static boolean hasPermissionHere(EntityPlayer player) {
		    if (player.world.isRemote) {
		      return true;//Do not check permissions on client side! can crash or go crazy
		    }
		    return PermissionAPI.hasPermission(player, OXYGEN_CMDS);
		  }
}
