# FakerAndroid ([中文文档](https://github.com/Efaker/FakerAndroid/blob/main/CHINESE.md))
A tool translate apk file to standard android project and support so hook and include il2cpp c++ scaffolding when apk is a il2cpp game apk  
直接将Apk转换为可二次开发的标准AndroidStudio项目的工具，提供so hook api 对于unity il2cpp游戏Apk同时会生成il2cpp脚手架
## Summary
- The Apk file can be directly converted into Android project for secondary development, supporting so hook. For the game of il2cpp, APK directly generates il2cpp C++ scaffolding
- What's more to say about transforming the painful reverse environment into a comfortable development environment, saying goodbye to assembly and binary~ 
## Feature
- Stantard AndroidStudio android project generated
- Original java class usage or cover it by compileable java code
- Hook Api offered for hooking .so method 
- When apk is a il2cpp game il2cpp c++ scaffoding generated
- Back compilation voluntary when there is a modification of smali files(AndroidStudio project file tree model)
- Code hints base original java api
- Debug in code base original java api
- Unlimited possibilities and expansibility. You has the final say
### Environment
- Java
- Il2cpp Game Apk unable to generate il2cpp C++ scaffolding for non windows
### Usage
- Download [FakerAndroid.jar](https://github.com/Efaker/FakerAndroid/releases/tag/0.0.1)(2020/10/28/17:14:00)
- cmd ```cd <FakerAndroid.jar base dir>``` 
- cmd ```java -jar FakerAndroid.jar fk <apkpath>``` (project will be generated in the same dir of the orininal apk) or ```java -jar FakerAndroid.jar fk <apkpath> -o <outdir>```
- Demo```java -jar FakerAndroid.jar fk D:\apk\test.apk``` or ```java -jar FakerAndroid.jar fk D:\apk\test.apk -o D:\test```
### Secondary development course
##### 1、Open the project
- By Android studio File->open->```<generated project root>```
- keep the root dir build.gradle file depends com.android.tools.build:gradle:3.4.0,don't upgrate or modify it
- Set project ndk base version 21 best
- A little modification will be needed by yourself when the res or AndroidManifest.xml can't pass the compiler 
##### 2、Debug or run the project
- With a testing machine conected
- Run（Note：reason of dex cache,when there have a smali file modification you should make a uninstallation of before installation before run it ）
##### 3、Advanced
- Original java class call  
  With the help of javaScaffoding write your java code to call original class in app moudle(app/src/main/java) 
- Original java class replacement      
  Write java code in moudle app（app/src/main/java）,keep the class name and package name corresponding same as the original class
- So Hook  
  With the help of fakeCpp api use jni hook the so method
- Il2cpp unity script development  
  With the help of il2cpp Scaffolding and fakeCpp api,use jni have a modification of il2cpp game script
##### 4、Issues
- [Issues](https://github.com/Efaker/FakerAndroid/issues)
##### 5、Give me a star?free!           
## Demo
- [Demo Download](https://github.com/Efaker/FakerAndroid-Demos/releases/tag/BasicDemo1)








        
        
        
        
        
      
                
 








