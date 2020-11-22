package com.faker.android;
public class FakerMain {
    static String apkFilePath = "C:\\Users\\Yang\\Desktop\\apk\\牛仔很忙.apk";
    public static void main(String[] args) {
        System.out.println(apkFilePath);
        FakerTransfer.translate(apkFilePath,null,null);
    }
}
