package com.faker.android;

public class FakerMain {
    ///TODO 来个中文注释吧
    //static String apkFilePath = "C:\\Users\\Yang\\Desktop\\apk\\_vivo_ad-release.apk";
    static String apkFilePath = "C:\\Users\\Yang\\Desktop\\apk\\牛仔很忙.apk";

    public static void main(String[] args) {
        System.out.println(apkFilePath);
        FakerTransfer.translate(apkFilePath,null);
    }
}
