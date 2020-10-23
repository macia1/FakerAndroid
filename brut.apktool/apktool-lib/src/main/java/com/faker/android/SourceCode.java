package com.faker.android;

import java.io.File;

public class SourceCode {

    private String sourceRoot;

    public SourceCode(String sourceRoot) {
        this.sourceRoot = sourceRoot;
    }
    public String getSourceRoot() {
        return sourceRoot;
    }
    public String getJavaScaffodingLibs(){
        return sourceRoot+"/libs-javaScaffoding";
    }
    public String getJavaScaffodingJava(){
        return sourceRoot+"/java-javaScaffoding";
    }
    public String ManifestjavaScaffoding(){
        return sourceRoot+"/Manifest-javaScaffoding";
    }
    public String getBuildGame(){
        return sourceRoot+"/build-app";
    }
    public String getBuildJavaScffoing(){
        return sourceRoot+"/build-javaScaffoding";
    }

    public String getBuildProject(){
        return sourceRoot+"/build-project";
    }
    public String getCpp(boolean isIl2cpp){
        if(isIl2cpp){
            return sourceRoot+"/il2cpp-cpp";
        }
        return sourceRoot+"/cpp";
    }

    public String getGradle(){
        return sourceRoot+"/gradle";
    }

    public String getJava(boolean isIl2cpp){
        if(isIl2cpp){
            return sourceRoot+"/il2cpp-java";
        }
        return sourceRoot+"/app-java";
    }

    public String getJniLibs(){
        return sourceRoot+"/jniLibs";
    }

    public String getRes(){
        return sourceRoot+"/res";
    }

    public String getScaffolding_cpp(){
        return sourceRoot+"/scaffolding-cpp";
    }
}
