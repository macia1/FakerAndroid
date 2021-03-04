package faker.android.decoder;
import com.luhuiguo.chinese.ChineseUtils;
import com.luhuiguo.chinese.pinyin.PinyinFormat;
import faker.android.decoder.api.AndroidProject;
import faker.android.decoder.api.Apk;
import faker.android.decoder.api.Transfer;
import faker.android.decoder.pipeline.TransformInvocation;
import faker.android.decoder.pipeline.TransformManager;
import faker.android.decoder.transforms.*;
import faker.android.decoder.util.TestUtils;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class TestProject {
//    static File in = TestUtils.getFileFromSampleDir("app-with-fake-dex.apk");
    static File in = TestUtils.getFileFromSampleDir("niuzai.apk");
    @Test
    public void fakeRroject() throws IOException {
        File out = TestUtils.getFileOutSampleDir("fakeRroject","niuzai");
        TransformManager transformManager = new TransformManager(new TransformInvocation() {
            @Override
            public void callBack(String msg) {
                System.out.println(msg);
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

    @Test
    public void batchFakeWithApi() {
        func(new File("D:\\Apk"));
    }

    private static void func(File file){
        File[] fs = file.listFiles();
        for(File f:fs){
            if(f.isDirectory()){
                func(f);
            }else if(f.isFile()) {
                if(f.getName().endsWith(".apk")){
                    TransformInvocation transformInvocation = new TransformInvocation() {
                        @Override
                        public void callBack(String msg) {
                            System.out.println(msg);
                        }
                    };
                    File outs = new File("D:\\Outs");
                    File projectFile = new File(outs, ChineseUtils.toPinyin(f.getName().replace(".apk",""), PinyinFormat.TONELESS_PINYIN_FORMAT).replace(" ","-"));
                    String outDir = projectFile.getAbsolutePath();
                    new Transfer(f.getAbsolutePath(),outDir,transformInvocation).translate();
                }
            }
        }
    }
}
