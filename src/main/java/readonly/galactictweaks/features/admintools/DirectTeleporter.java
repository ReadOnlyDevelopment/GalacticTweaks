package readonly.galactictweaks.features.admintools;

import static net.minecraft.util.text.TextFormatting.*;

import net.minecraft.command.ICommand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;
import readonly.api.feature.Feature;
import readonly.galactictweaks.GCTweaks;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModuleConfig;

public class DirectTeleporter extends Feature {

	public DirectTeleporter() {
		this.category = "AdminTeleport";
		this.categoryComment = "Allows Admins to teleport to planets directly";
	}

	@Override
	public void ServerStartingEvent(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandAdminTeleport());
	}

	@Override
	public boolean isEnabled() {
		return GalacticraftModuleConfig.DIRECT_TELEPORTER;
	}
	
	@Override
	public boolean usesEvents() {
		return true;
	}
	
	@Override
	public void init() {
		PermissionAPI.registerNode(GCTweaks.NODE_ADMINTP, DefaultPermissionLevel.OP, "Allows direct teleport to Planets");
	}

	@SubscribeEvent
	public void onCommand(CommandEvent event) {
		ICommand admintp = new CommandAdminTeleport();

		if(event.getCommand().getName() == "dimensiontp") {
			event.setCanceled(true);
			TextComponentString overrideNotice = new TextComponentString(
					msg(RED, "The Command ") +
					msg(GOLD, "\"dimensiontp\"") + 
					msg(RED, " is overrriden"));
			TextComponentString useInstead = new TextComponentString(msg(GOLD, "Use the following command instead:"));
			TextComponentString BLANK = new TextComponentString("");
			TextComponentString admintpCmd = new TextComponentString(msg(GREEN, admintp.getUsage(event.getSender())));
			
			event.getSender().sendMessage(overrideNotice);
			event.getSender().sendMessage(useInstead);
			event.getSender().sendMessage(BLANK);
			event.getSender().sendMessage(admintpCmd);
		}
	}
	
	private String msg(TextFormatting format, String msg) {
		String output = format + msg + RESET;
		return output;
	}
}
