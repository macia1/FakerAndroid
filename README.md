# FakerAndroid ([FakerAndroid.jar1.0.1 ](https://github.com/Efaker/FakerAndroid/releases))

# FakerAndroid ([中文文档](https://github.com/Efaker/FakerAndroid/blob/main/CHINESE.md))
A tool translate a apk file to stantard android project include so hook api and il2cpp c++ scaffolding when apk is a unity il2cpp game.
## Summary
- Write code on a apk file elegantly.
- The Apk file can be directly converted into Android project for secondary development, supporting so hook. For the game of il2cpp, APK directly generates il2cpp C++ scaffolding
- What's more to say about transforming the painful reverse environment into a comfortable development environment, saying goodbye to assembly and binary~ 
## Feature
- Stantard AndroidStudio android project generated
- Original java class usage or cover it by compileable java code
- Hook Api offered for hooking .so method 
- When apk is a il2cpp game il2cpp c++ scaffoding generated
- Back compilation voluntary when there is a modification of smali files(AndroidStudio project file tree model)
- Code hints base original java api
- Unlimited possibilities and expansibility. You has the final say
### Environment
- Java
- Il2cpp Game Apk unable to generate il2cpp C++ scaffolding for non windows
### Usage
- Download [FakerAndroid.jar](https://github.com/Efaker/FakerAndroid/releases)(2020/11/15/16:52:00)
- cmd ```cd <FakerAndroid.jar base dir>``` 
- cmd ```java -jar FakerAndroid.jar fk <apkpath>``` (project will be generated in the same dir of the orininal apk) or ```java -jar FakerAndroid.jar fk <apkpath> -o <outdir>```
- Demo```java -jar FakerAndroid.jar fk D:\apk\test.apk``` or ```java -jar FakerAndroid.jar fk D:\apk\test.apk -o D:\test```
### Secondary development course
##### 1、Open the project
- By Android studio File->open->```<generated project root>```
- Keep the root dir build.gradle file depends com.android.tools.build:gradle:3.4.0,don't upgrate or modify it
- Set project ndk base version 21 best
- A little modification will be needed by yourself when the res or AndroidManifest.xml can't pass the compiler 
##### 2、Debug or run the project
- With a testing machine conected
##### 3、Advanced
- Original java class call  
  With the help of javaScaffoding write your java code to call original class in app moudle(app/src/main/java) 
- Original java class replacement      
  Write java code in moudle app（app/src/main/java）,keep the class name and package name corresponding same as the original class
- Smali increament building  
  When there is a smali files mod,there is a least files builing 
- So Hook  
  With the help of fakeCpp api use jni hook the so method
- Il2cpp unity script development  
  With the help of il2cpp Scaffolding and fakeCpp api,use jni have a modification of il2cpp game script
##### 4、Feature on the way
  resources.arsc decode format compat 
##### 5、Issues
- [Issues](https://github.com/Efaker/FakerAndroid/issues)
- custom version QQ 1404774249
##### 6、Give me a star?free!           
## Demo 
- [Demo Download](https://github.com/Efaker/FakerAndroid-Demos/releases/tag/BasicDemo1)








        
        
        
        
        
      
                
 








