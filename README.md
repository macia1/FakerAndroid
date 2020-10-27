# FakerAndroid[中文文档](https://github.com/Efaker/FakerAndroid/blob/main/CHINESE.md)
A tool translate apk file to common android project and support so hook and include il2cpp c++ scaffolding when apk is a il2cpp game apk

## Summary
- The APK file can be directly converted into Android project tools for secondary development, supporting so hook. For the game of il2cpp, APK directly generates il2cpp C + + scaffolding
- What's more to say about transforming the painful reverse environment into a comfortable development environment, saying goodbye to assembly and binary~ 
## Feature
- Stantard AndroidStudio android project generated
- Original java class usage or cover it by compileable java code
- Hook Api offered for hooking .so method 
- When apk is a il2cpp game il2cpp c++ scaffoding generated
- Smali file edite in AndroidStudio and back compilation voluntary when there is a modification 
- Unlimited possibilities and expansibility. You has the final say
### Environment
- Java
- Il2cpp Game Apk unable to generate il2cpp C++ scaffolding for non windows
### Usage
- Download [FakerAndroid.jar](https://github.com/Efaker/FakerAndroid/releases/tag/0.0.1)(2020/10/27/15:12:00)
- cmd ```cd <FakerAndroid.jar base dir>``` 
- cmd ```java -jar FakerAndroid.jar fk <apkpath>``` (project will be generated in the same dir of the orininal apk) or ```java -jar FakerAndroid.jar fk <apkpath> -o <outdir>```
- Demo```java -jar FakerAndroid.jar fk D:\apk\test.apk``` or ```java -jar FakerAndroid.jar fk D:\apk\test.apk -o D:\test```
### Secondary development course

##### 1、Open the project
- By Android studio File->open->```<generated project root>```
- keep the root dir build.gradle file depends com.android.tools.build:gradle:3.4.0,don't upgrate or modify it
- Set project ndk base version 21 best
- A little modification will be needed by yourself when the res or AndroidManifest.xml can't pass the compiler 
##### 2、Debug or Run the project
- With a testing machine conected
- Run（Note：reason of dex cache,when have a smali file modification you should make a uninstall before run it）
##### 3、Advanced
- Original java class call  
  With the help of javaScaffoding in app moudle(app/src/main/java) write your java to call orinal class  
- Original java class replacement      
  In moudle app（app/src/main/java）write java code,keep class name and package name corresponding to the original class   
- So Hook  
  With the help of FakeCpp use jni hook the so method
- il2cpp unity script development  
  With the help of il2cpp Scaffolding and FakeCpp,use jni have a modification of il2cpp game script
##### 4、Issues
- [issues](https://github.com/Efaker/FakerAndroid/issues)
##### 5、Give me a star?free!           

## Demo
- [Demo Download](https://github.com/Efaker/FakerAndroid-Demos/releases/tag/BasicDemo1)








        
        
        
        
        
      
                
 








