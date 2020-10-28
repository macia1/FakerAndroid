package com.faker.android;

import com.googlecode.d2j.dex.Dex2jar;
import com.googlecode.d2j.reader.BaseDexFileReader;
import com.googlecode.d2j.reader.MultiDexFileReader;
import com.googlecode.dex2jar.tools.BaksmaliBaseDexExceptionHandler;

import java.io.File;
import java.nio.file.Files;

public class FakerMain {
    ///TODO 来个中文注释吧
    //static String apkFilePath = "C:\\Users\\Yang\\Desktop\\apk\\_vivo_ad-release.apk";
    //static String apkFilePath = "C:\\Users\\Yang\\Desktop\\apk\\牛仔很忙.apk";
//    static String apkFilePath = "C:\\Users\\Yang\\Desktop\\apk\\kwai-android-generic-gifmakerrelease-8.1.10.16599_x32_44c55a.apk";

    static String apkFilePath = "C:\\Users\\Yang\\Desktop\\apk\\大亨联盟_killer.apk";


    public static void main(String[] args) {
        System.out.println(apkFilePath);
        FakerTransfer.translate(apkFilePath,null);

    }
}
