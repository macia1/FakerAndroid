package com.facker.toolchain;

public class FakerMain {
    static String apkFilePath = "D:\\DemoApk\\牛仔很忙.apk";
    public static void main(String[] args) {
        FakerTransfer.translate(apkFilePath);
    }
}
