package faker.android.decoder.api;

import faker.android.decoder.pipeline.TransformInvocation;
import faker.android.decoder.pipeline.TransformManager;
import faker.android.decoder.transforms.*;

import java.io.File;

public class Transfer {
    private File in;
    private File out;
    public Transfer(String inPath,String outPath){
        in = new File(inPath);
        out = new File(outPath);
    }
    public void translate(){
        TransformManager transformManager = new TransformManager(new TransformInvocation() {
            @Override
            public void callBack(String msg) {
            }
        });
        Apk apk = new Apk(in);
        AndroidProject androidProject = new AndroidProject(out);

        //Decoder
        transformManager.addTransform(new ResourceProcesser(apk,androidProject));
        transformManager.addTransform(new DexToSmali(apk,androidProject));
        transformManager.addTransform(new DexToJar(apk,androidProject));
        transformManager.addTransform(new Il2cppDumper(apk,androidProject));

        //Merge
        transformManager.addTransform(new RuntimeBaseMerge(apk,androidProject));
        transformManager.addTransform(new RuntimeIl2cppMerge(apk,androidProject));

        //fix
        transformManager.addTransform(new Project(apk,androidProject));
        transformManager.action();
    }
}
