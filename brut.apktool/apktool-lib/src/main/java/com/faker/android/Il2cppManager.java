package com.faker.android;

import brut.common.BrutException;
import brut.util.AaptManager;
import brut.util.Jar;
import brut.util.OSDetection;

import java.io.File;

public class Il2cppManager {
    public static File getIl2cppBinary() throws BrutException {
        File il2cppBinary=null;
        if (OSDetection.isMacOSX()) {
        } else if (OSDetection.isUnix()) {
        } else if (OSDetection.isWindows()) {
            il2cppBinary = Jar.getResourceAsFile("/prebuilt/windows/"+"il2cpp.exe", Il2cppManager.class);
        }
        if(il2cppBinary!=null){
            il2cppBinary.setExecutable(true);
        }
        return il2cppBinary;
    }
}
