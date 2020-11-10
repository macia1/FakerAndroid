package com.faker.android;

import com.luhuiguo.chinese.ChineseUtils;
import com.luhuiguo.chinese.pinyin.PinyinFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SrcDecodeApk {

    private File originalFile;
    private String outDir;
    private String md5;

    private long id;

    private static String DEX_MATCHER = "classes[1-9]?\\d*\\.dex";

    public long getId() {
        return id;
    }

    public SrcDecodeApk(File originalFile) {
        this.originalFile = originalFile;
    }
    public void setOutDir(String outDir){
        this.outDir = outDir;
    }
    public File getOriginalApkFile() {
        return originalFile;
    }
    public File getProjectDir() {
        if(outDir==null){
            File projectDir = new File(originalFile.getParent(), ChineseUtils.toPinyin(originalFile.getName().replace(".apk",""), PinyinFormat.TONELESS_PINYIN_FORMAT).replace(" ","-"));
            if(!projectDir.exists()){
                projectDir.mkdir();
            }
            return projectDir;
        }else {
            File outDirFile = new File(outDir);
            if(outDirFile.getParentFile().exists()&&outDirFile.getParentFile().isDirectory()){
                outDirFile.mkdir();
                return outDirFile;
            }else {
                System.out.println("out put dir is illegal---");
            }
        }
        return null;
    }

    public File getJavaScaffoding(){
        File file = new File(getGameDir(),"javaScaffoding");
        return file;
    }
    public File getJavaScaffodingLibs(){
        File file = new File(getJavaScaffoding(),"libs");
        return file;
    }
    public File getgetJavaScaffodingMain(){
        File file = new File(getJavaScaffoding(),"src");
        return file;
    }
    public File getJavaScaffodingMain(){
        File file = new File(getgetJavaScaffodingMain(),"main");
        return file;
    }

    public File getJavaScaffodingJava(){
        File file = new File(getJavaScaffodingMain(),"java");
        return file;
    }
    public File getGameDir(){
        File file = new File(getProjectDir(),"app");
        return file;
    }
    public File getGameBuild(){
        File file = new File(getGameDir(),"build.gradle");
        return file;
    }
    public File getAppSrc(){
        File file = new File(getGameDir(),"src");
        return file;
    }
    public File getDecodeDir() {
        File decodeDir = new File(getAppSrc(),"main");
        if(!decodeDir.exists()){
            decodeDir.mkdirs();
        }
        return decodeDir;
    }

//    public File getProjectDir() {
//        File file = new File(originalFile.getParent(),originalFile.getName().replace(".apk",""));
//        return file;
//    }
    public File getMETAINF() {
        File file = new File(getDecodeDir(),"META-INF");
        return file;
    }
    public File getAssets() {
        File file = new File(getDecodeDir(),"assets");
        if(!file.exists()){
            file.mkdir();
        }
        return file;
    }

    public File getLibDir() {
        File file = new File(getDecodeDir(),"lib");
        return file;
    }

    public File getXMLDir() {
        File file = new File(getResDir(),"xml");
        return file;
    }

    public File getResDir() {
        File file = new File(getDecodeDir(),"res");
        if(!file.exists()){
            file.mkdir();
        }
        return file;
    }

    public File getManifestFile() {
        File file = new File(getDecodeDir(),"AndroidManifest.xml");
        return file;
    }

    public File getYmlFile() {
        File file = new File(getDecodeDir(),"apktool.yml");
        return file;
    }

    public File getSmalis(){
        File file = new File(getDecodeDir(),"smali");
        return file;
    }


    public List<File> getDexsFiles() {
        List<File> targetDexList = new ArrayList<File>();
        for (File f : getDecodeDir().listFiles()) {
            if (f.getName().matches(DEX_MATCHER)) {
                targetDexList.add(f);
            }
        }
        return targetDexList;
    }


    public File getjniLibs() {
        File file = new File(getDecodeDir(),"jniLibs");
        return  file;
    }
    public File getCpp() {
        File file = new File(getDecodeDir(),"cpp");
        return  file;
    }

    public File getResources() {
        File file = new File(getDecodeDir(),"resources");
        return  file;
    }

    public File getGradle() {
        File file = new File(getProjectDir(),"gradle");
        return  file;
    }

    public File getJava(){
        File file = new File(getDecodeDir(),"java");
        return  file;
    }
    public File getLibs(){
        File file = new File(getGameDir(),"libs");
        return  file;
    }
    public boolean decode(){
        return ApkTool.decodeSrc(getOriginalApkFile(),getDecodeDir());
    }
}
