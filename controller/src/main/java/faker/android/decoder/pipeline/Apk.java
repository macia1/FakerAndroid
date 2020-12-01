package faker.android.decoder.pipeline;

import java.io.File;

public class Apk {
    private File apkFile;

    public Apk(File apkFile){
        this.apkFile = apkFile;
    }

    public File getApkFile() {
        return apkFile;
    }
}
