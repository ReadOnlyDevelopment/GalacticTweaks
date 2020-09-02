package net.rom.gctweaks.mixins;

//@Mixin(CommandLocate.class)
public abstract class CommandLocateMixin {

//	@Redirect(
//			method = "getTabCompletions(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/command/ICommandSender;[Ljava/lang/String;Lnet/minecraft/util/math/BlockPos;)Ljava/util/List;",
//			at = @At(
//					value = "INVOKE",
//					target = "net/minecraft/command/CommandLocate.getListOfStringsMatchingLastWord([Ljava/lang/String;[Ljava/lang/String;)Ljava/util/List;"))
//	private List<String> addDungeonsToList (String[] args, String... possibilities) {
//		List<String> list = CommandBase.getListOfStringsMatchingLastWord(args, "Dungeon");
//		list.addAll(Arrays.stream(possibilities).collect(Collectors.toList()));
//		return CommandBase.getListOfStringsMatchingLastWord(args, list);
//	}
}
