
# FakerAndroid
A tool translate apk file to common android project and support so hook and include il2cpp c++ scaffolding when apk is a il2cpp game apk

# 求大拿提供Res混淆decode失败解决方案ing... 

## 简介
- 直接将Apk文件转换为可以进行二次开发的Android项目的工具,支持so hook,对于il2cpp的游戏apk直接生成il2cpp c++脚手架
- 将痛苦的逆向环境，转化为舒服的开发环境，告别汇编，告别二进制，还有啥好说的~~ 
## 特点

- 基于AndroidStudio进行smali修改编译
- 提供Java层代码覆盖及继承替换的脚手架
- 提供so函数Hook Api
- 对于il2cpp的游戏apk直接生成il2cpp c++脚手架
- Java层标准的对原有Java api的AndroidStudio编码提示
- smali文件修改后运行或打包时自动回编译（AndroidStudio project 文件树模式下可以直接找到smali文件，支持对smali修改，增量编译）
- Debug知道直接定位为到Java伪代码
- 对于il2cpp的游戏apk,标准的Jni对原有il2cpp脚本的编码提示
- 无限的可能性和扩展性,能干啥你说了算~
- Dex折叠，对敏感已经存在或植入代码进行隐藏规避静态分析
### 运行环境
- Java
- il2cpp游戏Apk，非windows暂时无法生成il2cpp c++脚手架
- 目前来说decode环节还是主要依赖了Apktool,请确保Apktool decode是可以通过的，FakerAndroid 保留了Apktool本来命令  
例： ```java -jar FakerAndroid.jar d -f <apkpath> -o <outPath>```
- 对于加壳的apk请自行脱壳后使用,理论上Apktool正常decode、encode的Apk都可以一马平川的直接运行
### 使用方式
- 下载[FakerAndroid.jar](https://github.com/Efaker/FakerAndroid/releases)(2020/11/15/16:53:00)
- cmd命令行 ```cd <FakerAndroid.jar平级目录>``` 
- cmd命令行 ```java -jar FakerAndroid.jar fk <apkpath>``` (项目生成路径与apk文件平级) 或 ```java -jar FakerAndroid.jar fk <apkpath> -o <outdir>```
- 例：```java -jar FakerAndroid.jar fk D:\apk\test.apk``` 或 ```java -jar FakerAndroid.jar fk D:\apk\test.apk -o D:\test```

### 生成的Android项目二次开发教程(<a href="https://blog.csdn.net/easy6798/article/details/109404325" target="_blank">图文教程</a>)
##### 1、打开项目
- Android studio直接打开工具生成的Android项目
- 保持跟目录build.gradle中com.android.tools.build:gradle:3.4.0依赖固定，请勿升级或修改该版本号，且项目配置NDk版本为21
- 存在已知缺陷，res下的部分资源文件编译不过，需要手动修复一下，部分Manifest标签无法编译需要手动修复

##### 2、调试运行项目
- 连接测试机机
- Run项目
##### 3、进阶
- 类调用  
  借助javaScaffoding 在主模块（app/src/main/java)编写java代码对smali代码进行调用  
- 类替换      
  在主模块（app/src/main/java）直接编写Java类，类名与要替换的类的smali文件路径对应
- Smali 增量编译  
  你可以使用传统的smali修改方式对smali代码进行修改，且编译方式为最小文件数增量编译，smali文件修改后javascaffoding会同步，比如遇到final或private的java元素无法掉用时可以先修改smali(执行一次编译后javaScaffoding会同步)
- So Hook  
  借助FakeCpp 使用jni对so函数进行hook替换
- il2cpp unity游戏脚本二次开发  
  借助il2cpp Scaffolding 和FakeCpp,使用jni对原il2cpp游戏脚本进行Hook调用
- Dex折叠  
  build.gradle 配置sensitiveOptions用于隐藏敏感的dex代码，以规避静态分析
  
  
##### 4、正在路上
resources.arsc decode 兼容，目前混淆某些大型 apk Res decoder有问题  
各种不想情况兼容
##### 5、遇到问题了？兄弟别走肯定能用，而且是你最佳的解决方案，咨询探讨
- QQ群:901157034
- 版本定制:QQ 1404774249
- [问题反馈](https://github.com/Efaker/FakerAndroid/issues)
##### 6、给个star?免费的           

##### 7、兼容性
1、目前某些大型的apk资做过资源文件混淆的会有问题！  
2、加固Apk需要先脱壳后才能,暴漏java api  
3、有自校验的Apk,须项目运行起来后自行检查破解  
4、Defalut Activity Not found，请先将AndroidManifest.xml中Application节点下非启动Activity标签注释掉后做一次运行，再还原试试  
5、暂时不支持x86，pc模拟器是x86
## 实例(过时，稍后补充新的)
- 一夜之间来了这么多的star好慌张，全是问怎么用的，请原谅我只会写代码不会写文档啊！！！  
- 因为是il2cpp 游戏apk，里面包含了原游戏安装包和生成的代码项目所以比较大，下载的话得时间稍微长一点，如果github实在下不下来的话，直接QQ我索要
- [实例下载地址](https://github.com/Efaker/FakerAndroid-Demos/releases/tag/BasicDemo1)








        
        
        
        
        
      
                
 








