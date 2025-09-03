package com.barry.bytecode.thirdparty;

import com.android.build.api.instrumentation.FramesComputationMode;
import com.android.build.api.instrumentation.InstrumentationScope;
import com.android.build.api.variant.AndroidComponentsExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;


public class FixThirdPartyLibPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        if (!project.getPlugins().hasPlugin("com.android.application") &&
                !project.getPlugins().hasPlugin("com.android.library")) {
            project.getLogger().warn("未找到 Android 插件，FixThirdPartyLibPlugin 不会生效。");
            return;
        }

        try {
            AndroidComponentsExtension<?, ?, ?> androidComponents = project.getExtensions().getByType(AndroidComponentsExtension.class);

            androidComponents.onVariants(androidComponents.selector().all(), variant -> {
                variant.getInstrumentation().transformClassesWith(
                        ThirdPartyLibVisitorFactory.class,
                        InstrumentationScope.ALL,
                        params -> null
                );
                variant.getInstrumentation().setAsmFramesComputationMode(
                        FramesComputationMode.COPY_FRAMES
                );
            });
        } catch (Exception e) {
            project.getLogger().error("配置字节码转换失败: " + e.getMessage(), e);
        }
    }
}