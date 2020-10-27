package com.faker.android;
import java.io.IOException;

public abstract class IImporter {
	protected static String TAG = "Importer";
	protected XSrcTarget xSrcTarget;

	protected SourceCode sourceCode;

	//处理DEX
	abstract boolean unZipTarget();

	abstract boolean orlderXTarget(XSrcTarget xSrcTarget) throws IOException;

	abstract boolean mergeSourceCode(SourceCode sourceCode,XSrcTarget xSrcTarget) throws IOException;

	abstract boolean makeCppScaffolding(XSrcTarget xSrcTarget) throws IOException;

	abstract boolean makeJavaScaffolding(SourceCode sourceCode,XSrcTarget xSrcTarget) throws IOException;

	abstract boolean makeJavaScaffoldingLib(SourceCode sourceCode,XSrcTarget xSrcTarget) throws IOException;

	abstract boolean mergeFaker(SourceCode sourceCode,XSrcTarget xSrcTarget) throws IOException;

	abstract boolean modManifest(SourceCode sourceCode,XSrcTarget xSrcTarget) throws IOException;

	abstract boolean fixRes(SourceCode sourceCode,XSrcTarget xSrcTarget) throws IOException;

	//TODO
	protected IImporter(XSrcTarget xSrcTarget,SourceCode sourceCode) {
		this.xSrcTarget = xSrcTarget;
		this.sourceCode = sourceCode;
	}

	public void doImport() throws IOException {
		if(!unZipTarget()){
			return;
		}
		if(!orlderXTarget(xSrcTarget)){
			return;
		}

		if(!makeCppScaffolding(xSrcTarget)){

		}
		if(!mergeSourceCode(sourceCode,xSrcTarget)){
			return;
		}
		if(!makeJavaScaffolding(sourceCode,xSrcTarget)){
			return;
		}
		if(!makeJavaScaffoldingLib(sourceCode,xSrcTarget)){
			return;
		}
		if(!mergeFaker(sourceCode,xSrcTarget)){
			return;
		}
		if(!modManifest(sourceCode,xSrcTarget)){
			return;
		}
		if(!fixRes(sourceCode,xSrcTarget)){
			return;
		}


		System.out.println("your have faked a android project from a apk file the path is "+xSrcTarget.getProjectDir());
	}
}
