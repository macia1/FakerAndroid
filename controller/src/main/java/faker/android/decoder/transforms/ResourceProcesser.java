package faker.android.decoder.transforms;

import brut.androlib.meta.MetaInfo;
import faker.android.decoder.apktool.Resources;
import faker.android.decoder.api.AndroidProject;
import faker.android.decoder.api.Apk;
import faker.android.decoder.pipeline.Transform;
import faker.android.decoder.pipeline.TransformInvocation;
import faker.android.decoder.util.FileUtils;
import faker.android.decoder.util.ManifestEditor;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceProcesser extends Transform {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceProcesser.class);
    public ResourceProcesser(Apk apk, AndroidProject androidProject) {
        super(apk, androidProject);
    }

    @Override
    public void transform(TransformInvocation transformInvocation) {
        transformInvocation.callBack("ResourceProcesser");
        File out = androidProject.getMain();
        if(out.exists()){
            FileUtils.deleteDir(out);
        }
        out.mkdirs();
        Resources.decode(apk.getApkFile(),out);
        project(androidProject);
        discard(androidProject);
        meta(androidProject);
    }

    private void meta(AndroidProject androidProject) {
        File manifest = androidProject.getAndroidManifest();
        AndroidProject.ManifestInfo manifestInfo = new AndroidProject.ManifestInfo();
        MetaInfo metaInfo = null;
        try {
            metaInfo = MetaInfo.load(new FileInputStream(androidProject.getYmlFile()));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        ManifestEditor manifestEditor = null;
        try {
            manifestEditor = new ManifestEditor(manifest);
            String packageName = manifestEditor.getPackagenName();
            manifestInfo.setPakcageName(packageName);
            String applicationName = manifestEditor.getApplicationName();
            manifestInfo.setApplicationName(applicationName);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        if(metaInfo!=null){
            manifestInfo.setVersionCode(metaInfo.versionInfo.versionCode);
            manifestInfo.setVersionName(metaInfo.versionInfo.versionName);
            manifestInfo.setMinSdkVersion(metaInfo.sdkInfo.get("minSdkVersion"));
            manifestInfo.setTargetSdkVersion(metaInfo.sdkInfo.get("targetSdkVersion"));
        }
        androidProject.addIntermediate(AndroidProject.INTERMEDIATE_MANIFESTINFO,manifestInfo);
    }

    private void discard(AndroidProject androidProject) {
        File resources = androidProject.getMain();
        List<File> fs = Arrays.asList(resources.listFiles());
        List<String> dexnames = new ArrayList<>();
        for (File f:fs){
            String fn = f.getName();
            if(fn.startsWith("classes")&&fn.endsWith(".dex")) {
                dexnames.add(fn);
                f.delete();
            }
        }
        androidProject.addIntermediate(AndroidProject.INTERMEDIATE_DEX_NAMES,dexnames);
    }

    private void project(AndroidProject androidProject) {//TODO Rename
        //TODO 整理文件夾
        File resources = androidProject.getMain();
        if(!resources.exists()){
            return;
        }
        File libResources = new File(resources,"lib");
        if(libResources.exists()){
            libResources.renameTo(androidProject.getjniLibs());
        }
        File unknownResources = new File(resources,"unknown");
        if(unknownResources.exists()){
            unknownResources.renameTo(androidProject.getResources());
        }

        File originalResources = new File(resources,"original");
        if(originalResources.exists()){
            originalResources.renameTo(new File(androidProject.getAssets(),"original"));
        }
    }
}
