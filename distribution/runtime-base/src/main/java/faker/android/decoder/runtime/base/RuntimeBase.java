package faker.android.decoder.runtime.base;

import faker.android.decoder.util.PatchUtil;

import java.io.File;

public class RuntimeBase {
    public static void mergeRuntimeLibsCpp(File out){
        PatchUtil.copyDirFromJar("/libs/cpp",out.getAbsolutePath());
    }

    public static void mergeRuntimeLibsJava(File out){
        PatchUtil.copyDirFromJar("/libs/java",out.getAbsolutePath());
    }

    public static void mergeRuntimeJavaCode(File out){
        PatchUtil.copyDirFromJar("/template/java",out.getAbsolutePath());
    }

    public static void mergeRuntimeCppCode(File out){
        PatchUtil.copyDirFromJar("/template/cpp",out.getAbsolutePath());
    }
}
