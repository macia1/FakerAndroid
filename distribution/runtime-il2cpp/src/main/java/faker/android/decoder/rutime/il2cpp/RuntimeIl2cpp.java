package faker.android.decoder.rutime.il2cpp;

import faker.android.decoder.util.PatchUtil;

import java.io.File;

public class RuntimeIl2cpp {
    public static void mergeRuntimeIl2cppCpp(File out){
        PatchUtil.copyDirFromJar("/il2cpp-template/cpp",out.getAbsolutePath());
        mergeRuntimeIl2cppscaffolding(out);
    }

    public static void mergeRuntimeIl2cppJava(File out){
        PatchUtil.copyDirFromJar("/il2cpp-template/java",out.getAbsolutePath());
    }

    private static void mergeRuntimeIl2cppscaffolding(File out){
        File il2cppScaffoldingARM = new File(out,"Il2cpp-Scaffolding-ARM");
        if(il2cppScaffoldingARM.exists()){
            PatchUtil.copyDirFromJar("/il2cpp-template/scaffolding-cpp",il2cppScaffoldingARM.getAbsolutePath());
        }
        File il2cppScaffoldingARM64 = new File(out,"Il2cpp-Scaffolding-ARM64");
        if(il2cppScaffoldingARM64.exists()){
            PatchUtil.copyDirFromJar("/il2cpp-template/scaffolding-cpp",il2cppScaffoldingARM64.getAbsolutePath());
        }
        File il2cppScaffoldingx86 = new File(out,"Il2cpp-Scaffolding-x86");
        if(il2cppScaffoldingARM64.exists()){
            PatchUtil.copyDirFromJar("/il2cpp-template/scaffolding-cpp",il2cppScaffoldingx86.getAbsolutePath());
        }
    }
}
