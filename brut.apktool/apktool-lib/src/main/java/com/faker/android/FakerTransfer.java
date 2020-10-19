package com.faker.android;

import java.io.File;
import java.io.IOException;

public class FakerTransfer {
    public static void translate(String apkFilePath,String outPath) {
        SourceCode sourceCode = new SourceCode("/patch");
        File targetFile = new File(apkFilePath);//废弃
        XSrcTarget xSrcTarget = new XSrcTarget(targetFile);
        if(!TextUtil.isEmpty(outPath)){
            xSrcTarget.setOutDir(outPath);
        }
        Importer importer = new Importer(xSrcTarget,sourceCode);
        try {
            importer.doImport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
