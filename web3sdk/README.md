# web3sdk-note(web3sdk)
## 准备web3sdk
```
$ git clone https://github.com/FISCO-BCOS/web3sdk /opt/web3sdk
$ cd /opt/web3sdk && find ./ -name "*.sh" | xargs chmod +x
```
## 开始编译:
```
$ ./compile.sh
linux-firmware-20150904-43.git6ebf5d5.el7.noarch
lm_sensors-libs-3.4.0-4.20160601gitf9185e5.el7.x86_64
net-tools-2.0-0.22.20131004git.el7.x86_64
git-1.8.3.1-14.el7_5.x86_64
crontabs-1.11-6.20121102git.el7.noarch
git has already been installed
lsof-4.87-5.el7.x86_64
lsof has already been installed
unzip-6.0-19.el7.x86_64
unzip has already been installed
Starting a Gradle Daemon (subsequent builds will be faster)

> Task :compileJava 
Note: /opt/web3sdk/src/main/java/org/bcos/web3j/crypto/sm2/crypto/asymmetric/SM2Algorithm.java uses or overrides a deprecated API.
Note: Recompile with -Xlint:deprecation for details.
Note: Some input files use unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.


Deprecated Gradle features were used in this build, making it incompatible with Gradle 5.0.
See https://docs.gradle.org/4.6/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 50s
5 actionable tasks: 4 executed, 1 up-to-date
execute command gradle build SUCCESS
```
## 替换web3sdk生成的配置文件为区块链中的文件，物料包构建的区块链示例如下:
```
$ yes|cp /opt/10.0.252.5_agent1/build/web3sdk/conf/* ./dist/conf/
```
## 修改配置:
#### 确认/opt/web3sdk/dist/conf/applicationContext.xml配置的监听ip和端口与区块链一致
```
$ vi /opt/web3sdk/dist/conf/applicationContext.xml  
```
#### 如果是调试AMOP极简的例子需要保证客户端和服务端连接不同的节点（机构），可以简单的拷贝一份配置文件做修改
```
$ cp /opt/web3sdk/dist/conf/applicationContext.xml /opt/web3sdk/dist/conf/applicationContext2.xml
$ vi /opt/web3sdk/dist/conf/applicationContext2.xml  
```
