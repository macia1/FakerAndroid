package faker.android.decoder.conversation;

import faker.android.decoder.pipeline.AndroidProject;
import faker.android.decoder.pipeline.Apk;
import faker.android.decoder.pipeline.Context;
import faker.android.decoder.pipeline.Conversation;
import faker.android.decoder.transforms.DexToJar;
import faker.android.decoder.transforms.DexToSmali;
import faker.android.decoder.transforms.Il2cppDumper;
import faker.android.decoder.transforms.ResourceProcesser;

public class SupportConversation extends Conversation {

    public SupportConversation(Context context) {
        super(context);
    }
    @Override
    public void converse() {
        //获取到环境变量
        Apk apk = context.getApks().iterator().next();
        AndroidProject  androidProject = context.getAndroidProject();

        context.getTransformManager().addTransform(new ResourceProcesser(apk,androidProject));

        context.getTransformManager().addTransform(new DexToJar(apk,androidProject));

        context.getTransformManager().addTransform(new DexToSmali(apk,androidProject));

        context.getTransformManager().addTransform(new Il2cppDumper(apk,androidProject));
    }
}
