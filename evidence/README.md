# web3sdk-note(evidence)
## 准备工作
```
$ git clone https://github.com/FISCO-BCOS/evidenceSample /opt/evidenceSample
```
## 开始编译:
```
$ cd /opt/evidenceSample/evidence
$ ll
total 4
drwxr-xr-x. 5 root root   46 Oct 12 10:23 build
-rw-r--r--. 1 root root 2967 Oct 11 18:47 build.gradle
drwxr-xr-x. 2 root root   24 Oct 11 18:47 lib
drwxr-xr-x. 3 root root   17 Oct 11 18:47 src
$ yes|cp /opt/web3sdk/dist/apps/web3sdk.jar ./lib/
$ gradle build

> Task :compileJava 
Note: /opt/evidenceSample/evidence/src/main/java/org/bcos/evidence/app/BcosApp.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.


Deprecated Gradle features were used in this build, making it incompatible with Gradle 5.0.
See https://docs.gradle.org/4.6/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 1s
3 actionable tasks: 3 executed
$ ls
build  build.gradle  evidence  lib  src
```
## 替换存证工具包生成的配置文件为区块链中的文件，物料包构建的区块链示例如下:
```
$ yes|cp /opt/10.0.252.5_agent1/build/web3sdk/conf/* ./evidence/conf/
```
## 执行程序:
```
$ cd evidence/bin && chmod +x *.sh evidence && ll
total 20
-rwxr-xr-x. 1 root root 1128 Oct 17 11:32 compile.sh
-rwxr-xr-x. 1 root root 8458 Oct 17 11:32 evidence
-rwxr-xr-x. 1 root root  809 Oct 17 11:32 runEvidence.sh
$ ./runEvidence.sh
```