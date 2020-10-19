package com.faker.android;

import java.io.File;
import java.util.Map;

public class XSrcTarget extends SrcDecodeApk {

	public XSrcTarget(File originalFile) {
		super(originalFile);
	}

	private File splash;

	private Map<String, File> logos;

	private boolean shell;

	public static class Signature {

		private String path;
		private String md5;
		private String storePassword;
		private String keyAlias;

		private String keyPassword;
	}
	public File getPackerDexFile(){
		return new File(getAssets(),"dex");
	}

	public File getEncodeFile(){
		File file = new File(getOriginalApkFile().getParent(),getId()+"_"+getOriginalApkFile().getName());
		return file;
	}

}
