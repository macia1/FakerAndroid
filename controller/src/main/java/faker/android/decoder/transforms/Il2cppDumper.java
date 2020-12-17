package faker.android.decoder.transforms;

import faker.android.decoder.exception.BinaryException;
import faker.android.decoder.exception.FakerAndroidException;
import faker.android.decoder.il2cpp.Il2cppBinary;
import faker.android.decoder.api.AndroidProject;
import faker.android.decoder.api.Apk;
import faker.android.decoder.pipeline.Transform;
import faker.android.decoder.pipeline.TransformInvocation;

public class Il2cppDumper extends Transform  {
    public Il2cppDumper(Apk apk, AndroidProject androidProject) {
        super(apk, androidProject);
    }

    @Override
    public boolean transform(TransformInvocation transformInvocation) {
        transformInvocation.callBack("Checking or generating il2cppscafoding...");
        try {
            Il2cppBinary.dumpIl2cpp(apk.getApkFile(),androidProject.getCpp());
        } catch (Exception e) {
            transformInvocation.callBack("exception while excute il2cpp binary");
            e.printStackTrace();
        }
        return true;
    }
}
