# web3sdk-note(web3sdk)
## 准备web3sdk
```
$ git clone https://github.com/FISCO-BCOS/web3sdk /opt/web3sdk
$ cd /opt/web3sdk && find ./ -name "*.sh" | xargs chmod +x
```
## web3sdk默认的连接超时时间是30秒，某些环境可能不足，可以选择增大为60秒或者90秒

```
$ cat src/main/java/org/bcos/channel/client/Service.java | grep "private Integer connectSeconds"
        private Integer connectSeconds = 30;
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
#### 确保/opt/web3sdk/dist/conf/applicationContext.xml配置的监听ip和端口与区块链一致
```
$ cat /opt/10.0.252.5_agent1/build/node0/config.json|grep channelPort
    "channelPort":"8821",
$ netstat -antp|grep 8821
tcp        0      0 0.0.0.0:8821            0.0.0.0:*               LISTEN      676/./fisco-bcos
$ vi /opt/web3sdk/dist/conf/applicationContext.xml  
```
#### 如果是调试AMOP极简的例子需要保证客户端和服务端连接不同的节点（机构），可以简单的拷贝一份配置文件做修改
```
$ cp /opt/web3sdk/dist/conf/applicationContext.xml /opt/web3sdk/dist/conf/applicationContext2.xml
$ vi /opt/web3sdk/dist/conf/applicationContext2.xml  
```
#### 确认一下两个配置文件连接的节点是不一样的，如果调试的是存证样例，这一步可以忽略
```
$ diff /opt/web3sdk/dist/conf/applicationContext.xml /opt/web3sdk/dist/conf/applicationContext2.xml
48c48
<                                                               <value>node1@127.0.0.1:8821</value>  
---
>                                                               <value>node1@127.0.0.1:8822</value>  
```
## 查看web3sdk日志:
```
查找日志目录，./log/是指程序执行当前目录
$ cat /opt/web3sdk/dist/conf/log4j2.xml|grep logPath
        <Property name="logPath">./log/</Property>
                <RollingFile name="fileAppender" fileName="${logPath}all.log"
                                         filePattern="${logPath}all.log.%d{yyyy-MM-dd}.%i.log.gz">
                <RollingFile name="debugLog" fileName="${logPath}debug.log"
                                         filePattern="${logPath}debug.log.%d{yyyy-MM-dd}.%i.log.gz">
                <RollingFile name="infoLog" fileName="${logPath}info.log"
                                         filePattern="${logPath}info.log.%d{yyyy-MM-dd}.%i.log.gz">
                <RollingFile name="warnLog" fileName="${logPath}warn.log"
                                         filePattern="${logPath}warn.log.%d{yyyy-MM-dd}.%i.log.gz">
                <RollingFile name="errorLog" fileName="${logPath}error.log"
                                         filePattern="${logPath}error.log.%d{yyyy-MM-dd}.%i.log.gz">
$ ll log
total 208
-rw-r--r--. 1 root root 105501 Oct 17 10:28 all.log
-rw-r--r--. 1 root root 105501 Oct 17 10:28 debug.log
-rw-r--r--. 1 root root      0 Oct 17 10:27 error.log
-rw-r--r--. 1 root root      0 Oct 17 10:27 info.log
-rw-r--r--. 1 root root      0 Oct 17 10:27 warn.log
$ tail -f log/all.log
2018-10-17 10:28:55.911 [nioEventLoopGroup-2-1] DEBUG ChannelPush2() - send ChannelResponse seq:null
2018-10-17 10:28:55.912 [nioEventLoopGroup-2-1] DEBUG Service() - response seq:d3db34b96ccf495d9e1a273de34cdb5a length:0
2018-10-17 10:28:55.913 [nioEventLoopGroup-2-1] DEBUG Service() - channel2 message
2018-10-17 10:28:55.913 [nioEventLoopGroup-2-1] DEBUG ChannelMessage2() - readExtra channel2 package: 0
2018-10-17 10:28:55.913 [nioEventLoopGroup-2-1] DEBUG ChannelMessage2() - data: 52 [114, 101, 99, 101, 105, 118, 101, 32, 114, 101, 113, 117, 101, 115, 116, 32, 115, 101, 113, 58, 100, 51, 100, 98, 51, 52, 98, 57, 54, 99, 99, 102, 52, 57, 53, 100, 57, 101, 49, 97, 50, 55, 51, 100, 101, 51, 52, 99, 100, 98, 53, 97]
2018-10-17 10:28:55.914 [nioEventLoopGroup-2-1] DEBUG Service() - ChannelResponse seq:d3db34b96ccf495d9e1a273de34cdb5a
2018-10-17 10:28:55.914 [nioEventLoopGroup-2-1] DEBUG Service() - channel message:d3db34b96ccf495d9e1a273de34cdb5a
2018-10-17 10:28:55.914 [nioEventLoopGroup-2-1] DEBUG Service() - found callback response
2018-10-17 10:28:55.914 [nioEventLoopGroup-2-1] DEBUG Service() - response: receive request seq:d3db34b96ccf495d9e1a273de34cdb5a
2018-10-17 10:28:56.863 [threadDeathWatcher-3-1] DEBUG PoolThreadCache() - Freed 1 thread-local buffer(s) from thread: threadDeathWatcher-3-1
```