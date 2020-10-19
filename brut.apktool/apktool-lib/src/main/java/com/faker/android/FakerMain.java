package com.faker.android;

public class FakerMain {
    ///TODO 来个中文注释吧
    static String apkFilePath = "C:\\Users\\Yang\\Desktop\\apk\\超级进球.apk";
    public static void main(String[] args) {
        System.out.println(apkFilePath);
        FakerTransfer.translate(apkFilePath,null);
    }
}
