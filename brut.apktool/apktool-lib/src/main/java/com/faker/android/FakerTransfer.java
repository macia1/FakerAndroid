package com.faker.android;

import brut.androlib.src.DecoderLogger;
import brut.androlib.src.IFakerLogerCallBack;

import java.io.File;
import java.io.IOException;

public class FakerTransfer {
    public static void translate(String apkFilePath,String outPath,final ILogCat iLogCat) {
        Logger.sendLog(iLogCat,"You are using FakerAndroid(Doc or help github https://github.com/Efaker/FakerAndroid) to fake a stantard Android project...");
        SourceCode sourceCode = new SourceCode("/patch");
        File targetFile = new File(apkFilePath);//废弃
        XSrcTarget xSrcTarget = new XSrcTarget(targetFile);
        if(!TextUtil.isEmpty(outPath)){
            xSrcTarget.setOutDir(outPath);
        }
        if(iLogCat!=null){
            DecoderLogger.getFakerLogger().setFakerLogerCallBack(new IFakerLogerCallBack() {
                @Override
                public void transLog(String log) {
                    Logger.sendLog(iLogCat,log);
                }
            });
        }
        Importer importer = new Importer(xSrcTarget,sourceCode,iLogCat);
        try {
            importer.doImport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
