package com.fakerandroid.decoder.transforms;

import com.fakerandroid.decoder.api.AndroidProject;
import com.fakerandroid.decoder.runtime.base.RuntimeBase;
import com.fakerandroid.decoder.api.Apk;
import com.fakerandroid.decoder.pipeline.Transform;
import com.fakerandroid.decoder.pipeline.TransformInvocation;
import com.fakerandroid.decoder.util.FileUtils;
import com.fakerandroid.decoder.util.ManifestEditor;
import com.fakerandroid.decoder.util.TextUtil;

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
        File fakerActivityFile = new File(androidProject.getJava(),"com/fakerandroid/boot/FakerActivity.java");
        try {
            FileUtils.autoReplaceStr(fakerActivityFile,"{R}",manifestInfo.getPakcageName()+".R");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file  = new File(androidProject.getJava(),"com/fakerandroid/boot/FakerApp.java");
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

            manifestEditor.modApplication("com.fakerandroid.boot.FakerApp");//
            manifestEditor.extractNativeLibs();
            manifestEditor.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
