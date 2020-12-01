package faker.android.decoder;

import faker.android.decoder.api.AndroidProject;
import faker.android.decoder.api.Apk;
import faker.android.decoder.pipeline.TransformInvocation;
import faker.android.decoder.pipeline.TransformManager;
import faker.android.decoder.transforms.*;
import faker.android.decoder.util.TestUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TestProject {
    private static final Logger LOG = LoggerFactory.getLogger(TestProject.class);
    static File in = TestUtils.getFileFromSampleDir("app-with-fake-dex.apk");
    @Test
    public void fakeRroject() throws IOException {
        File out = TestUtils.getFileOutSampleDir("fakeRroject","niuzai");
        LOG.info("today to finish--");
        TransformManager transformManager = new TransformManager(new TransformInvocation() {
            @Override
            public void callBack(String msg) {
                LOG.info("call back -----------"+msg);
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

        transformManager.addTransform(new Project(apk,androidProject));

        transformManager.action();
    }
}
