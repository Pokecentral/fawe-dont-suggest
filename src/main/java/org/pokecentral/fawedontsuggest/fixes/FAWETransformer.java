package org.pokecentral.fawedontsuggest.fixes;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class FAWETransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (transformedName.startsWith("com.sk89q.worldedit.sponge.SpongePlatform")) {
            ClassNode node = new ClassNode();
            ClassReader reader = new ClassReader(basicClass);
            reader.accept(node, 0);

            node.methods.forEach(method -> {
                if (method.name.equalsIgnoreCase("getSuggestions")) {
                    System.out.println("Disabling FAWE command suggestions.");

                    InsnList insns = new InsnList();
                    insns.add(new TypeInsnNode(Opcodes.NEW, "java/util/ArrayList"));
                    insns.add(new InsnNode(Opcodes.DUP));
                    insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V",false));
                    insns.add(new InsnNode(Opcodes.ARETURN));

                    method.instructions = insns;
                }
            });

            ClassWriter writer = new ClassWriter(0);
            node.accept(writer);
            return writer.toByteArray();
        }

        return basicClass;
    }
}
