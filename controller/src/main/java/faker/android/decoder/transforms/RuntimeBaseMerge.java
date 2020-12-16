package faker.android.decoder.transforms;

import faker.android.decoder.api.AndroidProject;
import faker.android.decoder.api.Apk;
import faker.android.decoder.pipeline.Transform;
import faker.android.decoder.pipeline.TransformInvocation;
import faker.android.decoder.runtime.base.RuntimeBase;
import faker.android.decoder.util.FileUtils;
import faker.android.decoder.util.ManifestEditor;
import faker.android.decoder.util.TextUtil;
import org.dom4j.DocumentException;

import java.io.File;
import java.io.IOException;

public class RuntimeBaseMerge extends Transform {

    public RuntimeBaseMerge(Apk apk, AndroidProject androidProject) {
        super(apk, androidProject);
    }

    @Override
    public boolean transform(TransformInvocation transformInvocation) {
        transformInvocation.callBack("Rumtime base mereging...");
        RuntimeBase.mergeRuntimeLibsJava(androidProject.getLibs());
        RuntimeBase.mergeRuntimeLibsCpp(androidProject.getCppLibs());
        RuntimeBase.mergeRuntimeJavaCode(androidProject.getJava());
        RuntimeBase.mergeRuntimeCppCode(androidProject.getCpp());
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
        File file  = new File(androidProject.getJava(),"com/faker/android/FakerApp.java");
        String applicationName = manifestInfo.getApplicationName();
        if(!TextUtil.isEmpty(applicationName)) {
            try {
                FileUtils.autoReplaceStr(file,"{APPLICATION_NAME}",applicationName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                FileUtils.autoReplaceStr(file,"{APPLICATION_NAME}","Application");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File manifest = androidProject.getAndroidManifest();
        ManifestEditor manifestEditor = null;
        try {
            manifestEditor = new ManifestEditor(manifest);

            manifestEditor.modApplication("com.faker.android.FakerApp");//
            manifestEditor.extractNativeLibs();
            manifestEditor.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
