package net.romvoid95.plugin;

import org.apache.logging.log4j.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import net.minecraft.launchwrapper.*;

public class Transformer implements IClassTransformer
{

	Logger logger = LogManager.getLogger("GalacticTweaksPlugin");

	public Transformer()
	{
	}

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if (transformedName.equals("net.minecraft.client.Minecraft"))
		{
			return patchMinecraft(basicClass);
		}
		return basicClass;
	}

	private byte[] patchMinecraft(byte[] basicClass)
	{
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, 0);

		MethodNode refreshResources = null;
		MethodNode startGame = null;

		for (MethodNode mn : classNode.methods)
		{
			if (mn.name.equals(ObfHelper.method("func_110436_a")))
			{
				refreshResources = mn;
			} else if (mn.name.equals(ObfHelper.method("func_71384_a")))
			{
				startGame = mn;
			}
		}

		if (refreshResources != null)
		{
			for (int i = 0; i < refreshResources.instructions.size(); i++)
			{
				AbstractInsnNode ain = refreshResources.instructions.get(i);
				if (ain instanceof MethodInsnNode)
				{
					MethodInsnNode min = (MethodInsnNode) ain;
					if (min.name.equals(ObfHelper.method("func_110541_a")))
					{
						InsnList toInsert = new InsnList();
						toInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "net/romvoid95/galactic/feature/common/serverhub/assets/AssetsUtil",
								"defineResourcePack", "(Ljava/util/List;)V", false));
						toInsert.add(new VarInsnNode(Opcodes.ALOAD, 1));
						refreshResources.instructions.insertBefore(min, toInsert);
						i += 2;
					} else if (min.name.equals("newArrayList"))
					{
						AbstractInsnNode target = refreshResources.instructions.get(i + 1);
						InsnList toInsert = new InsnList();
						toInsert.add(new VarInsnNode(Opcodes.ALOAD, 1));
						toInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "net/romvoid95/galactic/feature/common/serverhub/assets/AssetsUtil",
								"defineResourcePack", "(Ljava/util/List;)V", false));
						refreshResources.instructions.insert(target, toInsert);
					}
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);

		return writer.toByteArray();
	}
}
