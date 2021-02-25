package faker.android.decoder;
import faker.android.decoder.apktool.Resources;
import faker.android.decoder.dex2jar.Dex2jar;
import faker.android.decoder.exception.BinaryException;
import faker.android.decoder.exception.FakerAndroidException;
import faker.android.decoder.il2cpp.Il2cppBinary;
import faker.android.decoder.runtime.base.RuntimeBase;
import faker.android.decoder.smali.DexToSmaliException;
import faker.android.decoder.smali.SmaliDecoder;
import faker.android.decoder.util.TestUtils;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class TestDistribution {
    Logger logger = Logger.getLogger("TestDistribution");
    static File in = TestUtils.getFileFromSampleDir("niuzai.apk");

    @Test
    public void resources() throws IOException {
        File out = TestUtils.getFileOutSampleDir("resources","niuzai");
        Resources.decode(in,out);
    }

    @Test
    public void testDex2Jar() throws IOException {
        File out = TestUtils.getFileOutSampleDir("testDex2Jar","niuzai");
        Dex2jar.toJar(in,out);
    }

    @Test
    public void testDex2Smali() throws IOException, DexToSmaliException {
        File out = TestUtils.getFileOutSampleDir("testDex2Smali","niuzai");
        SmaliDecoder.decode(in,out,"classes.dex",true,23);
    }

    @Test
    public void testIl2CppDumper() throws Exception{
        File out = TestUtils.getFileOutSampleDir("testIl2CppDumper","niuzai");
        Il2cppBinary.dumpIl2cpp(in,out);
    }

    @Test
    public void testMergeJavaLib() throws IOException, BinaryException, FakerAndroidException {
        File out = TestUtils.getFileOutSampleDir("testMergeJavaLib","niuzai");
        RuntimeBase.mergeRuntimeLibsJava(out);
    }

    @Test
    public void testMergeCppLib() throws IOException, BinaryException, FakerAndroidException {
        File out = TestUtils.getFileOutSampleDir("testMergeCppLib","niuzai");
        RuntimeBase.mergeRuntimeLibsCpp(out);
    }

    @Test
    public void testMergeCppCode() throws IOException, BinaryException, FakerAndroidException {
        File out = TestUtils.getFileOutSampleDir("testMergeCppCode","niuzai");
        RuntimeBase.mergeRuntimeCppCode(out);
    }

    @Test
    public void testMergeJavaCode() throws IOException, BinaryException, FakerAndroidException {
        File out = TestUtils.getFileOutSampleDir("testMergeJavaCode","niuzai");
        RuntimeBase.mergeRuntimeJavaCode(out);
    }
}
