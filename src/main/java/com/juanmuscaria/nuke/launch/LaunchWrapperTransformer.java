package com.juanmuscaria.nuke.launch;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public class LaunchWrapperTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass != null && (transformedName.startsWith("net.minecraft.")
            || transformedName.startsWith("com.mojang.")
            || !name.contains("."))) {
            try {
                ClassNode classNode = new ClassNode();
                ClassReader cr = new ClassReader(basicClass);
                cr.accept(classNode, 0);
                Platform.current().engine().corruptClass(classNode);
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                classNode.accept(cw);
                return cw.toByteArray();
            } catch (Throwable e) {
                Platform.current().logger().error("Something bad happened when trying to patch {0}", name, e);
            }
        }
        return basicClass;
    }
}
