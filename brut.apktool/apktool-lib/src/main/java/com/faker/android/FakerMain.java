package com.faker.android;
public class FakerMain {
    static String apkFilePath = "xxx.apk";
    public static void main(String[] args) {
        System.out.println(apkFilePath);
        FakerTransfer.translate(apkFilePath,null,null);
    }
}
