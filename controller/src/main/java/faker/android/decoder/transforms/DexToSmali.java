package faker.android.decoder.transforms;
import faker.android.decoder.pipeline.AndroidProject;
import faker.android.decoder.pipeline.Apk;
import faker.android.decoder.pipeline.Transform;
import faker.android.decoder.pipeline.TransformInvocation;
import faker.android.decoder.smali.DexToSmaliException;
import faker.android.decoder.smali.SmaliDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class DexToSmali extends Transform {
    private static final Logger LOG = LoggerFactory.getLogger(DexToSmali.class);

    public DexToSmali(Apk apk, AndroidProject androidProject) {
        super(apk, androidProject);
    }
    @Override
    public void transform(TransformInvocation transformInvocation) {
        transformInvocation.callBack("dexToSmali");
        List<String> names = (List<String>) androidProject.getIntermediate(AndroidProject.INTERMEDIATE_DEX_NAMES);
        AndroidProject.ManifestInfo s = (AndroidProject.ManifestInfo) androidProject.getIntermediate(AndroidProject.INTERMEDIATE_MANIFESTINFO);
        LOG.info(s.toString());
        for (String name:names) {
            try {
                SmaliDecoder.decode(apk.getApkFile(),androidProject.getSmali(),name,true,Integer.valueOf(s.getMinSdkVersion()));
            } catch (DexToSmaliException e) {
                e.printStackTrace();
            }
        }
    }
}
