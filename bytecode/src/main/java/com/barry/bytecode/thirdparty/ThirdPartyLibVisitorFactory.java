package com.barry.bytecode.thirdparty;

import com.android.build.api.instrumentation.AsmClassVisitorFactory;
import com.android.build.api.instrumentation.ClassContext;
import com.android.build.api.instrumentation.ClassData;
import com.android.build.api.instrumentation.InstrumentationParameters;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public abstract class ThirdPartyLibVisitorFactory implements AsmClassVisitorFactory<InstrumentationParameters.None> {
    private static final String TARGET_CLASS = "com.example.third_party_lib.activity.ThirdPartyActivity";
    private static final String TARGET_CLASS_INTERNAL = TARGET_CLASS.replace('.', '/');

    @Override
    public @NotNull ClassVisitor createClassVisitor(@NotNull ClassContext classContext, @NotNull ClassVisitor classVisitor) {
        return new DanaleCloudClassVisitor(classVisitor);
    }

    @Override
    public boolean isInstrumentable(@NotNull ClassData classData) {
        return classData.getClassName().equals(TARGET_CLASS);
    }

    static class DanaleCloudClassVisitor extends ClassVisitor {

        public DanaleCloudClassVisitor(ClassVisitor cv) {
            super(Opcodes.ASM9, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            // 拦截并替换目标方法，签名: private void registerCustomReceiver()
            if ("registerCustomReceiver".equals(name) && "()V".equals(desc)) {
                System.out.println(">>> [ThirdPartyLib] Injecting method: " + TARGET_CLASS_INTERNAL + "#registerCustomReceiver");

                // 创建一个新的方法（覆盖原有）
                MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
                if (mv == null) {
                    return null;
                }

                // 开始写方法体
                mv.visitCode();

                // this.mCustomReceiver = new CustomReceiver(this);
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitTypeInsn(Opcodes.NEW, TARGET_CLASS_INTERNAL + "$CustomReceiver");
                mv.visitInsn(Opcodes.DUP);
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
                        TARGET_CLASS_INTERNAL + "$CustomReceiver",
                        "<init>",
                        "(L" + TARGET_CLASS_INTERNAL + ";)V",
                        false);
                mv.visitFieldInsn(Opcodes.PUTFIELD,
                        TARGET_CLASS_INTERNAL,
                        "mCustomReceiver",
                        "L" + TARGET_CLASS_INTERNAL + "$CustomReceiver;");

                // IntentFilter var1 = new IntentFilter();
                mv.visitTypeInsn(Opcodes.NEW, "android/content/IntentFilter");
                mv.visitInsn(Opcodes.DUP);
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "android/content/IntentFilter", "<init>", "()V", false);
                mv.visitVarInsn(Opcodes.ASTORE, 1);

                // var1.addAction("CustomAction");
                mv.visitVarInsn(Opcodes.ALOAD, 1);
                mv.visitLdcInsn("CustomAction");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                        "android/content/IntentFilter",
                        "addAction",
                        "(Ljava/lang/String;)V",
                        false);

                // if (android.os.Build.VERSION.SDK_INT >= 33) {
                //     this.registerReceiver(this.mCustomReceiver, var1, Context.RECEIVER_NOT_EXPORTED);
                // } else {
                //     this.registerReceiver(this.mCustomReceiver, var1);
                // }
                mv.visitFieldInsn(Opcodes.GETSTATIC, "android/os/Build$VERSION", "SDK_INT", "I");
                mv.visitIntInsn(Opcodes.BIPUSH, 33);                 // push 33
                Label elseLabel = new Label();
                Label endLabel = new Label();
                mv.visitJumpInsn(Opcodes.IF_ICMPLT, elseLabel);     // if (SDK_INT < 33) goto else

                // then-block (SDK >= 33)
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitFieldInsn(Opcodes.GETFIELD, TARGET_CLASS_INTERNAL, "mCustomReceiver",
                        "L" + TARGET_CLASS_INTERNAL + "$CustomReceiver;");
                mv.visitVarInsn(Opcodes.ALOAD, 1);
                mv.visitFieldInsn(Opcodes.GETSTATIC, "android/content/Context", "RECEIVER_NOT_EXPORTED", "I");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                        TARGET_CLASS_INTERNAL,
                        "registerReceiver",
                        "(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;I)Landroid/content/Intent;",
                        false);
                mv.visitInsn(Opcodes.POP);
                mv.visitJumpInsn(Opcodes.GOTO, endLabel);

                // else-block (SDK < 33)
                mv.visitLabel(elseLabel);
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitFieldInsn(Opcodes.GETFIELD, TARGET_CLASS_INTERNAL, "mCustomReceiver",
                        "L" + TARGET_CLASS_INTERNAL + "$CustomReceiver;");
                mv.visitVarInsn(Opcodes.ALOAD, 1);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                        TARGET_CLASS_INTERNAL,
                        "registerReceiver",
                        "(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;",
                        false);
                mv.visitInsn(Opcodes.POP);

                // end
                mv.visitLabel(endLabel);
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                mv.visitInsn(Opcodes.RETURN);

                // 正确的栈和局部变量大小计算
                // 最大栈深度：4（new + dup + aload + aload）
                // 局部变量：2（this + IntentFilter）
                mv.visitMaxs(4, 2);
                mv.visitEnd();

                // 返回 null 或者返回一个 MethodVisitor 都可，这里返回 null 表示我们已直接写入方法，不需要后续访问
                return null;
            }

            // 非目标方法，正常传递
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }
}
