package faker.android.decoder.transforms;

import faker.android.decoder.api.AndroidProject;
import faker.android.decoder.api.Apk;
import faker.android.decoder.pipeline.Transform;
import faker.android.decoder.pipeline.TransformInvocation;
import faker.android.decoder.rutime.il2cpp.RuntimeIl2cpp;
import faker.android.decoder.util.FileUtils;
import java.io.File;
import java.io.IOException;

public class RuntimeIl2cppMerge extends Transform {

    public RuntimeIl2cppMerge(Apk apk, AndroidProject androidProject) {
        super(apk, androidProject);
    }

    @Override
    public boolean transform(TransformInvocation transformInvocation) {
        File il2cppScaffoldingARM = new File(androidProject.getCpp(),"Il2cpp-Scaffolding-ARM");
        File il2cppScaffoldingARM64 = new File(androidProject.getCpp(),"Il2cpp-Scaffolding-ARM64");
        if(!il2cppScaffoldingARM.exists()&&!il2cppScaffoldingARM64.exists()) {
            return true;
        }
        transformInvocation.callBack("Runtime il2cpp merging...");
        RuntimeIl2cpp.mergeRuntimeIl2cppCpp(androidProject.getCpp());
        RuntimeIl2cpp.mergeRuntimeIl2cppJava(androidProject.getJava());
        fixTmplCode(androidProject);
        return true;
    }

    private void fixTmplCode(AndroidProject androidProject) {
        AndroidProject.ManifestInfo manifestInfo = (AndroidProject.ManifestInfo) androidProject.getIntermediate(AndroidProject.INTERMEDIATE_MANIFESTINFO);
        File fakerActivityFile = new File(androidProject.getJava(),"com/faker/android/FakerActivity.java");
        try {
            FileUtils.autoReplaceStr(fakerActivityFile,"{R}",manifestInfo.getPakcageName()+".R");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
