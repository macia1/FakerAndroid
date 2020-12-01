package faker.android.decoder.transforms;

import faker.android.decoder.api.AndroidProject;
import faker.android.decoder.api.Apk;
import faker.android.decoder.pipeline.Transform;
import faker.android.decoder.pipeline.TransformInvocation;
import faker.android.decoder.util.FileUtils;
import faker.android.decoder.util.PatchUtil;
import faker.android.decoder.util.TextUtil;
import java.io.File;
import java.io.IOException;

public class Project extends Transform {
    public Project(Apk apk, AndroidProject androidProject) {
        super(apk, androidProject);
    }
    @Override
    public void transform(TransformInvocation transformInvocation) {
        transformInvocation.callBack("Android studio project fomarting....");
        PatchUtil.copyDirFromJar("/project",androidProject.getProject().getAbsolutePath());
        fixProject(androidProject);
        transformInvocation.callBack("You have faked a android studio project from apk!");
        transformInvocation.callBack("Generated project path:"+androidProject.getProject().getAbsolutePath()+".");
    }

    private void fixProject(AndroidProject androidProject){

        AndroidProject.ManifestInfo manifestInfo = (AndroidProject.ManifestInfo) androidProject.getIntermediate(AndroidProject.INTERMEDIATE_MANIFESTINFO);
        File appBuild = androidProject.getAppBuild();
        //pkg
        try {
            FileUtils.autoReplaceStr(appBuild,"{pkg}",manifestInfo.getPakcageName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ABI
        String abiStr = "";
        File targetjniLibs = androidProject.getjniLibs();
        File jniLibsARMV7A = new File(targetjniLibs,"armeabi-v7a");
        File armeabi = new File(targetjniLibs,"armeabi");
        if(armeabi.exists()&&!jniLibsARMV7A.exists()){
            armeabi.renameTo(jniLibsARMV7A);
        }
        if(jniLibsARMV7A.exists()){
            if(TextUtil.isEmpty(abiStr)){
                abiStr = "'armeabi-v7a'";
            }else {
                abiStr = abiStr+",'armeabi-v7a'";
            }
        }
        File jniLibsARM64V8A = new File(targetjniLibs,"arm64-v8a");
        if(jniLibsARM64V8A.exists()){
            if(TextUtil.isEmpty(abiStr)){
                abiStr = "'arm64-v8a'";
            }else {
                abiStr = abiStr+",'arm64-v8a'";
            }
        }
        if(!jniLibsARMV7A.exists()&&!jniLibsARM64V8A.exists()&&!armeabi.exists()){
            abiStr = "'armeabi-v7a','arm64-v8a'";
        }
        try {
            FileUtils.autoReplaceStr(appBuild,"{abi}",abiStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //buildconfig

        try {
            FileUtils.autoReplaceStr(appBuild,"{versionCode}",manifestInfo.getVersionCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileUtils.autoReplaceStr(appBuild,"{versionName}",manifestInfo.getVersionName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String minSdkVersion = manifestInfo.getMinSdkVersion();
        //FileUtil.autoReplaceStr(gameBuildGrandle,"{minSdkVersion}",minSdkVersion);
        String targetSdkVersion =manifestInfo.getTargetSdkVersion();

        if(TextUtil.isEmpty(targetSdkVersion)){
            try {
                FileUtils.autoReplaceStr(appBuild,"{targetSdkVersion}","26");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                FileUtils.autoReplaceStr(appBuild,"{targetSdkVersion}",targetSdkVersion);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
